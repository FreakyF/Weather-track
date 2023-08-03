package org.weathertrack.api.service.geocoding.openmeteo;

import com.google.inject.name.Named;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.weathertrack.api.service.exception.ApiServiceExceptionMessage;
import org.weathertrack.api.service.geocoding.GeocodingApiModule;
import org.weathertrack.api.service.geocoding.GeocodingApiService;
import org.weathertrack.api.service.geocoding.openmeteo.model.CityDataResponseDTO;
import org.weathertrack.api.service.geocoding.openmeteo.model.GetCityDataRequest;
import org.weathertrack.api.service.http.HttpService;
import org.weathertrack.model.ResponseData;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class OpenMeteoGeocodingApiService implements GeocodingApiService {
	private final URIBuilder uriBuilder;
	private final HttpClient httpClient;
	private final HttpService httpService;

	public OpenMeteoGeocodingApiService(@Named(GeocodingApiModule.ANNOTATION_GEOCODING_API) URIBuilder uriBuilder, HttpClient httpClient, HttpService httpService) {
		this.uriBuilder = uriBuilder;
		this.httpClient = httpClient;
		this.httpService = httpService;
	}

	@Override
	public ResponseData<List<CityDataResponseDTO>> fetchCitiesForCityName(String cityName) {
		validateCityName(cityName);
		var requestUrl = buildGeocodingApiUri(cityName);
		throw new UnsupportedOperationException("Not implemented");
//		return Response.<List<CityDataResponseDTO>>ok(response);
	}

	@Override
	public ResponseData<CityDataResponseDTO> fetchGeocodingDataForCity(GetCityDataRequest request) {
		throw new UnsupportedOperationException("Not Implemented");
	}

	private URI buildGeocodingApiUri(String cityName) {
		try {
			return uriBuilder.setParameter("name", cityName).build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(ApiServiceExceptionMessage.URI_SYNTAX_IS_INVALID);
		}
	}

	private void validateCityName(String cityName) {
		if (cityName == null) {
			throw new NullPointerException(ApiServiceExceptionMessage.CITY_NAME_IS_NULL);
		}
		if (cityName.isBlank()) {
			throw new IllegalArgumentException(ApiServiceExceptionMessage.CITY_NAME_IS_BLANK);
		}
	}
}
