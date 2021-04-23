package web.mediscreen.personalInfo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.mediscreen.personalInfo.dto.PatientDto;
import web.mediscreen.personalInfo.exception.DbSaveException;
import web.mediscreen.personalInfo.exception.PatientExistException;
import web.mediscreen.personalInfo.exception.PatientNoExistException;
import web.mediscreen.personalInfo.model.Patient;
import web.mediscreen.personalInfo.service.PatientService;
import web.mediscreen.personalInfo.utils.PatientUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientUtils utils;

    @GetMapping(value = "/patient/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getAllPatient() {
	logger.info("Getting all patient lists");
	return patientService.gettingAllPatient();
    }

    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Patient> getPatientById(@PathVariable int id) throws PatientNoExistException {
	logger.info("Getting patient with id {}", id);
	return patientService.getPatientById(id);
    }
    @PutMapping(path = "/patient/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> putPatient(@RequestBody @Valid PatientDto patient) throws DbSaveException, PatientExistException {
	logger.info("Creating new patient : {}", patient);
	patientService.addNewPatient(utils.convertDtoToPatient(patient));
	return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(path = "/patient/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postPatient(@RequestBody @Valid PatientDto patient) throws PatientNoExistException {
	logger.info("Updating patient : {}", patient.getId());
	patientService.updatingPatient(utils.convertDtoToPatient(patient));
	return new ResponseEntity<>(HttpStatus.OK);

    }
}