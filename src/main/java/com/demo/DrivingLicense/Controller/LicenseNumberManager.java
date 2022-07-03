package com.demo.DrivingLicense.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.DrivingLicense.Entity.LicenseNumber;
import com.demo.DrivingLicense.Service.LicenseNumberService;

//Only admin access

@RestController
public class LicenseNumberManager {

@Autowired
LicenseNumberService licensenumberservice;

@GetMapping("/drivingLicense/all")
public java.util.List<LicenseNumber> getDrivingLicenseNumberAll(@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return licensenumberservice.getLicenseNumberAllService(cookie);
}

@GetMapping("/drivingLicense/id")
public Optional<LicenseNumber> getDrivingLicenseNumberByUserId(@RequestParam(name="userId")int userId,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{

	return licensenumberservice.getLicenseNumberByUserIdService(userId,cookie);
	
}

@GetMapping("/drivingLicense/name")
public List<LicenseNumber> getLicenseNumberByUserName(@RequestParam(name="name")String name,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return licensenumberservice.getLicenseNumberByUserNameService(name,cookie);
}

@GetMapping("/drivingLicense/dob")
public List<LicenseNumber> getLicenseNumberByUserDOB(@RequestParam(name="userDOB")String userDOB,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return licensenumberservice.getLicenseNumberByUserDOBService(userDOB,cookie);
}

@GetMapping("/drivingLicense/dlnumber")
public Optional<LicenseNumber> getLicenseNumberByDOB(@RequestParam(name="DLNumber")String DLNumber,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return licensenumberservice.getLicenseNumberByLicenseNumberService(DLNumber,cookie);
}


}
