package com.cjss.employee.model;

public class Location {
	private int locationId;
	private String locationName;
	private String locationCountry;
	public Location(int locationId,String locationName,String locationCountry ) {
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationCountry = locationCountry; 
	}
	public int getLocationId() {
		return locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public String getLocationCountry() {
		return locationCountry;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}
}
