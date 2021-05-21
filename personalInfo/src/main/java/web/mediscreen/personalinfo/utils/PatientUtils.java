package web.mediscreen.personalinfo.utils;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

import web.mediscreen.personalinfo.dto.PatientDto;
import web.mediscreen.personalinfo.model.Patient;

/**
 * @author Nicolas
 *
 */
@Service
public class PatientUtils {

    /**
     * @param patientDto as Object
     * @return patient Object
     */
    public Patient convertDtoToPatient(PatientDto patientDto) {
    	var mapper = DozerBeanMapperBuilder.buildDefault();
	return mapper.map(patientDto, Patient.class);
    }

}
