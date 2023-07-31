package org.weathertrack.api.service.geocoding.openmeteo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.weathertrack.api.service.geocoding.GeocodingApiService;
import org.weathertrack.api.service.geocoding.model.GeocodingCityData;
import org.weathertrack.api.service.resource.ApiServiceExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OpenMeteoGeocodingApiServiceTests {
	private GeocodingApiService sut;

	@BeforeEach
	void beforeEach() {
		sut = new OpenMeteoGeocodingApiService();
	}

	private static Stream<Arguments> fetchCitiesForCityName_WhenCityNameIsInvalid_ShouldThrowException_WithAppropriateMessage() {
		return Stream.of(
				Arguments.of(null, ApiServiceExceptionMessage.CITY_NAME_IS_NULL, NullPointerException.class),
				Arguments.of("", ApiServiceExceptionMessage.CITY_NAME_IS_BLANK, IllegalArgumentException.class),
				Arguments.of(" ", ApiServiceExceptionMessage.CITY_NAME_IS_BLANK, IllegalArgumentException.class)
		);
	}

	@ParameterizedTest
	@MethodSource
	void fetchCitiesForCityName_WhenCityNameIsInvalid_ShouldThrowException_WithAppropriateMessage(
			String cityNameValue, String expectedExceptionMessage, Class<? extends Throwable> exceptionClass) {
		// Then
		var exception = assertThrows(exceptionClass, () -> sut.fetchCitiesForCityName(cityNameValue));
		assertEquals(expectedExceptionMessage, exception.getMessage());
	}

	private static Stream<Arguments> fetchCitiesForCityName_WhenCityNameIsValid_ShouldReturnGeocodingCityData() {
		return Stream.of(
				Arguments.of("Kielce"),
				Arguments.of("Jastrzębie-Zdrój"),
				Arguments.of("San Francisco"),
				Arguments.of("D'Lo"),
				Arguments.of("Saint-Lô"),
				Arguments.of("Winston-Salem")
		);
	}

	@ParameterizedTest
	@MethodSource
	void fetchCitiesForCityName_WhenCityNameIsValid_ShouldReturnGeocodingCityData(String cityNameValue) {
		// When
		List<GeocodingCityData> mockResponse = new ArrayList<>();
		mockResponse.add(new GeocodingCityData("Kielce", "Świętokrzyskie", "Poland"));
		mockResponse.add(new GeocodingCityData("Jastrzębie-Zdrój", "Silesia", "Poland"));
		mockResponse.add(new GeocodingCityData("San Francisco", "California", "United States"));
		mockResponse.add(new GeocodingCityData("D'Lo", "Mississippi", "United States"));
		mockResponse.add(new GeocodingCityData("Winston-Salem", "North Carolina", "United States"));

		when(sut.fetchCitiesForCityName(anyString())).thenReturn(mockResponse);

		// Given
		var result = sut.fetchCitiesForCityName(cityNameValue);

		// Then
		assertEquals(mockResponse, result);
	}
}
