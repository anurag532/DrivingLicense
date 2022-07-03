package com.demo.DrivingLicense.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LICENSE_DETAILS")
public class LicenseDetails {
//For boolean values, true means pass or all good, fail means not passed or missing
@Id
@Column(name="USER_ID")
int userId;
@Column(name="ID_PROOF")
String idProof="";//SUBMITTED or NOT SUBMITTED
@Column(name="ADDRESS_PROOF")
String addressProof="";//SUBMITTED or NOT SUBMITTED
@Column(name="MEDICAL_CERTIFICATE")
String medicalCertificate="";//SUBMITTED or NOT SUBMITTED
@Column(name="TEST_RESULT")
String testResult="";//PASS or FAIL
@Column(name="PAYMENT_STATUS")
String paymentStatus="";// PAID NOT PAID
@Column(name="ATTEMPT_NUMBER")//FIRST, SECOND, THIRD
String attemptNumber="";
@Column(name="COST")
int cost;

public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getIdProof() {
	return idProof;
}
public void setIdProof(String idProof) {
	this.idProof = idProof;
}
public String getAddressProof() {
	return addressProof;
}
public void setAddressProof(String addressProof) {
	this.addressProof = addressProof;
}
public String getMedicalCertificate() {
	return medicalCertificate;
}
public void setMedicalCertificate(String medicalCertificate) {
	this.medicalCertificate = medicalCertificate;
}
public String getTestResult() {
	return testResult;
}
public void setTestResult(String testResult) {
	this.testResult = testResult;
}
public String getPaymentStatus() {
	return paymentStatus;
}
public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}
public String getAttemptNumber() {
	return attemptNumber;
}
public void setAttemptNumber(String attemptNumber) {
	this.attemptNumber = attemptNumber;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}

}
