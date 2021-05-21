package web.mediscreen.diabetalert.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Nicolas
 *
 */
public class DiabetUtils {

	private static final Logger logger = LogManager.getLogger(DiabetUtils.class);

	/**
	 * <p>Default builder to raise exception</p>
	 */
	private DiabetUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @param param1 String from which to remove wrong characters
	 * @return the corrected string
	 */
	public static String removeBadCharacters(String param1) {
		logger.info("Correcting string");
		return param1.replaceAll("[\n\r|\t]", " ");

	}
}
