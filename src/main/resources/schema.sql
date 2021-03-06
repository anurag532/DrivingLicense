CREATE TABLE USER(
USER_ID INT,
USER_NAME VARCHAR(40),
USER_PASSWORD VARCHAR(20),
USER_EMAIL VARCHAR(40),
USER_ADDRESS VARCHAR(50),
USER_DOB VARCHAR(40), 
VEHICLE_TYPE VARCHAR(15));

CREATE TABLE ADMIN(
ADMIN_ID INT,
ADMIN_NAME VARCHAR(40),
ADMIN_PASSWORD VARCHAR(20),
ADMIN_EMAIL VARCHAR(40));

CREATE TABLE LICENSE_DETAILS(
USER_ID INT,
ID_PROOF VARCHAR(20),
ADDRESS_PROOF VARCHAR(20),
MEDICAL_CERTIFICATE VARCHAR(20),
TEST_RESULT VARCHAR(20),
PAYMENT_STATUS VARCHAR(20),
ATTEMPT_NUMBER VARCHAR(15),
COST INT);

CREATE TABLE LICENSE_NUMBER(
USER_ID INT,
USER_NAME VARCHAR(40),
USER_DOB VARCHAR(30),
DL_NUMBER VARCHAR(40),
VEHICLE_TYPE VARCHAR(15),
COST INT);




