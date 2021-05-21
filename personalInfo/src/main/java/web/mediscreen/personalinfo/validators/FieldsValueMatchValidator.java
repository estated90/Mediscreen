package web.mediscreen.personalinfo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import web.mediscreen.personalinfo.dto.PatientDto;

/**
 * @author Nico
 *
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldMatch, PatientDto> {

	private Logger logger = LoggerFactory.getLogger(FieldsValueMatchValidator.class);

	/**
	 *<p>Check if the fields received are correct. Apply to Sex and Phone number</p>
	 */
	@Override
	public boolean isValid(PatientDto patient, final ConstraintValidatorContext context) {
		try {
			String sex = patient.getSex();
			var checked = false;
			if (sex.equals("M") || sex.equals("F")) {
				if (isValidE123(patient.getPhone(), patient.getCountryCode())) {
					checked = true;
				}
			}
			return checked;
		} catch (final Exception ex) {
			logger.info("Error while getting values from object", ex);
			return false;
		}

	}

	/**
	 * @param s Phone number
	 * @param countryCode as ISO code
	 * @return booelan
	 * @throws NumberParseException Exception when unable to parse
	 */
	private static boolean isValidE123(String s, String countryCode) throws NumberParseException {
		PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phoneNumber = numberUtil.parse(s, countryCode);
		return numberUtil.isValidNumber(phoneNumber);
	}

}
