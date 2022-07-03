package com.demo.DrivingLicense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.DrivingLicense.Entity.Admin;
import com.demo.DrivingLicense.Entity.LicenseDetails;
import com.demo.DrivingLicense.Entity.LicenseNumber;
import com.demo.DrivingLicense.Entity.User;
import com.demo.DrivingLicense.Repository.AdminRepository;
import com.demo.DrivingLicense.Repository.DLNumberRepository;
import com.demo.DrivingLicense.Repository.UserRepository;
import com.demo.DrivingLicense.Service.AdminService;
import com.demo.DrivingLicense.Service.LicenseDetailsService;
import com.demo.DrivingLicense.Service.LicenseNumberService;
import com.demo.DrivingLicense.Service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DrivingLicenseApplicationTests {

@Autowired
UserRepository userrepository;
@Autowired
AdminService adminservice;
@Autowired
UserService userservice;
@Autowired
LicenseDetailsService licensedetailsservice;
@Autowired
DLNumberRepository dlrepository;
@Autowired
LicenseNumberService licensenumberservice;
@Autowired
AdminRepository adminrepository;
static User user;
static Admin admin;
static LicenseDetails licensedetails;

@BeforeAll
static void instanceCreation()//Before All, creating instances of user,admin and licensedetails object
{
	user=new User();
	admin=new Admin();
	licensedetails=new LicenseDetails();
}

@Test
@Order(1)
void addUserDetailsServiceTest()//Adding user service
{
	
//User user=new User();
user.setUserId(106);
user.setName("Test");
user.setUserPassword("Test");
user.setUserDOB("11-08-1998");
user.setUserAddress("Delhi,India");
user.setVehicleType("2Wheeler");
user.setUserEmail("test@gmail.com");
assertFalse(userrepository.existsById(user.getUserId()));//Checking user existence before saving it in repository
userrepository.save(user);//Saving user
assertTrue(userrepository.existsById(user.getUserId()));//Checking user existence after saving it in repository
	}

@Test
@Order(2)
void getUserDetailsByIdServiceTest()//Getting user details service
{
	User actualUser=userservice.getUserDetailsByIdService(106, "4_TestTest");
	String actual=actualUser.toString();
	String expected=user.toString();
	assertEquals(expected,actual);
}

@Test
@Order(9)
public void deleteUserDetailsServiceTest()//Deleting user details service test
{
	String actual=userservice.deleteUserDetailsService(106,"4_TestTest");
	String expected="User data deleted successfully and application also deleted from database";
	assertEquals(expected,actual);
}

@Test
@Order(3)
void addAdminServiceTest()//Adding admin service
{
	String expected="Admin successfully added to the database";
	admin.setAdminId(403);
	admin.setName("testadmin");
	admin.setPassword("testadmin");
	admin.setEmail("testadmin@gmail.com");
	String actual=adminservice.addAdminService(admin, "6_AnuragAnurag");//Calling add admin service
	assertEquals(expected,actual);//Should be equal
	
}

@Test
@Order(8)
public void deleteAdminServiceTest()//Deleting admin service test
{
	String actual=adminservice.deleteAdminService(403,"9_testadmintestadmin");
	String expected="Admin deleted successfully from the database";
	assertEquals(expected,actual);
}

@Test 
@Order(10)
public void getAdminDetailsServiceTest()//Getting admin details for a admin that doesn't exist
{
	assertEquals(null,adminservice.getAdminDetailsByIdService(401,"6_AshishAshish"));
}

@Test
@Order(4)
void addLicenseDetailsTest()//Adding license details service test for user 106
{
licensedetails.setUserId(106);licensedetails.setTestResult("PASS");licensedetails.setAttemptNumber("FIRST");licensedetails.setPaymentStatus("PAID");
licensedetails.setAddressProof("SUBMITTED");licensedetails.setIdProof("SUBMITTED");licensedetails.setMedicalCertificate("SUBMITTED");
String actual=licensedetailsservice.addLicenseDetailsService(licensedetails,"6_AnuragAnurag");
LicenseNumber DLObj=dlrepository.findById(106).orElse(null);
String expected="CONGRATULATIONS!! Test has successfully completed all the required tasks and the Driving License has been issued.Below are the details:\n"+DLObj;
assertEquals(expected,actual);
}

@Test
@Order(5)
void deleteLicenseDetailsServiceTest()//Deleting license details for a user that doesn't exist
{
	String actual=licensedetailsservice.deleteLicenseDetailsService(107,"6_AnuragAnurag");
	String expected="Data for user id:107 doesn't exist. Kindly enter a valid user id";
	assertEquals(expected,actual);
	
}

@Test
@Order(6)
void getLicenseNumberByUserIdServiceTest()//Getting driving license number using userid
{
	LicenseNumber actualObj=licensenumberservice.getLicenseNumberByUserIdService(106,"6_AnuragAnurag").orElse(null);
	LicenseNumber expectedObj=dlrepository.findById(106).orElse(null);
	String actual=actualObj.toString();
	String expected=expectedObj.toString();
	assertEquals(expected,actual);

}

@Test
@Order(7)
public void getLicenseNumberByLicenseNumberServiceTest()//Getting driving license number using user name
{
	LicenseNumber actualObj=licensenumberservice.getLicenseNumberByLicenseNumberService("DL2021106","6_AnuragAnurag").orElse(null);
	LicenseNumber expectedObj=dlrepository.findByLicenseNumber("DL2021106").orElse(null);
	String actual=actualObj.toString();
	String expected=expectedObj.toString();
	assertEquals(expected,actual);
}

}
