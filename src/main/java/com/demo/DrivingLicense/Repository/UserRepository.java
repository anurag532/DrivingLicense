package com.demo.DrivingLicense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.DrivingLicense.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

User findByName(String name);
}
