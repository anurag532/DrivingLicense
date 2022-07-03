package com.demo.DrivingLicense.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.DrivingLicense.Controller.Authentication;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Entity.LicenseNumber;
import com.demo.DrivingLicense.Repository.AdminRepository;
import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.UserRepository;

@Service
public class LicenseNumberService {


Authentication authentication;
AdminRepository adminrepository;
DLNumberRepository dlrepository;
UserRepository userrepository;

@Autowired
public LicenseNumberService(Authentication authentication, AdminRepository adminrepository,
		DLNumberRepository dlrepository, UserRepository userrepository) {
	super();
	this.authentication = authentication;
	this.adminrepository = adminrepository;
	this.dlrepository = dlrepository;
	this.userrepository = userrepository;
}

public List<LicenseNumber> getLicenseNumberAllService(String cookie)//Only admin access
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepository.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	return dlrepository.findAll();
    }
	return null;
}

public Optional<LicenseNumber> getLicenseNumberByUserIdService(int userId,String cookie)//Only admin access
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepository.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		return dlrepository.findById(userId); 
	}
	else
	{
		return null;
	}
}

public List<LicenseNumber> getLicenseNumberByUserNameService(String name,String cookie)//Only admin access
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepository.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		return dlrepository.findByName(name);
	}
	else
	{
		return null;
	}
}

public List<LicenseNumber> getLicenseNumberByUserDOBService(String userDOB,String cookie)//Only admin access
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepository.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		return dlrepository.findByDOB(userDOB);
	}
	else
	{
		return null;
	}
	
}

public Optional<LicenseNumber> getLicenseNumberByLicenseNumberService(String DLNumber,String cookie)//Only admin access
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepository.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		return dlrepository.findByLicenseNumber(DLNumber);
	}
	else
	{
		return null;
	}
}

}
