package web.mediscreen.personalInfo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import web.mediscreen.personalInfo.service.PatientService;

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
	
	
}
