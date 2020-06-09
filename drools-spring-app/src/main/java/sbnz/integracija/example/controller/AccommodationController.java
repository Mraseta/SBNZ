package sbnz.integracija.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.AccommodationService;
import sbnz.integracija.example.service.ReservationService;
import sbnz.integracija.example.service.RulesService;
import sbnz.integracija.example.service.UserService;

@RestController
@RequestMapping(value = "accommodation")
public class AccommodationController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private AccommodationRepository accommodationRepository;
	
	@Autowired 
	private RulesService rulesService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(value = "one", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(@RequestBody AccommodationDTO requestDTO) {
		 
				Accommodation a = new Accommodation();
				a.setName(requestDTO.name);
				a.setCountry(requestDTO.country);
				a.setCity(requestDTO.city);
				a.setAddress(requestDTO.address);
				a.setDistanceFromCenter(requestDTO.distance);
				a.setPricePerDay(requestDTO.pricePerDay);
				a.setOwner(userRepository.getOne(requestDTO.ownerId).getUsername());
				a.setZone(Accommodation.Zone.NA);
				a = accommodationService.setAccommodationZone(a);
				return new ResponseEntity<>(a,HttpStatus.CREATED);
	}
	private static class AccommodationDTO {
		public Long ownerId;
		public String name;
		public String country;
		public String city;
		public String address;
		public double distance;
		public double pricePerDay;
	}

}
