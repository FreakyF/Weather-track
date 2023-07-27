package org.weathertrack.weather.provider.openmeteo.model.city;

public class CityData {
	private final long id;
	private final String name;
	private final double latitude;
	private final double longitude;
	private final double elevation;
	private final String feature_code;
	private final String country_code;
	private final long admin1_id;
	private final long admin2_id;
	private final long admin3_id;
	private final long admin4_id;
	private final String timezone;
	private final String population;
	private final long[] postcodes;
	private final int country_id;
	private final String country;
	private final String admin1;
	private final String admin2;
	private final String admin3;
	private final String admin4;

	public CityData(long id,
	                String name,
	                double latitude,
	                double longitude,
	                double elevation,
	                String feature_code,
	                String country_code,
	                long admin1_id,
	                long admin2_id,
	                long admin3_id,
	                long admin4_id,
	                String timezone,
	                String population,
	                long[] postcodes,
	                int country_id,
	                String country,
	                String admin1,
	                String admin2,
	                String admin3,
	                String admin4) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.feature_code = feature_code;
		this.country_code = country_code;
		this.admin1_id = admin1_id;
		this.admin2_id = admin2_id;
		this.admin3_id = admin3_id;
		this.admin4_id = admin4_id;
		this.timezone = timezone;
		this.population = population;
		this.postcodes = postcodes;
		this.country_id = country_id;
		this.country = country;
		this.admin1 = admin1;
		this.admin2 = admin2;
		this.admin3 = admin3;
		this.admin4 = admin4;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public String getFeature_code() {
		return feature_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public long getAdmin1_id() {
		return admin1_id;
	}

	public long getAdmin2_id() {
		return admin2_id;
	}

	public long getAdmin3_id() {
		return admin3_id;
	}

	public long getAdmin4_id() {
		return admin4_id;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getPopulation() {
		return population;
	}

	public long[] getPostcodes() {
		return postcodes;
	}

	public int getCountry_id() {
		return country_id;
	}

	public String getCountry() {
		return country;
	}

	public String getAdmin1() {
		return admin1;
	}

	public String getAdmin2() {
		return admin2;
	}

	public String getAdmin3() {
		return admin3;
	}

	public String getAdmin4() {
		return admin4;
	}
}
