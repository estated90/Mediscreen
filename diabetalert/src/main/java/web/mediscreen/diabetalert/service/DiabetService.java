package web.mediscreen.diabetalert.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import web.mediscreen.diabetalert.constants.Terms;
import web.mediscreen.diabetalert.model.Historic;
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
		var risks = 0;
		for (Historic historic : historics) {
			for( String term: Terms.getTerms()) {
				var p = Pattern.compile(term.toLowerCase());
				var m = p.matcher(
						DiabetUtils.removeBadCharacters(historic.getPractitionnerNotesRecommandation().toLowerCase()));
				while (m.find()) {
					risks++;
				}
			}
			logger.info("Comments have been browsed");
		}
		logger.info("{} risks were found", risks);
		return risks;
	}

	private String getRules(int patId, int risks) {
		logger.info("getting the rule for patient");
		var patient = patientFeign.getPatientById(patId);
		logger.info("Patient retrieved successfully");
		LocalDate birthdate = patient.getDob();
		String sex = patient.getSex();
		logger.info("Calculating age");
		int age = ageCalculator.ageCalculation(birthdate);
		String assessment = null;
		if (age <= 30) {
			switch (sex) {
			case "M":
				assessment = maleUnderThirty(risks);
				break;
			case "F":
				assessment = femaleUnderThirty(risks);
				break;
			default:
				throw new IllegalArgumentException("Unkown sex");
			}
		} else {
			assessment = patientOverThirty(risks);
		}
		logger.info("Assessment retrieved {}", assessment);
		return "Patient: " + patient.getFamily() + " " + patient.getGiven() + " (" + age + ") diabetes assessment is: "
				+ assessment;
	}

	//Code for logic from business.
	private static final String DANGER = "In Danger";
	private static final String EARLYONSET = "Early onset";
	private static final String NONE = "None";
	private static final String BORDERLINE = "Borderline";

	/**
	 * <p>Code dedicated for patient of sex Male and under thirty</p>
	 * @param risks as in number of
	 * @return The assessment product by the code
	 */
	private String maleUnderThirty(int risks) {
		String assessment;
		if (risks > 2 && risks <= 4) {
			assessment = DANGER;
		} else if (risks >= 5) {
			assessment = EARLYONSET;
		} else {
			assessment = NONE;
		}
		return assessment;
	}
	/**
	 * <p>Code dedicated for patient of sex Female and under thirty</p>
	 * @param risks as in number of
	 * @return The assessment product by the code
	 */
	private String femaleUnderThirty(int risks) {
		String assessment;
		if (risks > 4 && risks <= 6) {
			assessment = DANGER;
		} else if (risks >= 7) {
			assessment = EARLYONSET;
		} else {
			assessment = NONE;
		}
		return assessment;
	}
	/**
	 * <p>Code dedicated for patient over thirty</p>
	 * @param risks as in number of
	 * @return The assessment product by the code
	 */
	private String patientOverThirty(int risks) {
		String assessment;
		if (risks >= 2 && risks < 6) {
			assessment = BORDERLINE;
		} else if (risks >= 6 && risks < 8) {
			assessment = DANGER;
		} else if (risks >= 8) {
			assessment = EARLYONSET;
		} else {
			assessment = NONE;
		}
		return assessment;
	}
}
