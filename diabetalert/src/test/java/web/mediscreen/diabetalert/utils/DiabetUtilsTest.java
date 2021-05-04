package web.mediscreen.diabetalert.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiabetUtilsTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void whenPrividingString_thenReplaceCharacters() {
		String toverify = "I need\ra string\nwith lot|of errors";
		String replaced = DiabetUtils.removeBadCharacters(toverify);
		
		assertEquals("I need a string with lot of errors", replaced);
	}
	
	@Test
	void whenPrividingStringWithNumbers_thenReplaceCharacters() {
		String toverify = "I need\ra string\nwith lot|of errors 123";
		String replaced = DiabetUtils.removeBadCharacters(toverify);
		
		assertEquals("I need a string with lot of errors 123", replaced);
	}
	
	@Test
	void whenPrividingStringEmpty_thenThrowError() {
		String toverify = null;
		assertThrows(NullPointerException.class, () -> DiabetUtils.removeBadCharacters(toverify));
	}

}
