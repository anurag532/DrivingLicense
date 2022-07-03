package com.demo.DrivingLicense.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.DrivingLicense.Entity.LicenseNumber;

@Repository
public interface DLNumberRepository extends JpaRepository<LicenseNumber,Integer> {

@Query("select l from LicenseNumber l WHERE l.name LIKE :name")
List<LicenseNumber> findByName(@Param("name")String name);

@Query("select l from LicenseNumber l where l.userDOB LIKE :n")
List<LicenseNumber> findByDOB(@Param("n")String userDOB);

@Query("select l from LicenseNumber l where l.DLNumber =:n")
Optional<LicenseNumber> findByLicenseNumber(@Param("n")String DLNumber);
}
