package org.weathertrack.view.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weathertrack.service.input.CommandLineUserInputService;
import org.weathertrack.util.logger.LoggerInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommandLineUserInterfaceTests {
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private CommandLineUserInterface commandLineUserInterface;

	private LoggerInterface<CommandLineUserInterface> logger;

	private static final String EXPECTED_LOG_MESSAGE =
			"Tried to print cities with the same name, when the list of cities is empty!";

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outputStream));
		CommandLineUserInputService userInputService = mock(CommandLineUserInputService.class);
		logger = mock(LoggerInterface.class);
		commandLineUserInterface = new CommandLineUserInterface(userInputService, logger);
	}

	@Test
	void printCitiesWithSameName_shouldPrintCities() {
		// When
		List<String> cities = Arrays.asList("New York", "London", "Paris");

		// Given
		commandLineUserInterface.printCitiesWithSameName(cities);

		// Then
		String expectedOutput = "1. New York\n2. London\n3. Paris";
		assertEquals(expectedOutput, outputStream.toString());
	}

	@Test
	void printCitiesWithSameName_emptyList() {
		// When
		List<String> cities = List.of();

		// Given
		commandLineUserInterface.printCitiesWithSameName(cities);

		// Then
		verify(logger).warn(EXPECTED_LOG_MESSAGE);
	}

	@Test
	void printCitiesWithSameName_singleCity() {
		// When
		List<String> cities = List.of("New York");

		// Given
		commandLineUserInterface.printCitiesWithSameName(cities);

		// Then
		String expectedOutput = "1. New York";
		assertEquals(expectedOutput, outputStream.toString());
	}
}
