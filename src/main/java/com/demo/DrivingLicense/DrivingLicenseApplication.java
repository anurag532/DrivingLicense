package com.demo.DrivingLicense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DrivingLicenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivingLicenseApplication.class, args);
	}

}
