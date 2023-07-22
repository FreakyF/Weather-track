package org.weathertrack.service.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.weathertrack.service.validation.util.ValidationMessage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInputValidatorTests {
	private InputValidator sut;

	@BeforeEach
	void setUp() {
		sut = new UserInputValidator();
	}

	private static Stream<Arguments>
	validateCityNameInput_WhenUserCityInput_IsEmpty_ShouldReturnInvalidInputValidationResult() {
		return Stream.of(
				Arguments.of("", ValidationMessage.CITY_INPUT_EMPTY),
				Arguments.of(" ", ValidationMessage.CITY_INPUT_EMPTY)
		);
	}

	@ParameterizedTest
	@MethodSource
	void validateCityNameInput_WhenUserCityInput_IsEmpty_ShouldReturnInvalidInputValidationResult(String cityNameValue, String inputValidationResultValue) {
		// When
		var actualResult = sut.validateCityNameInput(cityNameValue);

		// Then
		assertEquals(inputValidationResultValue, actualResult.validationMessage());
	}

	static String generateCityName(int length) {
		return "x".repeat(Math.max(0, length));
	}

	private static Stream<Arguments>
	validateCityNameInput_WhenUserCityInput_IsTooLong_ShouldReturnInvalidInputValidationResult() {
		return Stream.of(
				Arguments.of(generateCityName(170), ValidationMessage.CITY_INPUT_TOO_LONG),
				Arguments.of(generateCityName(171), ValidationMessage.CITY_INPUT_TOO_LONG),
				Arguments.of(generateCityName(220), ValidationMessage.CITY_INPUT_TOO_LONG)
		);
	}

	@ParameterizedTest
	@MethodSource
	void validateCityNameInput_WhenUserCityInput_IsTooLong_ShouldReturnInvalidInputValidationResult(String cityNameValue, String inputValidationResultValue) {
		// When
		var actualResult = sut.validateCityNameInput(cityNameValue);

		// Then
		assertEquals(inputValidationResultValue, actualResult.validationMessage());
	}

	private static Stream<Arguments>
	validateCityNameInput_WhenUserCityInput_ContainsNumbers_ShouldReturnInvalidInputValidationResult() {
		return Stream.of(
				Arguments.of("0", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS),
				Arguments.of("123 San Francisco", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS),
				Arguments.of("San Francisco 345", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS),
				Arguments.of("San 678 Francisco", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS),
				Arguments.of("0123456789", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS),
				Arguments.of("0 1 2 3 4 5 6 7 8 9", ValidationMessage.CITY_INPUT_CONTAINS_NUMBERS)
		);
	}

	@ParameterizedTest
	@MethodSource
	void validateCityNameInput_WhenUserCityInput_ContainsNumbers_ShouldReturnInvalidInputValidationResult(String cityNameValue, String inputValidationResultValue) {
		// When
		var actualResult = sut.validateCityNameInput(cityNameValue);

		// Then
		assertEquals(inputValidationResultValue, actualResult.validationMessage());
	}

	private static Stream<Arguments>
	validateCityNameInput_WhenUserCityInput_ContainsSpecialCharacters_ShouldReturnInvalidInputValidationResult() {
		return Stream.of(
				Arguments.of("!@#$%^&*()_+=/*~<>?;:{}[]|\\", ValidationMessage.CITY_INPUT_CONTAINS_SPECIAL_CHARACTERS),
				Arguments.of("! San Francisco", ValidationMessage.CITY_INPUT_CONTAINS_SPECIAL_CHARACTERS),
				Arguments.of("San Francisco @", ValidationMessage.CITY_INPUT_CONTAINS_SPECIAL_CHARACTERS),
				Arguments.of("San # Francisco", ValidationMessage.CITY_INPUT_CONTAINS_SPECIAL_CHARACTERS)
		);
	}

	@ParameterizedTest
	@MethodSource
	void validateCityNameInput_WhenUserCityInput_ContainsSpecialCharacters_ShouldReturnInvalidInputValidationResult(String cityNameValue, String inputValidationResultValue) {
		// When
		var actualResult = sut.validateCityNameInput(cityNameValue);

		// Then
		assertEquals(inputValidationResultValue, actualResult.validationMessage());
	}

	@Test
	void validateCityNameInput_WhenUserCityInput_IsNull_ShouldThrowError() {
		// When
		var throwedException = assertThrows(IllegalStateException.class, () ->
				sut.validateCityNameInput(null));

		// Then
		assertEquals(InputValidationExceptionMessage.USER_INPUT_IS_NULL, throwedException.getMessage());
	}
}
