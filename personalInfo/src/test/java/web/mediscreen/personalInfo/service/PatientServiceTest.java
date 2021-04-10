package web.mediscreen.personalInfo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
class PatientServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void whengettingandcreating_UpdateDb() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(0)));
	String patient = "{\"family\": \"TestNone\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(put("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isCreated());
	mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].family", is("TestNone"))).andExpect(jsonPath("$[0].given", is("Test")))
		.andExpect(jsonPath("$[0].dob", is("1966-12-30"))).andExpect(jsonPath("$[0].sex", is("F")))
		.andExpect(jsonPath("$[0].address", is("1 Brookside St")))
		.andExpect(jsonPath("$[0].phone", is("100-222-3333")));
	mockMvc.perform(MockMvcRequestBuilders.get("/patient/1")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.family", is("TestNone")))
		.andExpect(jsonPath("$.given", is("Test"))).andExpect(jsonPath("$.dob", is("1966-12-30")))
		.andExpect(jsonPath("$.sex", is("F"))).andExpect(jsonPath("$.address", is("1 Brookside St")))
		.andExpect(jsonPath("$.phone", is("100-222-3333")));
	patient = "{\"id\":\"1\",\"family\": \"TestNoneUpdate\",\"given\": \"TestUpdate\",\"dob\": \"1966-12-13\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(
		MockMvcRequestBuilders.post("/patient/update").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(MockMvcResultMatchers.status().isOk());
	mockMvc.perform(MockMvcRequestBuilders.get("/patient/1")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.family", is("TestNoneUpdate")))
		.andExpect(jsonPath("$.given", is("TestUpdate"))).andExpect(jsonPath("$.dob", is("1966-12-13")))
		.andExpect(jsonPath("$.sex", is("F"))).andExpect(jsonPath("$.address", is("1 Brookside St")))
		.andExpect(jsonPath("$.phone", is("100-222-3333")));
	;
    }

    @Test
    @Order(2)
    void whenSendingIncorrectData_thenAnswerErros() throws Exception {
	String patient = "{\"family\": \"\",\"given\": \"\",\"dob\": \"2030-09-30\",\"sex\": \"Fe\",\"address\": \"\",\"phone\": \"\"}";
	mockMvc.perform(put("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors", hasSize(5)))
		.andExpect(jsonPath("$.errors", hasItem("address: Adress cannot be blank")))
		.andExpect(jsonPath("$.errors", hasItem("family: Family name is mandatory")))
		.andExpect(jsonPath("$.errors", hasItem("phone: Phone cannot be blank")))
		.andExpect(jsonPath("$.errors", hasItem("given: Given name is mandatory")))
		.andExpect(jsonPath("$.errors", hasItem("patient: You can only choose F or M")));
    }

    @Test
    @Order(3)
    void whenReceivingText_thenReturnUnsupported() throws Exception {
	String patient = "{\"family\": \"TestNone\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(put("/patient/add").contentType(MediaType.TEXT_PLAIN).content(patient))
		.andExpect(status().isUnsupportedMediaType()).andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors", hasSize(1)))
		.andExpect(jsonPath("$.errors",
			hasItem("text/plain media type is not supported. Supported media types are application/jso")))
		.andExpect(jsonPath("$.message", is("Content type 'text/plain' not supported")));
    }

    @Test
    @Order(4)
    void whenErroTypeRequest_thenBadRequest() throws Exception {
	mockMvc.perform(post("/patient/list")).andExpect(status().isMethodNotAllowed())
		.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors[0]",
			is("POST method is not supported for this request. Supported methods are GET ")));
    }

    @Test
    @Order(5)
    void whenDoublePatient_thenThrowError() throws Exception {
	String patient = "{\"family\": \"TestNone2\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(put("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isCreated());
	mockMvc.perform(put("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message", is("This patient already exist in db")))
		.andExpect(jsonPath("$.errors", hasItem("This patient is already in db")));
    }

    @Test
    @Order(6)
    void whenWrongInput_thenAlertUser() throws Exception {
	String patient = "{\"family\": \"TestNone\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(get("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message", is(
			"Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"add\"")))
		.andExpect(jsonPath("$.errors", hasItem("id should be of type int")));
    }

    @Test
    @Order(7)
    void whenNoPatient_thenAlertUser() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/patient/0"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(jsonPath("$.message", is("This patient do not exist in db")))
		.andExpect(jsonPath("$.errors", hasItem("This patient do not exist in db")));
    }

    @Test
    @Order(8)
    void whenErrorSavingInDB_thenAlertUser() throws Exception {
	String patient = "{\"family\": \"TestNone3\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-330033\"}";
	mockMvc.perform(put("/patient/add").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(status().isInternalServerError())
		.andExpect(jsonPath("$.message",
			is("Transaction silently rolled back because it has been marked as rollback-only")))
		.andExpect(jsonPath("$.errors", hasItem("error occurred")));
	;
    }

    @Test
    @Order(9)
    void whenUpdatingPatientNotInDb_thenAlertUser() throws Exception {
	String patient = "{\"id\":\"0\",\"family\": \"TestNone\",\"given\": \"Test\",\"dob\": \"1966-12-30\",\"sex\": \"F\",\"address\": \"1 Brookside St\",\"phone\": \"100-222-3333\"}";
	mockMvc.perform(
		MockMvcRequestBuilders.post("/patient/update").contentType(MediaType.APPLICATION_JSON).content(patient))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(jsonPath("$.message", is("This patient do not exist in db")))
		.andExpect(jsonPath("$.errors", hasItem("This patient do not exist in db")));
    }
}
