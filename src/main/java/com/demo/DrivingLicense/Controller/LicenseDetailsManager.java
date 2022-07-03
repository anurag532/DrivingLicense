package com.demo.DrivingLicense.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.DrivingLicense.Entity.LicenseDetails;
import com.demo.DrivingLicense.Entity.LicenseNumber;
import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.LicenseDetailsRepository;
import com.demo.DrivingLicense.Repository.UserRepository;
import com.demo.DrivingLicense.Service.LicenseDetailsService;

//Only Admin access

@RestController
public class LicenseDetailsManager {

LicenseDetailsService licensedetailsservice;
LicenseDetailsRepository licenseRepo;
UserRepository userrepository;
LicenseNumber DL;
DLNumberRepository DLrepo;

@Autowired
public LicenseDetailsManager(LicenseDetailsService licensedetailsservice, LicenseDetailsRepository licenseRepo,
		UserRepository userrepository, LicenseNumber dL, DLNumberRepository dLrepo) {
	super();
	this.licensedetailsservice = licensedetailsservice;
	this.licenseRepo = licenseRepo;
	this.userrepository = userrepository;
	DL = dL;
	DLrepo = dLrepo;
}

@GetMapping("/applicationStatus/all")
public List<LicenseDetails> getLicenseDetails(@CookieValue(name="cookie",defaultValue="hi")String cookie)//Getting all License Details of user
{
	return licensedetailsservice.getLicenseDetailsService(cookie);
}

@GetMapping("/applicationStatus/{userId}")
public Optional<LicenseDetails> getLicenseDetailsById(@PathVariable(name="userId") int userId,@CookieValue(name="cookie",defaultValue="hi")String cookie)//Getting license Details of userId
{
	return licensedetailsservice.getLicenseDetailsByIdService(userId,cookie);
}

@PostMapping("/applicationStatus")
public String addLicenseDetails(@RequestBody LicenseDetails licensedetails,@CookieValue(name="cookie",defaultValue="hi")String cookie)//Adding a new record of license Details for a user
{
	return licensedetailsservice.addLicenseDetailsService(licensedetails,cookie);
}

@PatchMapping("/applicationStatus")
public String updateLicenseDetails(@RequestBody LicenseDetails licensedetails,@CookieValue(name="cookie",defaultValue="hi")String cookie)//Updating record
{
    return licensedetailsservice.updateLicenseDetailsService(licensedetails,cookie);
}

@DeleteMapping("/applicationStatus/{userId}")
public String deleteLicenseDetails(@PathVariable(name="userId") int userId,@CookieValue(name="cookie",defaultValue="hi")String cookie)//Deleting a record from License Details Entity
{
	return licensedetailsservice.deleteLicenseDetailsService(userId,cookie);
}
}
