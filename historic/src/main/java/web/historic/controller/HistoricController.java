package web.historic.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.historic.dto.HistoricDto;
import web.historic.exception.HistoryNotFoundException;
import web.historic.exception.PatientNotFoundException;
import web.historic.model.Historic;
import web.historic.service.HistoricService;
import web.historic.utils.HistoricUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricController {

    private static final Logger logger = LogManager.getLogger(HistoricController.class);
    @Autowired
    private HistoricService historicService;
    
    @GetMapping(value = "/historic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Historic> getAllHistoryPatient(@PathVariable int id) throws HistoryNotFoundException{
	logger.info("Getting the history for patient {}", id);
	return historicService.getHistoryOfPatient(id);
    }
    
    @PutMapping(value="/historic/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoricDto> pustHistoric(@RequestBody @Valid HistoricDto historicDto) throws PatientNotFoundException{
	logger.info("Creating the history for patient {}", historicDto.getPatient());
	historicService.createNewHistoric(HistoricUtils.convertDtoToHistoric(historicDto));
	return null;
	
    }
}
