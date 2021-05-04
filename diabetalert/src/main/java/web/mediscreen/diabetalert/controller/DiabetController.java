package web.mediscreen.diabetalert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.mediscreen.diabetalert.service.DiabetService;
import web.mediscreen.diabetalert.utils.DiabetUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DiabetController {

	private static final Logger logger = LogManager.getLogger(DiabetController.class);
	@Autowired
	private DiabetService diabetService;
	
	@PostMapping(value = "/assess", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postAssessDiabetFamily(@RequestParam String familyName) {
		logger.info("Getting the diabet alert for patient");
		return new ResponseEntity<>(diabetService.assessDiabetString(DiabetUtils.removeBadCharacters(familyName)), HttpStatus.OK);
	}
	
	@PostMapping(value = "/assess/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postAssessDiabetId(@PathVariable int id) {
		logger.info("Getting the diabet alert for patient {}", id);
		return new ResponseEntity<>(diabetService.assessDiabetId(id), HttpStatus.OK);
	}
	
}
