package org.weathertrack.api.service.exception;

public class ApiServiceExceptionMessage {
	public static final String CITY_NAME_IS_NULL
			= "Tried to get city name but the city name is null!";
	public static final String CITY_NAME_IS_BLANK
			= "Tried to get city name but the city name is blank!";
	public static final String GEOCODING_CITY_DATA_IS_NULL
			= "Tried to get geocoding city data but the geocoding city data is null!";
	public static final String URI_SYNTAX_IS_INVALID
			= "Tried to build URI but the URI syntax is invalid!";

	private ApiServiceExceptionMessage() {
	}
}
