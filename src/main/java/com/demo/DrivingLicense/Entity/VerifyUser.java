package com.demo.DrivingLicense.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.LicenseDetailsRepository;
import com.demo.DrivingLicense.Repository.UserRepository;

@Component
public class VerifyUser {

@Autowired
LicenseDetailsRepository licensedetailsrepository;
@Autowired
UserRepository userrepository;
@Autowired
DLNumberRepository dlnumberrepository;
public boolean verifyUserDetailsForLicense(LicenseDetails licensedetails)
{   //Will check if all requirements are fulfilled or not. If yes, license details will be updated and DL will be issued
	if(licensedetails.getIdProof().compareTo("SUBMITTED")==0 && licensedetails.getAddressProof().compareTo("SUBMITTED")==0 && 
	   licensedetails.getMedicalCertificate().compareTo("SUBMITTED")==0 && licensedetails.getTestResult().compareTo("PASS")==0 
	   && licensedetails.getPaymentStatus().compareTo("PAID")==0) 
	{
		if(licensedetails.getAttemptNumber().compareTo("FIRST")==0||licensedetails.getAttemptNumber().compareTo("SECOND")==0
		  ||licensedetails.getAttemptNumber().compareTo("THIRD")==0)
        {
			return true;
        }
	}
	return false;
}
public String updateLicenseDetails(LicenseDetails licensedetails)
{
	int flag=1;
	LicenseDetails tempLicenseDetails=licensedetailsrepository.findById(licensedetails.getUserId()).orElse(null);
	if(licensedetails.getIdProof().compareTo("SUBMITTED")==0 || licensedetails.getIdProof().compareTo("NOT SUBMITTED")==0)
	{
		tempLicenseDetails.setIdProof(licensedetails.getIdProof());
	}
	if(licensedetails.getAddressProof().compareTo("SUBMITTED")==0 || licensedetails.getAddressProof().compareTo("NOT SUBMITTED")==0)
	{
		tempLicenseDetails.setAddressProof(licensedetails.getAddressProof());
	}
	if(licensedetails.getMedicalCertificate().compareTo("SUBMITTED")==0 || licensedetails.getMedicalCertificate().compareTo("NOT SUBMITTED")==0)
	{
		tempLicenseDetails.setMedicalCertificate(licensedetails.getMedicalCertificate());
	}
	if(licensedetails.getPaymentStatus().compareTo("PAID")==0 || licensedetails.getPaymentStatus().compareTo("NOT PAID")==0)
	{
		tempLicenseDetails.setPaymentStatus(licensedetails.getPaymentStatus());
	}
	if(licensedetails.getTestResult().compareTo("PASS")==0 || licensedetails.getTestResult().compareTo("FAIL")==0)
	{
		tempLicenseDetails.setTestResult(licensedetails.getTestResult());
	}
	if(licensedetails.getAttemptNumber().compareTo("FIRST")!=0 && licensedetails.getAttemptNumber().compareTo("SECOND")!=0
			&& licensedetails.getAttemptNumber().compareTo("THIRD")!=0 && licensedetails.getAttemptNumber().compareTo("")!=0
			&& licensedetails.getAttemptNumber()!=null)
	{
		return "ATTEMPT NUMBER EXCEEDED";
	}

	if(licensedetails.getAttemptNumber().compareTo("FIRST")==0||licensedetails.getAttemptNumber().compareTo("SECOND")==0
		||licensedetails.getAttemptNumber().compareTo("THIRD")==0)
	{
		//flag=0;
		tempLicenseDetails.setAttemptNumber(licensedetails.getAttemptNumber());
	}
	if(tempLicenseDetails.getAttemptNumber().compareTo("FIRST")==0||tempLicenseDetails.getAttemptNumber().compareTo("SECOND")==0
		||tempLicenseDetails.getAttemptNumber().compareTo("THIRD")==0)
	{
		flag=0;
	}
	licensedetailsrepository.save(tempLicenseDetails);
	if(tempLicenseDetails.getIdProof().compareTo("SUBMITTED")==0 && tempLicenseDetails.getAddressProof().compareTo("SUBMITTED")==0 &&
	  tempLicenseDetails.getMedicalCertificate().compareTo("SUBMITTED")==0 && tempLicenseDetails.getPaymentStatus().compareTo("PAID")==0
	  && tempLicenseDetails.getTestResult().compareTo("PASS")==0 && flag==0)
	{
		User Userobj=userrepository.findById(tempLicenseDetails.getUserId()).orElse(null);
		LicenseNumber DLobj=new LicenseNumber();
		DLobj.setUserId(licensedetails.getUserId());
		DLobj.setName(Userobj.getName());
		DLobj.setUserDOB(Userobj.getUserDOB());
		DLobj.setVehicleType(Userobj.getVehicleType());
		DLobj.setDLNumber("DL2021"+Userobj.getUserId());
		CalculateCost tempobj=new CalculateCost();
		DLobj.setCost(tempobj.calculate(Userobj.getVehicleType(), tempLicenseDetails.getAttemptNumber()));
		dlnumberrepository.save(DLobj);
		System.out.println(tempLicenseDetails.getUserId());
		return "APPROVED";
	}
	else
	{
		return "NOT APPROVED YET";
	}
}
}
