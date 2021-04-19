package web.mediscreen.personalInfo.utils;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import web.mediscreen.personalInfo.dto.PatientDto;
import web.mediscreen.personalInfo.model.Patient;

@Service
public class PatientUtils {

    public Patient convertDtoToPatient(PatientDto patientDto) {
	Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	return mapper.map(patientDto, Patient.class);
    }

}
