package com.example.hotweather.model;

/**
 * city model 
 * @author 0527
 */
public class City {
	private int id;
	
	private String cityName;
	
	private String cityPy;
	
	private String provincePy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityPy() {
		return cityPy;
	}

	public void setCityPy(String cityPy) {
		this.cityPy = cityPy;
	}

	public String getProvincePy() {
		return provincePy;
	}

	public void setProvincePy(String provincePy) {
		this.provincePy = provincePy;
	}
}
