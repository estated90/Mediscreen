package web.mediscreen.personalInfo.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import web.mediscreen.personalInfo.exception.DateParsingException;
import web.mediscreen.personalInfo.exception.PatientException;
import web.mediscreen.personalInfo.model.Patient;
import web.mediscreen.personalInfo.service.PatientService;
import web.mediscreen.personalInfo.utils.UtilsApp;

@RestController
public class PatientController {

	private static final Logger logger = LogManager.getLogger(PatientController.class);
	@Autowired
	private PatientService patientService;

	@GetMapping(value = "/patient/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Patient> getAllPatient() throws Exception {
		logger.info("Getting all patient lists");
		List<Patient> patients = patientService.gettingAllPatient();
		return patients;
	}

	@PostMapping(path = "/patient/add", consumes = "application/x-www-form-urlencoded")
	public ResponseEntity<String> putPatient(String family, String given, String dob, String sex,
			String address, String phone) throws PatientException, DateParsingException {
		LocalDate birthDate = UtilsApp.stringToLocalDate(dob);
		Patient patient = new Patient(family, given, birthDate, sex, address, phone);
		logger.info("Creating new patient : {}", patient);
		patientService.addNewPatient(patient);
		return ResponseEntity.ok("Patient has been added");

	}
}
