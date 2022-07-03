package com.demo.DrivingLicense.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.DrivingLicense.Controller.Authentication;
import com.demo.DrivingLicense.Entity.CalculateCost;
import com.demo.DrivingLicense.Entity.User;
import com.demo.DrivingLicense.Repository.AdminRepository;
import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.LicenseDetailsRepository;
import com.demo.DrivingLicense.Repository.UserRepository;

@Service
public class UserService {

UserRepository userrepo;
CalculateCost cost;
AdminRepository adminrepository;
Authentication authentication;
AdminRepository adminrepo;
DLNumberRepository dlrepo;
LicenseDetailsRepository licensedetailsrepo;

@Autowired
public UserService(UserRepository userrepo, CalculateCost cost, AdminRepository adminrepository,
		Authentication authentication, AdminRepository adminrepo, DLNumberRepository dlrepo,
		LicenseDetailsRepository licensedetailsrepo) 
{
	super();
	this.userrepo = userrepo;
	this.cost = cost;
	this.adminrepository = adminrepository;
	this.authentication = authentication;
	this.adminrepo = adminrepo;
	this.dlrepo = dlrepo;
	this.licensedetailsrepo = licensedetailsrepo;
}

public User getUserDetailsByIdService(int userId,String cookieUser)
{
	List<String> output=authentication.extract(cookieUser);
	User usercheck=userrepo.findById(userId).orElse(null);//Logged in User
	if(usercheck.getName().equals(output.get(0)) && usercheck.getUserPassword().equals(output.get(1)))
	{
	User user=userrepo.findById(userId).orElse(null);
	return user;
	}
	else
	{
		return null;
	}
}

public String addUserDetailsService(User user)
{
	String s="";
	if(userrepo.existsById(user.getUserId()))
	{
		s= "User with user id:"+user.getUserId()+" already exist";
	}
	else
	{
		userrepo.save(user);
		s= "User's Personal data saved successfully";
	if(user.getVehicleType().equals("2Wheeler"))
	{
		s= s+"\nSince Vehicle Type is 2Wheeler, you need to pass riding test and need to submit Id and Address Proof as well.\nCOST LIST BASED ON ATTEMPT AND VEHICLE TYPE:\nFirst attempt:Rs.200/-\nSecond attempt:Rs.300/-\nThird Attempt:400/- ";
	}
	if(user.getVehicleType().equals("4Wheeler"))
	{
		s= s+"\nSince Vehicle Type is 4Wheeler, you need to pass driving test and need to submit Id and Address Proof as well.\nCOST LIST BASED ON ATTEMPT AND VEHICLE TYPE:\nFirst attempt:Rs.1100/-\nSecond attempt:Rs.1200/-\nThird Attempt:1300/- ";
	}
	}
	return s;
}

public String updateUserDetailsService(User user,String cookieUser)
{
	List<String> output=authentication.extract(cookieUser);
	User usercheck=userrepo.findById(user.getUserId()).orElse(null);//Logged in User
	if(usercheck.getName().equals(output.get(0)) && usercheck.getUserPassword().equals(output.get(1)))
	{
		User tempUser=userrepo.findById(user.getUserId()).orElse(null);
		if(tempUser==null)
		{
			return "User with user id:"+user.getUserId()+" doesn't exist. A user data needs to be added before updating it.";
		}
		if(dlrepo.existsById(user.getUserId()))
		{
			return "License has already been issued to user. Cannot update the changes now.";
		}
		if(licensedetailsrepo.existsById(user.getUserId()))
		{
			return "Application already under progress. Cannot update the changes now.";
		}
		if(user.getUserAddress()!=""&& user.getUserAddress()!=null)
		{
			tempUser.setUserAddress(user.getUserAddress());
		}
		if(user.getUserDOB()!="" && user.getUserDOB()!=null)
		{
			tempUser.setUserDOB(user.getUserDOB());
		}
		if(user.getUserEmail()!="" && user.getUserEmail()!=null)
		{
			tempUser.setUserEmail(user.getUserEmail());
		}
		if(user.getVehicleType()!="" && user.getVehicleType()!=null)
		{
			tempUser.setVehicleType(user.getVehicleType());
		}
		userrepo.save(tempUser);
		return "Changes updated for user id:"+user.getUserId();
	}
	else
		return "Invalid user credentials";	
}

public String deleteUserDetailsService(int userId,String cookieUser)
{
	List<String> output=authentication.extract(cookieUser);
	User usercheck=userrepo.findById(userId).orElse(null);//Logged in User
	if(usercheck.getName().equals(output.get(0)) && usercheck.getUserPassword().equals(output.get(1)))
	{
	if(userrepo.existsById(userId))
	{
		if(licensedetailsrepo.existsById(userId))
		{
		licensedetailsrepo.deleteById(userId);
		}
		userrepo.deleteById(userId);
    	//licensedetailsrepo.deleteById(userId);
    	return "User data deleted successfully and application also deleted from database";
    }
    else
    {
    	return "User with user id:"+userId+" doesn't exist. Kindly enter a valid user id";
    }
    }
	else
	{
		return "Invalid user credentails. Please retry";
	}
}

}
