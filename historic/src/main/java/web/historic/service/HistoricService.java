package web.historic.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import web.historic.exception.HistoryNotFoundException;
import web.historic.exception.PatientNotFoundException;
import web.historic.model.Historic;
import web.historic.proxy.PatientFeign;
import web.historic.repositories.HistoricRepositories;
import web.sharedobject.model.Patient;

@Service
@ComponentScan({"web.historic.proxy", "web.historic.repositories"})
public class HistoricService {

    private static final Logger logger = LogManager.getLogger(HistoricService.class);

    @Autowired
    private PatientFeign patientFeign;
    @Autowired
    private HistoricRepositories historicRepository;

    public List<Historic> getHistoryOfPatient(int id) throws HistoryNotFoundException {
	logger.info("Service to get patient history from DB");
	if (historicRepository.findByPatientId(id).size()>=1) {
	   return historicRepository.findByPatientId(id);
	} else {
	    logger.error("No occurance were found in DB for user {}", id);
	    throw new HistoryNotFoundException("No history has been found for patient "+id);
	}
    }

    public void createNewHistoric(Historic historic) throws PatientNotFoundException {
	logger.info("Service to create patient history into DB : {}", historic.getPatient());
	if(patientFeign.getPatientById(historic.getPatient())!=null) {
	    logger.info("History inserted");
	    historicRepository.insert(historic);
	} else {
	    logger.error("This patient do not exist");
	    throw new PatientNotFoundException("This patient do not exist. Enable to attach historic");
	}
	historicRepository.insert(historic);
    }

}
