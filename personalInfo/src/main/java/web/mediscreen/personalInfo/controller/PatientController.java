package web.mediscreen.personalInfo.controller;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.mediscreen.personalInfo.exception.DateParsingException;
import web.mediscreen.personalInfo.exception.PatientException;
import web.mediscreen.personalInfo.model.Patient;
import web.mediscreen.personalInfo.service.PatientService;
import web.mediscreen.personalInfo.utils.UtilsApp;

@Controller
public class PatientController {

	private static final Logger logger = LogManager.getLogger(PatientController.class);
	private static final String PATIENTS = "patients";
	@Autowired
	private PatientService patientService;

	@GetMapping("/patient/list")
	public String getAllPatient(Model model) throws Exception {
		logger.info("Getting all patient lists");
		model.addAttribute(PATIENTS, patientService.gettingAllPatient());
		return "/patient/list";
	}

	@PostMapping(path = "/patient/add", consumes = "application/x-www-form-urlencoded")
	public void putPatient(Model model, @RequestParam String family, @RequestParam String given,
			@RequestParam String dob, @RequestParam String sex, @RequestParam String address,
			@RequestParam String phone) throws PatientException, DateParsingException {
		LocalDate birthDate = UtilsApp.stringToLocalDate(dob);
		Patient patient = new Patient(family, given, birthDate, sex, address, phone);
		logger.info("Creating new patient : {}", patient);
		if (patientService.addNewPatient(patient)) {
			model.addAttribute(PATIENTS, patientService.addNewPatient(patient));
		} else {
			logger.error("Patient was not added correctly");
			throw new PatientException("Error while adding patient");
		}
	}
}
