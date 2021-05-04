package web.mediscreen.diabetalert.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiabetUtils {

	private static final Logger logger = LogManager.getLogger(DiabetUtils.class);
	
	private DiabetUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String removeBadCharacters(String param1) {
		logger.info("Correcting string");
		if (param1!=null) {
			logger.info("Correcting string has been done");
			return param1.replaceAll("[\n\r|\t]", " ");
		} else {
			logger.error("Parameter was null");
			throw new NullPointerException(param1 + "should not be null");
		}
		
	}
}
