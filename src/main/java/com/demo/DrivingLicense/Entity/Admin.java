package com.demo.DrivingLicense.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN")
public class Admin {
@Id
@Column(name="ADMIN_ID")
int adminId; 
@Column(name="ADMIN_NAME")
String name;
@Column(name="ADMIN_PASSWORD")
String password;
@Column(name="ADMIN_EMAIL")
String email;
public int getAdminId() {
	return adminId;
}
public void setAdminId(int adminId) {
	this.adminId = adminId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
