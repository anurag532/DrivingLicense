package com.demo.DrivingLicense.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.DrivingLicense.Controller.Authentication;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Repository.AdminRepository;

@Service
public class AdminService {

AdminRepository adminrepo;
Authentication authentication;

@Autowired
public AdminService(AdminRepository adminrepo, Authentication authentication) {
	super();
	this.adminrepo = adminrepo;
	this.authentication = authentication;
}
//public AdminService()
//{
//	
//}

public Admin getAdminDetailsByIdService(int adminId,String cookie)
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		Admin admin=adminrepo.findById(adminId).orElse(null);
		if(admincheck.getName()==admin.getName())
		{
		return admin;//Only if correct admin log
		}
	}
	return null;

}

public String addAdminService(Admin admin,String cookie)
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	if(adminrepo.existsById(admin.getAdminId()))
	{
		return "Admin Already exist with id:"+admin.getAdminId()+", please enter a valid admin id";
	}
	else
	{
		adminrepo.save(admin);
		return "Admin successfully added to the database";
	}
	}
	else
	{
		return "Inavlid admin credentials. An admin needs to login to add another admin in the database";
	}
}

public String deleteAdminService(int adminId,String cookie)
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findById(adminId).orElse(null);//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	if(adminrepo.existsById(adminId))
	{
		adminrepo.deleteById(adminId);
		return "Admin deleted successfully from the database";
	}
	else
	{
		return "Incorrect details. There is no admin with admin id:"+adminId+", please enter a valid admin id";
	}
	}
	else
	{
		return "Inavlid admin credentials. Only admin himself can delete his data.";	
	}
}

}
