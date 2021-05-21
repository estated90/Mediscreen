package web.mediscreen.historic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mediscreen.historic.exception.HistoryNotFoundException;
import web.mediscreen.historic.exception.PatientNotFoundException;
import web.mediscreen.historic.model.Historic;
import web.mediscreen.historic.proxy.PatientFeign;
import web.mediscreen.historic.repositories.HistoricRepositories;

/**
 * @author Nicolas
 *
 */
@Service
@Transactional
@ComponentScan({ "web.mediscreen.historic.proxy", "web.historic.mediscreen.repositories" })
public class HistoricService {

	private static final Logger logger = LogManager.getLogger(HistoricService.class);

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private PatientFeign patientFeign;
	@Autowired
	private HistoricRepositories historicRepository;

	/**
	 * @param id of the patient
	 * @return List of historic
	 * @throws HistoryNotFoundException Exception when no id found
	 */
	public List<Historic> getHistoryOfPatient(int id) throws HistoryNotFoundException {
		logger.info("Service to get patient history from DB");
		return historicRepository.findByPatientId(id);
	}

	/**
	 * @param patient as object
	 * @return List of historic
	 * @throws HistoryNotFoundException Exception when no id found
	 */
	public List<Historic> getHistoryOfPatient(String patient) throws HistoryNotFoundException {
		logger.info("Service to get patient history from DB");
		return historicRepository.findByName(patient);
	}

	/**
	 * @param historic as object
	 * @return the inserted historic
	 * @throws PatientNotFoundException Exception when no id found
	 */
	public Historic createNewHistoric(Historic historic) throws PatientNotFoundException {
		logger.info("Service to create patient history into DB : {}", historic.getPatient());
		if (patientFeign.getPatientById(historic.getPatId()) != null) {
			logger.info("History inserted");
			historic.setId(sequenceGeneratorService.generateSequence(Historic.SEQUENCE_NAME));
			historic.setCreatedAt(LocalDateTime.now());
			return historicRepository.insert(historic);
		} else {
			logger.error("This patient do not exist");
			throw new PatientNotFoundException("This patient do not exist. Enable to attach historic");
		}
	}

	/**
	 * @param historic as object
	 * @return the updated historic
	 * @throws HistoryNotFoundException Exception when no id found
	 */
	public Historic updateHistoric(Historic historic) throws HistoryNotFoundException {
		logger.info("Service to update patient history into DB : {}", historic.getPatient());
		Optional<Historic> historicRetrieved = historicRepository.findById(historic.getId());
		var patient = patientFeign.getPatientById(historic.getPatId());
		if (historicRetrieved.isPresent() && patient != null) {
			logger.info("history is being updated");
			historic.setModifiedAt(LocalDateTime.now());
			historicRepository.save(historic);
			logger.info("Update successful");
			return historic;
		} else {
			logger.error("The historic of the patient was not found");
			throw new HistoryNotFoundException("No history has been found with id " + historic.getId());
		}
	}

	/**
	 * @param id Id as integer
	 * @return The historic found in db
	 * @throws HistoryNotFoundException Exception when no id found
	 */
	public Optional<Historic> getHistoryId(int id) throws HistoryNotFoundException {
		logger.info("Service to get history from DB");
		Optional<Historic> historic = historicRepository.findById(id);
		if (historic.isPresent()) {
			return historic;
		} else {
			logger.error("No occurance were found in DB for history {}", id);
			throw new HistoryNotFoundException("No history has been found for id " + id);
		}
	}

}
