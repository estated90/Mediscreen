package web.mediscreen.diabetalert.utils;

public class DiabetUtils {

	private DiabetUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String removeBadCharacters(String param1) {
		return param1.replaceAll("[\n\r|\t]", "_");
	}
}
