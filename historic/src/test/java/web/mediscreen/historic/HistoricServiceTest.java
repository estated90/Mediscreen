package web.mediscreen.historic;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.inject.Inject;

import web.mediscreen.historic.dto.HistoricDto;
import web.mediscreen.historic.model.Historic;
import web.mediscreen.historic.model.Patient;
import web.mediscreen.historic.proxy.PatientFeign;
import web.mediscreen.historic.repositories.HistoricRepositories;
import web.mediscreen.historic.service.HistoricService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
class HistoricServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private HistoricRepositories repository;
	@MockBean
	@Inject
	private PatientFeign patientFeign;
	@Inject
	private HistoricService hitoricService;
	@Autowired
	private MongoOperations mongoOperations;

	@AfterEach
	void tearDownAfterClass() throws Exception {
		repository.deleteAll();
		mongoOperations.dropCollection("database_sequences");
	}

	@Test
	@Order(1)
	void whenRunningApp_thenCorrectFunctionningImpl() throws Exception {
		// GIVEN
		Patient patient = new Patient(1, "Test", "Test", LocalDate.now(), "F", "Test", "Test");
		// WHEN
		when(patientFeign.getPatientById(anyInt())).thenReturn(patient);
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/historic/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$").isArray());
		HistoricDto historic = new HistoricDto();
		historic.setPatId(4);
		historic.setPatient("Test");
		historic.setPractitionnerNotesRecommandation("Some note");
		mockMvc.perform(put("/historic/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(historic)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.patId", is(historic.getPatId())))
				.andExpect(jsonPath("$.patient", is(historic.getPatient())))
				.andExpect(jsonPath("$.practitionnerNotesRecommandation",
						is(historic.getPractitionnerNotesRecommandation())))
				.andExpect(jsonPath("$.id", is(1)));
		Historic historicReturned = repository.findById(1).get();
		mockMvc.perform(MockMvcRequestBuilders.get("/historic/4")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].patId", is(historicReturned.getPatId())))
				.andExpect(jsonPath("$[0].patient", is(historicReturned.getPatient())))
				.andExpect(jsonPath("$[0].practitionnerNotesRecommandation",
						is(historicReturned.getPractitionnerNotesRecommandation())))
				.andExpect(jsonPath("$[0].id", is(historicReturned.getId())));
		historic.setId(1);
		historic.setPractitionnerNotesRecommandation("Some new note for tests");
		mockMvc.perform(MockMvcRequestBuilders.post("/historic/update").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(historic))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.patId", is(historic.getPatId())))
				.andExpect(jsonPath("$.patient", is(historic.getPatient())))
				.andExpect(jsonPath("$.practitionnerNotesRecommandation",
						is(historic.getPractitionnerNotesRecommandation())))
				.andExpect(jsonPath("$.id", is(1)));
		historicReturned = repository.findById(1).get();
		mockMvc.perform(MockMvcRequestBuilders.get("/historic/get/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.patId", is(historicReturned.getPatId())))
				.andExpect(jsonPath("$.patient", is(historicReturned.getPatient())))
				.andExpect(jsonPath("$.practitionnerNotesRecommandation",
						is(historicReturned.getPractitionnerNotesRecommandation())))
				.andExpect(jsonPath("$.id", is(historicReturned.getId())));
	}

	@Test
	@Order(2)
	void whenSendingIncorrectData_thenAnswerErros() throws Exception {
		String historic = "{\"patient\":\"\",\"patId\":-1,\"practitionnerNotesRecommandation\":\"\"}";
		mockMvc.perform(put("/historic/create").contentType(MediaType.APPLICATION_JSON).content(historic))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("practitionnerNotesRecommandation: The note is mandatory")))
				.andExpect(jsonPath("$.errors", hasItem("patient: Patient name is mandatory")))
				.andExpect(jsonPath("$.errors", hasItem("patId: Patient id is a positive number")));
		historic = "{}";
		mockMvc.perform(put("/historic/create").contentType(MediaType.APPLICATION_JSON).content(historic))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("practitionnerNotesRecommandation: The note cannot be null")))
				.andExpect(jsonPath("$.errors", hasItem("practitionnerNotesRecommandation: The note is mandatory")))
				.andExpect(jsonPath("$.errors", hasItem("patient: Patient name is mandatory")))
				.andExpect(jsonPath("$.errors", hasItem("patient: Patient name cannot be null")))
				.andExpect(jsonPath("$.errors", hasItem("patId: Patient id is a positive number")));
	}

	@Test
	@Order(3)
	void whenReceivingText_thenReturnUnsupported() throws Exception {
		String historic = "{\"patient\":\"test\",\"patId\":1,\"practitionnerNotesRecommandation\":\"Patient states that they are 'feeling terrific' Weight at or below recommended level\"}";
		mockMvc.perform(put("/historic/create").contentType(MediaType.TEXT_PLAIN).content(historic))
				.andExpect(status().isUnsupportedMediaType()).andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors",
						hasItem("text/plain media type is not supported. Supported media types are application/jso")))
				.andExpect(jsonPath("$.message", is("Content type 'text/plain' not supported")));
	}

	@Test
	@Order(4)
	void whenErroTypeRequest_thenBadRequest() throws Exception {
		mockMvc.perform(post("/historic/1")).andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors[0]",
						is("POST method is not supported for this request. Supported methods are GET ")));
	}

	@Test
	@Order(5)
	void whenIdHistoryNotInDb_thenReturnError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/historic/get/80"))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(jsonPath("$.message", is("No history has been found for id 80")))
				.andExpect(jsonPath("$.errors", hasItem("No patient history returned")));
	}

	@Test
	@Order(6)
	void whenWrongInput_thenAlertUser() throws Exception {
		String historic = "{\"patient\":\"test\",\"patId\":\"n\",\"practitionnerNotesRecommandation\":\"Patient states that they are 'feeling terrific' Weight at or below recommended level\"}";
		mockMvc.perform(get("/historic/create").contentType(MediaType.APPLICATION_JSON).content(historic))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is(
						"Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"create\"")))
				.andExpect(jsonPath("$.errors", hasItem("id should be of type int")));
	}

	@Test
	@Order(9)
	void whenUpdatingPatientNotInDb_thenAlertUser() throws Exception {
		String historic = "{\"patient\":\"test\",\"patId\":80,\"practitionnerNotesRecommandation\":\"Patient states that they are 'feeling terrific' Weight at or below recommended level\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/historic/create").contentType(MediaType.APPLICATION_JSON)
				.content(historic)).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(jsonPath("$.message", is("This patient do not exist. Enable to attach historic")))
				.andExpect(jsonPath("$.errors", hasItem("No patient in DB with that id")));
		;
	}

	@Test
	@Order(10)
	void whenUpdatingHistoryNotInDb_thenAlertUser() throws Exception {
		String historic = "{\"id\":20,\"patient\":\"test\",\"patId\":80,\"practitionnerNotesRecommandation\":\"Patient states that they are 'feeling terrific' Weight at or below recommended level\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/historic/update").contentType(MediaType.APPLICATION_JSON)
				.content(historic)).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(jsonPath("$.message", is("No history has been found with id 20")))
				.andExpect(jsonPath("$.errors", hasItem("No patient history returned")));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
