package web.historic.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import web.historic.exception.HistoryNotFoundException;
import web.historic.exception.PatientNotFoundException;
import web.historic.model.Historic;
import web.historic.model.Patient;
import web.historic.proxy.PatientFeign;
import web.historic.repositories.HistoricRepositories;

@Service
@ComponentScan({"web.historic.proxy", "web.historic.repositories"})
public class HistoricService {

    private static final Logger logger = LogManager.getLogger(HistoricService.class);

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private PatientFeign patientFeign;
    @Autowired
    private HistoricRepositories historicRepository;

    public List<Historic> getHistoryOfPatient(int id) throws HistoryNotFoundException {
	logger.info("Service to get patient history from DB");
	List<Historic> historic = historicRepository.findByPatientId(id);
	if (historic.size()>=1) {
	   return historic;
	} else {
	    logger.error("No occurance were found in DB for user {}", id);
	    throw new HistoryNotFoundException("No history has been found for patient "+id);
	}
    }

    public Historic createNewHistoric(Historic historic) throws PatientNotFoundException {
	logger.info("Service to create patient history into DB : {}", historic.getPatient());
	if(patientFeign.getPatientById(historic.getPatId())!=null) {
	    logger.info("History inserted");
	    historic.setId(sequenceGeneratorService.generateSequence(Historic.SEQUENCE_NAME));
	    return historicRepository.insert(historic);
	} else {
	    logger.error("This patient do not exist");
	    throw new PatientNotFoundException("This patient do not exist. Enable to attach historic");
	}
    }

    public Historic updateistoric(Historic historic) throws HistoryNotFoundException {
	logger.info("Service to update patient history into DB : {}", historic.getPatient());
	Optional<Historic> historicRetrieved = historicRepository.findById(historic.getId());
	Patient patient = patientFeign.getPatientById(historic.getPatId());
	if (historicRetrieved.isPresent() & patient!=null) {
	    logger.info("history is being updated");
	    historicRepository.save(historic);
	    logger.info("Update successful");
	    return historic;
	} else {
	    logger.error("The historic of the patient was not found");
	    throw new HistoryNotFoundException("No history has been found with id " + historic.getId());
	}
    }

}
