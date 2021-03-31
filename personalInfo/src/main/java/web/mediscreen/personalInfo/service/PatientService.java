package web.mediscreen.personalInfo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mediscreen.personalInfo.model.Patient;
import web.mediscreen.personalInfo.repositories.PatientRepository;

@Service
public class PatientService {

	private static final Logger logger = LogManager.getLogger(PatientService.class);
	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> gettingAllPatient() throws Exception {
		logger.info("Getting all users from DB");
		List<Patient> patients = null;
		try {
			patients = patientRepository.findAll();
		}catch(Exception ex) {
			logger.error("error while retrieving all users : {}", ex.getMessage());
			throw new Exception("error while retrieving patients");
		}
		return patients;
	}

	public boolean addNewPatient(Patient patient) {
		logger.info("Adding new patient in services");
		patientRepository.save(patient);
		return true;
	}

}
