package web.mediscreen.diabetalert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import web.mediscreen.diabetalert.service.DiabetService;
import web.mediscreen.diabetalert.utils.DiabetUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Resource REST Endpoint to assess risks")
public class DiabetController {

	private static final Logger logger = LogManager.getLogger(DiabetController.class);
	@Autowired
	private DiabetService diabetService;

	@GetMapping(value = "/assess", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> postAssessDiabetFamily(@RequestParam String familyName) {
		logger.info("Getting the diabet alert for patient");
		if (familyName != null) {
			String familyNamed = DiabetUtils.removeBadCharacters(familyName);
			return new ResponseEntity<>(diabetService.assessDiabetString(familyNamed),
					HttpStatus.OK);
		} else {
			logger.error("Parameter was null");
			throw new NullPointerException("should not be null");
		}
	}

	@GetMapping(value = "/assess/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> postAssessDiabetId(@PathVariable int id) {
		logger.info("Getting the diabet alert for patient {}", id);
		return new ResponseEntity<>(diabetService.assessDiabetId(id), HttpStatus.OK);
	}

}
