package web.mediscreen.personalInfo;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.TestPropertySource;

import web.mediscreen.personalInfo.exception.PatientRetrevalException;
import web.mediscreen.personalInfo.repositories.PatientRepository;
import web.mediscreen.personalInfo.service.PatientService;

@TestPropertySource(locations = "classpath:application-test.properties")
class PatientServiceUnitTest {

    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private static PatientService patientService;

    @BeforeAll
    private static void init() {
	patientService = new PatientService();
    }

    @Test()
    void whenGettingPatient_thenTrowException() throws Exception {
	try {
	    patientService.gettingAllPatient();
	    fail();
	} catch (PatientRetrevalException e) {
	    assertThat(e.getMessage(), is("error while retrieving patients"));
	}
    }

}
