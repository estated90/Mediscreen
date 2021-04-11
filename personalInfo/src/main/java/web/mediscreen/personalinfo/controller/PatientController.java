package web.mediscreen.personalinfo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.mediscreen.personalinfo.model.Patient;
import web.mediscreen.personalinfo.service.PatientService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    
    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Patient> getPatientById(@PathVariable int id) throws Exception {
	logger.info("Getting patient with id {}", id);
	Optional<Patient> patients = patientService.getPatientById(id);
	return patients;
    }

    @PutMapping(path = "/patient/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putPatient(@RequestBody @Valid Patient patient) throws Exception {
	logger.info("Creating new patient : {}", patient);
	patientService.addNewPatient(patient);
	return ResponseEntity.created(null).build();

    }
    
    @PostMapping(path = "/patient/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postPatient(@RequestBody @Valid Patient patient) throws Exception {
	logger.info("Updating patient : {}", patient.getId());
	patientService.updatingPatient(patient);
	return ResponseEntity.ok("Patient was updated");

    }
}
