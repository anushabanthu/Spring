package com.cjss.employee.service;

import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
	static private List<Location> locations = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addLocations(Location location) {
		locations.add(new Location(location.getLocationId(),location.getLocationName(),location.getLocationCountry()));
	}
	public List<Location> getLocations(){
		return locations;
	}
	public void deleteLocationById(int id){
		int count=0;
		for(Location l:locations){
			if(l.getLocationId() == id){
				break;
			}
			count++;
		}
		locations.remove(count);
	}
}
