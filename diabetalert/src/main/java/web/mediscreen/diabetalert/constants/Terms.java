package web.mediscreen.diabetalert.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Terms {

	private Terms() {
		throw new IllegalStateException("Utility class");
	}

	public static final List<String> TERMS = new ArrayList<>(Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille",
			"Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps"));

}
