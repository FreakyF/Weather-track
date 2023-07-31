package org.weathertrack.api.service.resource;

public class ApiServiceExceptionMessage {
	public static final String CITY_NAME_IS_NULL
			= "Tried to get city name but the city name is null!";
	public static final String CITY_NAME_IS_BLANK
			= "Tried to get city name but the city name is blank!";
	public static final String GEOCODING_CITY_DATA_IS_NULL
			= "Tried to get geocoding city data but the geocoding city data is null!";

	private ApiServiceExceptionMessage() {
	}
}
