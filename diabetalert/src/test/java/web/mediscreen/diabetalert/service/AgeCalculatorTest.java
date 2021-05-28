package web.mediscreen.diabetalert.service;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class AgeCalculatorTest {

	@InjectMocks
	private static AgeCalculator calculateAge;

	@BeforeAll
	private static void init() {
		calculateAge = new AgeCalculator();
	}

	@Test
	@Tag("ValidTest")
	void givenBirthdayDate_whenCalculating_thenReturnAge() {
		// GIVEN
		int ageGoal = 30;
		LocalDate date = LocalDate.now().minusYears(ageGoal);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("ValidTest")
	void givenAOneYoPersons_whenAgeCalculation_thenReturnCorrectAge() {
		// GIVEN
		int ageGoal = 1;
		LocalDate date = LocalDate.now().minusYears(ageGoal);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("ValidTest")
	void givenASixMonthPerson_whenAgeCalculation_thenReturnOneYear() {
		// GIVEN
		int ageGoal = 1;
		LocalDate date = LocalDate.now().minusMonths(6);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("InvalidTest")
	void givenFutureBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = LocalDate.now().plusYears(6);
		// When

		// Then
		assertThatIllegalArgumentException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}

	@Test
	@Tag("InvalidTest")
	void givenFutureOneDayBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = LocalDate.now().plusDays(1);
		// When

		// Then
		assertThatIllegalArgumentException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}

	@Test
	@Tag("InvalidTest")
	void givenNullBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = null;
		// When

		// Then
		assertThatNullPointerException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}

}
