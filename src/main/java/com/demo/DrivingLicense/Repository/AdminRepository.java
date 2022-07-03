package com.demo.DrivingLicense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.DrivingLicense.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
Admin findByName(String name);
}
