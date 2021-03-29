package web.mediscreen.personalInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mediscreen.personalInfo.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
