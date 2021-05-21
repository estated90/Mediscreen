package web.mediscreen.diabetalert.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Nicolas
 * <p>List of the terms used by the application to calculate the diabet risks</p>
 *
 */
public class Terms {

	/**
	 * <p>Default constructor to raised exception</p> 
	 */
	private Terms() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 *  <p>List of the terms used by the application to calculate the diabet risks</p>
	 */
	protected static final List<String> diabetTerms = Collections.unmodifiableList(Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille",
			"Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps"));

	/**
	 * @return the terms
	 */
	public static List<String> getTerms() {
		return diabetTerms;
	}

}
