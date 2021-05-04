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
		return param1.replaceAll("[\n\r|\t]", " ");

	}
}
