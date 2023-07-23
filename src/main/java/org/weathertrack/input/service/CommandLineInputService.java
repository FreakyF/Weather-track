package org.weathertrack.input.service;

import org.weathertrack.logging.Logger;
import org.weathertrack.weather.exception.WeatherExceptionMessage;
import org.weathertrack.weather.model.WeatherData;
import org.weathertrack.weather.resource.WeatherDisplayResource;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CommandLineInputService implements InputService {
	private final Scanner scanner;

	private final Logger logger;

	public CommandLineInputService(Logger logger, Scanner scanner) {
		this.logger = logger;
		this.scanner = scanner;
	}

	@Override
	public String getUserInput(String message) {
		System.out.print(message);
		return scanner.nextLine().trim();
	}

	@Override
	public String getCityNameFromUser() {
		String cityName;
		do {
			cityName = getUserInput("Enter the city name: ");
		} while (cityName.isBlank());

		return cityName;
	}

	@Override
	public void printCitiesWithSameName(List<String> citiesWithSameName) {
		if (citiesWithSameName.isEmpty()) {
			logger.warn(WeatherExceptionMessage.CITIES_WITH_SAME_NAME_IS_EMPTY);
			return;
		}

		StringBuilder message = new StringBuilder();
		for (int i = 0; i < citiesWithSameName.size(); i++) {
			message.append(i + 1).append(". ").append(citiesWithSameName.get(i));
			var isLastCity = i == citiesWithSameName.size() - 1;
			if (!isLastCity) {
				message.append("\n");
			}
		}
		System.out.print(message);
	}

	@Override
	public void printWeather(WeatherData weatherData) {
		var weatherConditionMessage = String.format(
				WeatherDisplayResource.WEATHER_CONDITION,
				weatherData.weatherCondition()
		);
		System.out.println(weatherConditionMessage);

		var temperatureMessage = String.format(
				WeatherDisplayResource.TEMPERATURE,
				weatherData.temperatureCelsius()
		);
		System.out.println(temperatureMessage);

		var cloudinessMessage = String.format(
				WeatherDisplayResource.CLOUDINESS,
				weatherData.cloudiness()
		);
		System.out.println(cloudinessMessage);

		var rainChanceMessage = String.format(
				WeatherDisplayResource.RAIN_CHANCE,
				weatherData.rainChance()
		);

		System.out.println(rainChanceMessage);

		var windSpeedMessage = String.format(
				WeatherDisplayResource.WIND_SPEED,
				weatherData.windSpeed()
		);
		System.out.println(windSpeedMessage);

		var humidityMessage = String.format(
				WeatherDisplayResource.HUMIDITY,
				weatherData.humidity()
		);
		System.out.println(humidityMessage);

		var pressureMessage = String.format(
				WeatherDisplayResource.PRESSURE,
				weatherData.pressure()
		);
		System.out.println(pressureMessage);
	}
}
