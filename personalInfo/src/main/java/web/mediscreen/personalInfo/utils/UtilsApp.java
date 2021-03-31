package web.mediscreen.personalInfo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import web.mediscreen.personalInfo.exception.DateParsingException;

@Service
public class UtilsApp {

	private static final Logger logger = LogManager.getLogger(UtilsApp.class);

	public static LocalDate stringToLocalDate(String dateString) throws DateParsingException {
		try {
			logger.info("Parsing String {} to date", dateString);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dateString, dateTimeFormatter);
			return localDate;
		} catch (DateTimeParseException ex) {
			logger.error("Error while parsing String {} to date", dateString);
			throw new DateParsingException("Date should have the format YYYY-mm-dd and error "+ex.getMessage());
		}
	}
}
