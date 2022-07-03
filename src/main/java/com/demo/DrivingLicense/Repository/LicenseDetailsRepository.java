package com.demo.DrivingLicense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.DrivingLicense.Entity.LicenseDetails;

@Repository
public interface LicenseDetailsRepository extends JpaRepository<LicenseDetails, Integer> {

}
