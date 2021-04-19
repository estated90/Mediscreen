package web.mediscreen.personalInfo.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import web.mediscreen.personalInfo.exception.DbSaveException;
import web.mediscreen.personalInfo.exception.PatientExistException;
import web.mediscreen.personalInfo.exception.PatientNoExistException;
import web.mediscreen.personalInfo.model.Patient;
import web.mediscreen.personalInfo.repositories.PatientRepository;

@Service
@Transactional
public class PatientService {

    private static final Logger logger = LogManager.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> gettingAllPatient() {
	logger.info("Getting all users from DB");
	return patientRepository.findAll();
    }

    public Patient addNewPatient(Patient patient) throws DbSaveException, PatientExistException {
	if (checkDoubleDb(patient)) {
	    try {
		logger.info("Adding new patient in services");
		patientRepository.save(patient);
		return patient;
	    }  catch (DataIntegrityViolationException e) {
		logger.error("Data was too long");
		throw new DbSaveException("Data of the phone was too long");
	    } catch (UnexpectedRollbackException ex) {
		logger.error("Saving in db failed");
		throw new DbSaveException("Saving in db failed");
	    }
	} else {
	    logger.error("The patient was already in DB");
	    throw new PatientExistException("This patient already exist in db");
	}
    }

    public boolean checkDoubleDb(Patient patient) {
	logger.info("checking if patient {} {} and {} already in db", patient.getFamily(), patient.getGiven(),
		patient.getDob());
	return patientRepository.findDouble(patient.getFamily(), patient.getGiven(), patient.getDob()) == null;
    }
    
    public boolean checkDoubleDb(int id) {
	logger.info("checking if patient {} already in db", id);
	Optional<Patient> patient = patientRepository.findById(id);
	return patient.isPresent();
    }

    public Optional<Patient> getPatientById(int id) throws PatientNoExistException {
	logger.info("Service to return patient with id : {}", id);
	if(checkDoubleDb(id) && id!=0){
	    logger.info("Returning patient with id {}", id);
	    return patientRepository.findById(id);
	} else {
	    logger.error("The patient was not found in DB");
	    throw new PatientNoExistException("This patient do not exist in db");
	}
    }
    
    public Patient updatingPatient(Patient patient) throws PatientNoExistException {
	logger.info("Updating patient with id : {}", patient.getId());
	if(checkDoubleDb((patient.getId())) && (patient.getId()!=0)){
	    return patientRepository.save(patient);
	} else {
	    logger.error("The patient was not found in DB");
	    throw new PatientNoExistException("This patient do not exist in db");
	}
    }

}
