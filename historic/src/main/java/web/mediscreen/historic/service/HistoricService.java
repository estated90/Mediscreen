package web.mediscreen.historic.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import web.mediscreen.historic.model.Historic;
import web.mediscreen.historic.repositories.HistoricRepositories;

@Service
public class HistoricService {

    private static final Logger logger = LogManager.getLogger(HistoricService.class);

    private final HistoricRepositories historicRepository;

    public HistoricService(HistoricRepositories historicRepository) {
	this.historicRepository = historicRepository;
    }

    public List<Historic> getHistoryOfPatient(int id) {
	logger.info("Service to get patient hgistyory from DB");
	historicRepository.findByPatientId(id);
	return null;
    }

}
