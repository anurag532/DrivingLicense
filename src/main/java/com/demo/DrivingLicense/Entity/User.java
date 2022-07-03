package com.demo.DrivingLicense.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
@Id
@Column(name="USER_ID")
int userId;
@Column(name="USER_NAME")
String name;
@Column(name="USER_PASSWORD")
String userPassword;
@Column(name="USER_EMAIL")
String userEmail;
@Column(name="USER_ADDRESS")
String userAddress;
@Column(name="USER_DOB")
String userDOB;//DD-MM-YYYY
@Column(name="VEHICLE_TYPE")
String vehicleType;//2Wheeler, 4Wheeler
@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name="userId",referencedColumnName="userId")
LicenseNumber licenseNumber;
@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name="userId",referencedColumnName="userId")
LicenseDetails applicationStatus;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUserPassword() {
	return userPassword;
}
public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getUserAddress() {
	return userAddress;
}
public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}
public String getUserDOB() {
	return userDOB;
}
public void setUserDOB(String userDOB) {
	this.userDOB = userDOB;
}
public String getVehicleType() {
	return vehicleType;
}
public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}
public LicenseNumber getLicenseNumber() {
	return licenseNumber;
}
public void setLicenseNumber(LicenseNumber licenseNumber) {
	this.licenseNumber = licenseNumber;
}
public LicenseDetails getApplicationStatus() {
	return applicationStatus;
}
public void setApplicationStatus(LicenseDetails applicationStatus) {
	this.applicationStatus = applicationStatus;
}

@Override
public String toString() {
	return "User [userId=" + userId + ", name=" + name + ", userPassword=" + userPassword + ", userEmail=" + userEmail
			+ ", userAddress=" + userAddress + ", userDOB=" + userDOB + ", vehicleType=" + vehicleType
			+ ", licenseNumber=" + licenseNumber + ", applicationStatus=" + applicationStatus + "]";
}

}
