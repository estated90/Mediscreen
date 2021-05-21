package web.mediscreen.personalinfo.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import web.mediscreen.personalinfo.exception.DbSaveException;
import web.mediscreen.personalinfo.exception.PatientExistException;
import web.mediscreen.personalinfo.exception.PatientNoExistException;
import web.mediscreen.personalinfo.model.Patient;
import web.mediscreen.personalinfo.repositories.PatientRepository;

/**
 * @author Nicolas
 * <p> Service to manage the services with the DB</p>
 *
 */
@Service
@Transactional
public class PatientService {

	private static final Logger logger = LogManager.getLogger(PatientService.class);
	@Autowired
	private PatientRepository patientRepository;

	/**
	 * @return List of all patient
	 */
	public List<Patient> gettingAllPatient() {
		logger.info("Getting all users from DB");
		return patientRepository.findAll();
	}

	/**
	 * @param patient Receive a patient Object
	 * @return A patient object
	 * @throws DbSaveException Exception raised if not saved correctly in DB
	 * @throws PatientExistException Exception when the patient is not in DB
	 */
	public Patient addNewPatient(Patient patient) throws DbSaveException, PatientExistException {
		if (checkDoubleDb(patient)) {
			try {
				logger.info("Adding new patient in services");
				patientRepository.save(patient);
				return patient;
			} catch (DataIntegrityViolationException e) {
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

	/**
	 * @param patient Receive a patient Object
	 * @return Boolean if patient is in DB
	 */
	private boolean checkDoubleDb(Patient patient) {
		logger.info("checking if patient {} {} and {} already in db", patient.getFamily(), patient.getGiven(),
				patient.getDob());
		return patientRepository.findDouble(patient.getFamily(), patient.getGiven(), patient.getDob()) == null;
	}

	/**
	 * @param id of the patient
	 * @return Boolean if patient is in DB
	 */
	private boolean checkDoubleDb(int id) {
		logger.info("checking if patient {} already in db", id);
		Optional<Patient> patient = patientRepository.findById(id);
		return patient.isPresent();
	}

	/**
	 * @param id of the patient
	 * @return A patient informations
	 * @throws PatientNoExistException Exception when the patient is not in DB
	 */
	public Optional<Patient> getPatientById(int id) throws PatientNoExistException {
		logger.info("Service to return patient with id : {}", id);
		if (checkDoubleDb(id) && id != 0) {
			logger.info("Returning patient with id {}", id);
			return patientRepository.findById(id);
		} else {
			logger.error("The patient was not found in DB");
			throw new PatientNoExistException("This patient do not exist in db");
		}
	}

	/**
	 * @param patient Receive a patient Object
	 * @return A patient informations
	 * @throws PatientNoExistException Exception when the patient is not in DB
	 */
	public Patient updatingPatient(Patient patient) throws PatientNoExistException {
		logger.info("Updating patient with id : {}", patient.getId());
		if (checkDoubleDb((patient.getId())) && (patient.getId() != 0)) {
			return patientRepository.save(patient);
		} else {
			logger.error("The patient was not found in DB");
			throw new PatientNoExistException("This patient do not exist in db");
		}
	}

}
