package web.mediscreen.diabetalert.integration;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.github.dozermapper.core.inject.Inject;

import web.mediscreen.diabetalert.model.Historic;
import web.mediscreen.diabetalert.model.Patient;
import web.mediscreen.diabetalert.proxy.HistoryFeign;
import web.mediscreen.diabetalert.proxy.PatientFeign;
import web.mediscreen.diabetalert.service.DiabetService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class DiabetServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	@Inject
	private PatientFeign patientFeign;
	@MockBean
	@Inject
	private HistoryFeign historyFeign;
	@Inject
	private DiabetService diabetService;
	private Patient patient;
	private List<Historic> historics;
	private Historic historic;
	private MvcResult result;
	private String message;

	@BeforeEach
	void createBefore() throws Exception {
		patient = new Patient(1, "Test", "Test", LocalDate.now(), "F", "Test", "Test");
		historics = new ArrayList<>();
		historic = new Historic(1, "Test Test", 1, "This is a note");
		historics.add(historic);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		patient = null;
		historics = null;
		historic = null;
	}

	@Test
	void whenRunningApp_thenCorrectFunctionningImpl() throws Exception {
		// GIVEN

		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		// THEN
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: None", message);

	}

	@Test
	void whenCallingWithName_thenCorrectFunctionningImpl() throws Exception {
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: None", message);
	}

	@Test
	void whenFemaleCallingWithMultipleHistory_thenCorrectFunctionningImpl() throws Exception {
		// GIVEN
		historic = new Historic(1, "Test Test", 1,
				"This is a note with patology : Microalbumine and TaiLLe and Cholestérol and Rechute");
		historics.add(historic);
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: None", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);

		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: In Danger", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: In Danger", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: Early onset", message);
	}
	@Test
	void whenMaleCallingWithMultipleHistory_thenCorrectFunctionningImpl() throws Exception {
		// GIVEN
		patient = new Patient(1, "Test", "Test", LocalDate.now(), "M", "Test", "Test");
		historic = new Historic(1, "Test Test", 1,
				"This is a note with patology : Microalbumine and Rechute");
		historics.add(historic);
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: None", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: In Danger", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: In Danger", message);
		historic = new Historic(1, "Test Test", 1, "Réaction");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess?familyName=Test Test"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (1) diabetes assessment is: Early onset", message);
	}
	@Test
	void whenUnknownCallingWithMultipleHistory_thenError() throws Exception {
		// GIVEN
		patient = new Patient(1, "Test", "Test", LocalDate.now(), "R", "Test", "Test");
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unkown sex")))
				.andExpect(jsonPath("$.errors", hasItem("error occurred")));
	}
	@Test
	void whenMoreThanThirtyCallingWithMultipleHistory_thenCorrectFunctionningImpl() throws Exception {
		// GIVEN
		patient = new Patient(1, "Test", "Test", LocalDate.now().minusYears(31), "M", "Test", "Test");
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		when(historyFeign.getHistoricPatientById(anyInt())).thenReturn(historics);
		when(historyFeign.getHistoryWithName(anyString())).thenReturn(historics);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (31) diabetes assessment is: None", message);
		historic = new Historic(1, "Test Test", 1,
				"This is a note with patology : Microalbumine and Rechute");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (31) diabetes assessment is: Borderline", message);
		historic = new Historic(1, "Test Test", 1,
				"This is a note with patology : Microalbumine and Rechute, Rechute, Rechute");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (31) diabetes assessment is: In Danger", message);
		historic = new Historic(1, "Test Test", 1,
				"This is a note with patology : Microalbumine and Rechute");
		historics.add(historic);
		result = mockMvc.perform(MockMvcRequestBuilders.get("/assess/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		message = result.getResponse().getContentAsString();
		assertEquals("Patient: Test Test (31) diabetes assessment is: Early onset", message);
	}

}
