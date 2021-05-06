package web.mediscreen.diabetalert.integration;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void whenErroTypeRequest_thenBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/assess/1")).andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors[0]",
						is("POST method is not supported for this request. Supported methods are GET ")));
	}

	@Test
	void whenWrongInput_thenAlertUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/assess").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is(
						"Required request parameter 'familyName' for method parameter type String is not present")))
				.andExpect(jsonPath("$.errors", hasItem("familyName parameter is missing")));
	}
	
	@Test
	void whenStringInsteadInt_thenBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/assess/p")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())				
				.andExpect(jsonPath("$.message", is(
						"Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"p\"")))
				.andExpect(jsonPath("$.errors", hasItem("id should be of type int")));
	}
	
}
