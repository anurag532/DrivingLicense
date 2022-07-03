package com.demo.DrivingLicense.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Service.AdminService;

@RestController
public class AdminManager {

AdminService adminservice;
Authentication authentication;

@Autowired
public AdminManager(AdminService adminservice, Authentication authentication) {
	super();
	this.adminservice = adminservice;
	this.authentication = authentication;
}

@GetMapping("/adminDetails/{adminId}")// Admin can check only his data. One admin cannot check other admin's data
public Admin getAdminDetailsById(@PathVariable(name="adminId") int adminId,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return adminservice.getAdminDetailsByIdService(adminId,cookie);
}

@PostMapping("/adminDetails")//One admin can add another admin
public String addAdmin(@RequestBody Admin admin,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return adminservice.addAdminService(admin,cookie);
}

@DeleteMapping("/adminDetails/{adminId}")//Admin can check only his data. One admin cannot check other admin's data
public String deleteAdmin(@PathVariable(name="adminId") int adminId,@CookieValue(name="cookie",defaultValue="hi")String cookie)
{
	return adminservice.deleteAdminService(adminId,cookie);
}

}
