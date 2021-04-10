package web.mediscreen.personalInfo.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import web.mediscreen.personalInfo.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("SELECT p FROM Patient p WHERE p.family = :family AND p.given=:given AND p.dob=:dob")
	Patient findDouble(@Param("family")String family, @Param("given") String given, @Param("dob") LocalDate dob);
    
}
