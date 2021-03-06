package web.mediscreen.historic.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.mediscreen.historic.dto.HistoricDto;
import web.mediscreen.historic.exception.HistoryNotFoundException;
import web.mediscreen.historic.exception.PatientNotFoundException;
import web.mediscreen.historic.model.Historic;
import web.mediscreen.historic.service.HistoricService;
import web.mediscreen.historic.utils.HistoricUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricController {

	private static final Logger logger = LogManager.getLogger(HistoricController.class);
	@Autowired
	private HistoricService historicService;

	@GetMapping(value = "/historic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Historic>> getAllHistoryPatient(@PathVariable int id) throws HistoryNotFoundException {
		logger.info("Getting the history for patient {}", id);
		return new ResponseEntity<>(historicService.getHistoryOfPatient(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/historic/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Historic>> getHistoryIdPatient(@PathVariable int id) throws HistoryNotFoundException {
		logger.info("Getting the history id : {}", id);
		return new ResponseEntity<>(historicService.getHistoryId(id), HttpStatus.OK);
	}

	@PutMapping(value = "/historic/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historic> pustHistoric(@RequestBody @Valid HistoricDto historicDto)
			throws PatientNotFoundException {
		var historic = HistoricUtils.convertDtoToHistoric(historicDto);
		logger.info("Creating the history for patient {}", historic.getPatient());
		var historicReturned = historicService.createNewHistoric(HistoricUtils.convertDtoToHistoric(historicDto));
		return new ResponseEntity<>(historicReturned, HttpStatus.CREATED);
	}

	@PostMapping(value = "/historic/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historic> postHistoric(@RequestBody @Valid HistoricDto historicDto)
			throws HistoryNotFoundException {
		var historic = HistoricUtils.convertDtoToHistoric(historicDto);
		logger.info("Updating the history for patient {}", historic.getPatient());
		var historicReturned = historicService.updateHistoric(historic);
		return new ResponseEntity<>(historicReturned, HttpStatus.OK);
	}
	
	@GetMapping(value = "/historic", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Historic>> getAllHistoryPatient(@RequestParam String patient) throws HistoryNotFoundException {
		logger.info("Getting the history for patient {}", patient);
		return new ResponseEntity<>(historicService.getHistoryOfPatient(patient), HttpStatus.OK);
	}
}
