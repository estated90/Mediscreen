package web.mediscreen.diabetalert.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import web.mediscreen.diabetalert.constants.Terms;
import web.mediscreen.diabetalert.model.Historic;
import web.mediscreen.diabetalert.model.Patient;
import web.mediscreen.diabetalert.proxy.HistoryFeign;
import web.mediscreen.diabetalert.proxy.PatientFeign;
import web.mediscreen.diabetalert.utils.DiabetUtils;

@Service
@ComponentScan({ "web.mediscreen.diabetalert.proxy" })
public class DiabetService {

	private static final Logger logger = LogManager.getLogger(DiabetService.class);
	@Autowired
	private HistoryFeign historicFeign;
	@Autowired
	private PatientFeign patientFeign;
	@Autowired
	private AgeCalculator ageCalculator;
	private int index = 0;

	public String assessDiabetId(int id) {
		logger.info("Getting diabet alert for patient {}", id);
		int patId = id;
		List<Historic> historic = historicFeign.getHistoricPatientById(patId);
		logger.info("Historic retrieved successfully");
		int risks = evaluateRisk(historic);
		return getRules(patId, risks);
	}

	public String assessDiabetString(String familyName) {
		logger.info("Getting diabet alert for patient {}", familyName);
		Optional<List<Historic>> historicOp = Optional.ofNullable(historicFeign.getHistoryWithName(familyName));
		List<Historic> historic = null;
		var patId = historicOp.filter(list -> !list.isEmpty()).map(list -> list.get(0)).map(Historic::getPatId)
				.orElse(0);
		if (historicOp.isPresent()) {
			historic = historicOp.get();
		}
		logger.info("patient id retrieved is {}", patId);
		int risks = evaluateRisk(historic);
		return getRules(patId, risks);
	}

	private int evaluateRisk(List<Historic> historics) {
		logger.info("Calculate the risk with word occurance");
		index = 0;
		for (Historic historic : historics) {
			Terms.TERMS.forEach(term -> {
				Pattern p = Pattern.compile(term.toLowerCase());
				Matcher m = p.matcher(
						DiabetUtils.removeBadCharacters(historic.getPractitionnerNotesRecommandation().toLowerCase()));
				while (m.find()) {
					index++;
				}
			});
			logger.info("Comments have been browsed");
		}
		logger.info("{} risks were found", index);
		return index;
	}

	private String getRules(int patId, int risks) {
		logger.info("getting the rule for patient");
		Patient patient = patientFeign.getPatientById(patId);
		logger.info("Patient retrieved successfully");
		LocalDate birthdate = patient.getDob();
		String sex = patient.getSex();
		logger.info("Calculating age");
		int age = ageCalculator.ageCalculation(birthdate);
		String assessment = null;
		if (age <= 30) {
			switch (sex) {
			case "M":
				if (risks > 2 && risks <= 4) {
					assessment = "In Danger";
				} else if (risks >= 5) {
					assessment = "Early onset";
				} else {
					assessment = "None";
				}
				break;
			case "F":
				if (risks > 4 && risks <= 6) {
					assessment = "In Danger";
				} else if (risks >= 7) {
					assessment = "Early onset";
				} else {
					assessment = "None";
				}
				break;
			default:
				throw new IllegalArgumentException("Unkown sex");
			}
		} else {
			if (risks >= 2) {
				assessment = "Borderline";
			} else if (risks >= 6 && risks < 8) {
				assessment = "In Danger";
			} else if (risks >= 8) {
				assessment = "Early onset";
			} else {
				assessment = "None";
			}
		}
		logger.info("Assessment retrieved {}", assessment);
		return "Patient: " + patient.getFamily() + " " + patient.getGiven() + " (" + age + ") diabetes assessment is: "
				+ assessment;
	}
}
