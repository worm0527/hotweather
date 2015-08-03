package com.example.hotweather.model;

/**
 * county model
 * @author 0527
 */
public class County {
	private int id;
	
	private String countyName;
	
	private String countyPy;
	
	private String cityPy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyPy() {
		return countyPy;
	}

	public void setCountyPy(String countyPy) {
		this.countyPy = countyPy;
	}

	public String getCityPy() {
		return cityPy;
	}

	public void setCityPy(String cityPy) {
		this.cityPy = cityPy;
	}
}
