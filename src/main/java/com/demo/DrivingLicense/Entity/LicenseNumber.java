package com.demo.DrivingLicense.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="LICENSE_NUMBER")
public class LicenseNumber {
@Id
@Column(name="USER_ID")
int userId;
@Column(name="USER_NAME")
String name;
@Column(name="USER_DOB")
String userDOB;
@Column(name="DL_NUMBER")
String DLNumber;
@Column(name="VEHICLE_TYPE")
String vehicleType;
@Column(name="COST")
int cost;
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
public String getDLNumber() {
	return DLNumber;
}
public String getUserDOB() {
	return userDOB;
}
public void setUserDOB(String userDOB) {
	this.userDOB = userDOB;
}
public void setDLNumber(String dLNumber) {
	DLNumber = dLNumber;
}
public String getVehicleType() {
	return vehicleType;
}
public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
@Override
public String toString() {
	return "<<<<< USER ID = " + userId + " >>>>>\n<<<<< NAME = " + name + " >>>>>\n<<<<<<USER DOB = "+userDOB+">>>>>>\n<<<<< DL NUBMER = " + DLNumber 
			+ " >>>>>\n<<<<< VEHICLE TYPE = "+vehicleType+" >>>>>\n<<<<< COST = "+cost+" >>>>>";
}

}
