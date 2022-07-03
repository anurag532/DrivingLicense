package com.demo.DrivingLicense.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Entity.User;
import com.demo.DrivingLicense.Repository.AdminRepository;
import com.demo.DrivingLicense.Repository.UserRepository;

@RestController
public class Authentication {

AdminRepository adminrepository;
UserRepository userrepository;

@Autowired 
public Authentication(AdminRepository adminrepository, UserRepository userrepository) {
	super();
	this.adminrepository = adminrepository;
	this.userrepository = userrepository;
}

public List<String> extract(String cookie)
{
		int p=cookie.indexOf("_");
		String a=cookie.substring(0, p);
		int count=Integer.valueOf(a);
		String name=cookie.substring(p+1, p+count+1);
		String password=cookie.substring(p+count+1);
		List<String> ls=new ArrayList<>();
		ls.add(name);ls.add(password);
		return ls;
}
	
@PostMapping("/login/user")//COOKIE NEEDED-->SHOULD BE POSTMAPPING-->name and userPassword
public String Hello(@RequestBody User user,HttpServletResponse http)
{
	User usercheck=userrepository.findById(user.getUserId()).orElse(null);
	boolean idStatus=false;if(usercheck.getUserId()==user.getUserId()) {idStatus=true;}
	boolean passwordStatus=false;passwordStatus=usercheck.getUserPassword().equals(user.getUserPassword());
	if(idStatus==true && passwordStatus==true)
	{
		Cookie cookieUser=new Cookie("cookieUser",""+usercheck.getName().length()+"_"+usercheck.getName()+usercheck.getUserPassword());
		cookieUser.setMaxAge(60*60*3);
		cookieUser.setHttpOnly(true);
		cookieUser.setPath("/");
		http.addCookie(cookieUser);
		return "Hello "+usercheck.getName()+" [USER], you have successfully logged in. Don't forget to logout, once the required task is completed";
	}
	else
	{
		return "Invalid user credentails. Please retry";
	}
}

@PostMapping("/logout/user")
public String logoutUser (HttpServletResponse http)
{
		Cookie cookieUser=new Cookie("cookieUser",null);
		cookieUser.setMaxAge(0);
		cookieUser.setHttpOnly(true);
		cookieUser.setPath("/");
		http.addCookie(cookieUser);
		return "User logged out successfully";
}

@PostMapping("/login/admin")
public String loginAdmin(@RequestBody Admin admin,HttpServletResponse http)
{
	Admin admincheck=adminrepository.findById(admin.getAdminId()).orElse(null);
	boolean idStatus=false;if(admincheck.getAdminId()==admin.getAdminId()){idStatus=true;}
	boolean passwordStatus=false;passwordStatus=admincheck.getPassword().equals(admin.getPassword());
	if(idStatus==true && passwordStatus==true)
	{
		Cookie cookie=new Cookie("cookie",""+admincheck.getName().length()+"_"+admincheck.getName()+admincheck.getPassword());
		cookie.setMaxAge(60*60*3);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		http.addCookie(cookie);
		return "Hello "+admincheck.getName()+ "[ADMIN], you have successfully logged in. Don't forget to logout, once required task is completed";
	}
		else
		{
			return "Invalid admin credentails. Please retry";
		}
}

@PostMapping("/logout/admin")
public String logoutAdmin(HttpServletResponse http)
{
		Cookie cookie=new Cookie("cookie",null);
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		http.addCookie(cookie);
		return "Admin logged out successfully";
}

}
