package web.mediscreen.personalinfo;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;

import web.mediscreen.personalinfo.exception.PatientRetrevalException;
import web.mediscreen.personalinfo.repositories.PatientRepository;
import web.mediscreen.personalinfo.service.PatientService;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(MockitoJUnitRunner.class)
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
