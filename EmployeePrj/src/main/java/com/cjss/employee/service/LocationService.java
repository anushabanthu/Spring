package com.cjss.employee.service;
import com.cjss.employee.model.Location;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LocationService {
	private List<Location> locations = new ArrayList<>();
	public void addLocations(Location location) {
		locations.add(location);
	}
	public List<Location> getLocations(){
		return locations;
	}
	public void deleteLocationById(int id){
		locations.stream().filter(loc->loc.getLocationId()==id).collect(Collectors.toList()).forEach(each->locations.remove(each));
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocationService that = (LocationService) o;
		return Objects.equals(locations, that.locations);
	}
	@Override
	public int hashCode() {
		return Objects.hash(locations);
	}
}
