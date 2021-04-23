package web.historic.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.inject.Inject;

import web.historic.dto.HistoricDto;
import web.historic.model.DatabaseSequence;
import web.historic.model.Historic;
import web.historic.model.Patient;
import web.historic.proxy.PatientFeign;
import web.historic.repositories.HistoricRepositories;

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
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(jsonPath("$.message", is("No history has been found for patient 1")))
				.andExpect(jsonPath("$.errors", hasItem("No patient history returned")));
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
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
