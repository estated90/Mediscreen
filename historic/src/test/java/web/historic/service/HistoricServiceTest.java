package web.historic.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import web.historic.dto.HistoricDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
class HistoricServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    protected Environment env;

    @Test
    @Order(1)
    void whenRunningApp_thenCorrectFunctionningImpl() throws Exception {
	System.out.println(env.getProperty("spring.data.mongodb.database"));
	mockMvc.perform(MockMvcRequestBuilders.get("/historic/1"))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(jsonPath("$.message", is("No history has been found for patient 1")))
		.andExpect(jsonPath("$.errors", hasItem("No patient history returned")));
	HistoricDto historic = new HistoricDto();
	historic.setPatId(4);
	historic.setPatient("Test");
	historic.setPractitionnerNotesRecommandation("Some note");
	mockMvc.perform(put("/patient/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(historic)))
		.andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
