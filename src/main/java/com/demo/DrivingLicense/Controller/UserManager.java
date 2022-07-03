package com.demo.DrivingLicense.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.DrivingLicense.Entity.User;
import com.demo.DrivingLicense.Service.UserService;

@RestController
public class UserManager {
@Autowired
UserService userservice;

@GetMapping("/userDetails/{userId}")
public User getUserDetailsById(@PathVariable(name="userId")int userId,@CookieValue(name="cookieUser",defaultValue="hi")String cookieUser)
{
	return userservice.getUserDetailsByIdService(userId,cookieUser);
}

@PostMapping("/userDetails")
public String addUser(@RequestBody User user)
{
	return userservice.addUserDetailsService(user);
}

@PatchMapping("/userDetails")
public String updateUserDetails(@RequestBody User user,@CookieValue(name="cookieUser",defaultValue="hi")String cookieUser)
{
	return userservice.updateUserDetailsService(user, cookieUser);
}

@DeleteMapping(path="/userDetails/{userId}")//Only user access
public String deleteUserDetails(@PathVariable(name="userId")int userId,@CookieValue(name="cookieUser",defaultValue="hi")String cookieUser)
{
	return userservice.deleteUserDetailsService(userId,cookieUser);
}
}
