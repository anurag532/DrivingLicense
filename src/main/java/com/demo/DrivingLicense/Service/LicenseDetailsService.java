package com.demo.DrivingLicense.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.DrivingLicense.Controller.Authentication;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Entity.CalculateCost;
import com.demo.DrivingLicense.Entity.LicenseDetails;
import com.demo.DrivingLicense.Entity.LicenseNumber;
import com.demo.DrivingLicense.Entity.User;
import com.demo.DrivingLicense.Entity.VerifyUser;
import com.demo.DrivingLicense.Repository.AdminRepository;
import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.LicenseDetailsRepository;
import com.demo.DrivingLicense.Repository.UserRepository;

@Service
public class LicenseDetailsService {
	
LicenseDetailsRepository licenserepo;
LicenseNumber DL;
UserRepository userrepository;
DLNumberRepository DLrepo;
AdminRepository adminrepo;
Authentication authentication;
VerifyUser verifyuser;

@Autowired
public LicenseDetailsService(LicenseDetailsRepository licenserepo, LicenseNumber dL, UserRepository userrepository,
		DLNumberRepository dLrepo, AdminRepository adminrepo, Authentication authentication,VerifyUser verifyuser) {
	super();
	this.licenserepo = licenserepo;
	DL = dL;
	this.userrepository = userrepository;
	DLrepo = dLrepo;
	this.adminrepo = adminrepo;
	this.authentication = authentication;
	this.verifyuser=verifyuser;
}

public List<LicenseDetails> getLicenseDetailsService(String cookie)//Getting all License Details of user
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	return licenserepo.findAll();
	}
	else
	{
		return null;
	}
}

public Optional<LicenseDetails> getLicenseDetailsByIdService(int userId,String cookie)//Getting license Details by user ID
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	return licenserepo.findById(userId);
	}
	else
	{
		return null;
	}
}

public String addLicenseDetailsService(LicenseDetails licensedetails,String cookie)// Adding a new record of license Details for a user
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		if(DLrepo.existsById(licensedetails.getUserId())==true)//DL already issued
		{
			return "Driving license already issued for user id:"+licensedetails.getUserId();
		}
		if(licenserepo.existsById(licensedetails.getUserId())==true)//License details already exist
		{
			return "Application status details already exist for user id:"+licensedetails.getUserId()+", please enter a valid user id.";
		}
	   if(userrepository.existsById(licensedetails.getUserId())==true)
	    {
		    User userobj=userrepository.findById(licensedetails.getUserId()).orElse(null);
		    CalculateCost costobj=new CalculateCost();
		    licensedetails.setCost(costobj.calculate(userobj.getVehicleType(), licensedetails.getAttemptNumber()));
		    licenserepo.save(licensedetails);
		    boolean verifyUserResult=verifyuser.verifyUserDetailsForLicense(licensedetails);
			if(licensedetails.getAttemptNumber().compareTo("FIRST")!=0 && licensedetails.getAttemptNumber().compareTo("SECOND")!=0
			  && licensedetails.getAttemptNumber().compareTo("THIRD")!=0 && licensedetails.getAttemptNumber().compareTo("")!=0 )
			{
				licenserepo.deleteById(licensedetails.getUserId());
				return "Attempt Number exceeded for user. Removing user application";
			}
		    if(verifyUserResult==true)
		    {
		       User tempUser=userrepository.findById(licensedetails.getUserId()).orElse(null);
		       LicenseDetails tempLicenseDetails=licenserepo.findById(licensedetails.getUserId()).orElse(null);
		       tempLicenseDetails.setCost(costobj.calculate(tempUser.getVehicleType(), tempLicenseDetails.getAttemptNumber()));
		       licenserepo.save(tempLicenseDetails);
		       LicenseNumber DLobj=new LicenseNumber();
		       DLobj.setUserId(licensedetails.getUserId());
		       DLobj.setName(tempUser.getName());
		       DLobj.setUserDOB(tempUser.getUserDOB());
		       DLobj.setDLNumber("DL2021"+licensedetails.getUserId());
		       DLobj.setVehicleType(tempUser.getVehicleType());
		       DLobj.setCost(tempLicenseDetails.getCost());
		       DLrepo.save(DLobj);
		       return "CONGRATULATIONS!! "+DLobj.getName()+" has successfully completed all the required tasks and the Driving License has been issued.Below are the details:\n"+DLobj;
		    }
		    else
		    {
		    	return "User license application details saved successfully. But all the requirements are not fulfilled yet."
		    	+" Since the Vehicle type is "+userobj.getVehicleType()+" and Attempt number is "+licensedetails.getAttemptNumber()+
		    	", the amount to be paid is Rs."+licensedetails.getCost()+"/-";
		    }
		    
	    }
	   else
	   {
		   return "User Id:"+licensedetails.getUserId() +" hasn't enrolled yet. Please enrol first, then only application status can be updated";
	   }
    }

return "Admin not verified";
}
 
public String updateLicenseDetailsService(LicenseDetails licensedetails,String cookie)//Updating particular field of license Details
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
		if(DLrepo.existsById(licensedetails.getUserId())==true)//DL already issued
		{
			return "Driving license already issued for user id:"+licensedetails.getUserId();
		}
		if(licenserepo.existsById(licensedetails.getUserId())==false)
		{
			return "User data is not present in application status details database. Please add application status details for user first, then only status can be updated";
		}
		if(licenserepo.existsById(licensedetails.getUserId()))
		{
			String status=verifyuser.updateLicenseDetails(licensedetails);
			if(status.compareTo("ATTEMPT NUMBER EXCEEDED")==0) 
			{
				licenserepo.deleteById(licensedetails.getUserId());
				return "Attempt number exceeded more than 3. Removing the user application status from the database";
			}
			if(status.compareTo("APPROVED")==0)
			{
				LicenseNumber DLobj=DLrepo.findById(licensedetails.getUserId()).orElse(null);
				return "CONGRATULATIONS!! "+DLobj.getName()+" has successfully completed all the required task and the Driving License has been issued.Below are the details:\n"+DLobj;	
			}
            if(status.compareTo("NOT APPROVED YET")==0)
            {
            	User tempUser=userrepository.findById(licensedetails.getUserId()).orElse(null);
            	LicenseDetails tempLicenseDetails=licenserepo.findById(licensedetails.getUserId()).orElse(null);
            	CalculateCost costobj=new CalculateCost();
            	tempLicenseDetails.setCost(costobj.calculate(tempUser.getVehicleType(), tempLicenseDetails.getAttemptNumber()));
            	licenserepo.save(tempLicenseDetails);
            	return "User license application details updated successfully. But all the requirements are not fulfilled yet"
     		    	+". Since the Vehicle type is "+tempUser.getVehicleType()+" and Attempt number is "+tempLicenseDetails.getAttemptNumber()+
    		    	", the amount to be paid is Rs."+tempLicenseDetails.getCost()+"/-";
            }
		}
	}
	return "Invalid admin credentails";
}

public String deleteLicenseDetailsService(int userId,String cookie)//Deleting License Details
{
	List<String> output=authentication.extract(cookie);
	Admin admincheck=adminrepo.findByName(output.get(0));//Logged in Admin
	if(admincheck.getName().equals(output.get(0)) && admincheck.getPassword().equals(output.get(1)))
	{
	  if(licenserepo.existsById(userId))
	  {
		licenserepo.deleteById(userId);
		return "License data removed for user id:"+userId;
	  }
	  else
	  {
		return "Data for user id:"+userId+" doesn't exist. Kindly enter a valid user id";
	  }
    }
	else
	{
		return "Inavlid admin credentails. Kindly first log in";
	}
}

}

