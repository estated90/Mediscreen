package web.mediscreen.diabetalert.service;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AgeCalculator {

	private static final Logger logger = LogManager.getLogger("ageCalculation");

	/**
	 * @param birthdate to calculate from
	 * @return age of the persons
	 * @throws NullPointerException if not a date
	 * @throws IllegalArgumentException if date after today's date
	 */
	public int ageCalculation(LocalDate birthdate) {
		LocalDate birthdatePerson = birthdate;
		int age = Period.between(birthdatePerson, LocalDate.now()).getYears();
		if (birthdate.isAfter(LocalDate.now())) {
			logger.info("Person birthday after today's date : {}", birthdate);
			throw new IllegalArgumentException("Person's birthday date after today's date");
		} else if(age == 0) {
			logger.info("infant with age in month");
			age++;
		}
		logger.info("Age calculated is {}", age);
		return age;
	}
	
}
