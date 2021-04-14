package web.mediscreen.personalinfo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.mediscreen.personalinfo.dto.PatientDto;

/**
 * @author Nico
 *
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldMatch, PatientDto> {

    private Logger logger = LoggerFactory.getLogger(FieldsValueMatchValidator.class);

    @Override
    public boolean isValid(PatientDto patient, final ConstraintValidatorContext context) {
	try {
	    String sex = patient.getSex();
	    boolean checked = false;
	    if (sex.equals("M") || sex.equals("F")) {
		checked = true;
	    }
	    return checked;
	} catch (final Exception ex) {
	    logger.info("Error while getting values from object", ex);
	    return false;
	}

    }

}
