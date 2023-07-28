package org.weathertrack;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.weathertrack.api.geocoding.GeocodingProvider;
import org.weathertrack.api.geocoding.openmeteo.OpenMeteoGeocodingProvider;
import org.weathertrack.api.geocoding.openmeteo.model.city.CityData;
import org.weathertrack.input.service.userio.CommandLineUserIOService;
import org.weathertrack.input.service.userio.UserIOModule;
import org.weathertrack.logging.LoggingModule;

import java.util.List;

public class Main {
	private static final Injector injector = Guice.createInjector(new LoggingModule(), new UserIOModule());

	public static void main(String[] args) {
		var userIOService = injector.getInstance(CommandLineUserIOService.class);
		var cityName = userIOService.getCityNameFromUser();

		GeocodingProvider geocodingProvider = new OpenMeteoGeocodingProvider();
		List<CityData> cityDataList = geocodingProvider.fetchCityData(cityName);

		userIOService.printCitiesWithSameName(cityDataList);
	}
}
