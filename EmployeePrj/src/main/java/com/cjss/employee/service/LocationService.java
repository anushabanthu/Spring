package com.cjss.employee.service;
import com.cjss.employee.entity.LocationEntity;
import com.cjss.employee.model.Location;
import com.cjss.employee.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;

	public Location getLocDetailsById(Integer id){
		Optional<LocationEntity> locationEntity = locationRepository.findById(id);    //findById is available by default from JPARepository
		Location location = new Location();

		location.setLocationId(locationEntity.get().getLocationId());
		location.setLocationName(locationEntity.get().getLocationName());
		location.setLocationCountry(locationEntity.get().getLocationCountry());
		return location;
	}

	public LocationEntity addLocDetails(Location location){
		LocationEntity locationEntity = new LocationEntity();
		locationEntity.setLocationName(location.getLocationName());
		locationEntity.setLocationCountry(location.getLocationCountry());
		return locationRepository.save(locationEntity);
	}

	public void deleteLocationById(Integer id){
		locationRepository.deleteById(id);
	}
}
