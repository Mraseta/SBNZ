package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.AccommodationService;
import sbnz.integracija.example.service.ReservationService;
import sbnz.integracija.example.service.RulesService;
import sbnz.integracija.example.service.UserService;

@RestController
@RequestMapping(value = "search")
public class SearchController {
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RulesService rulesService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findPatient(@RequestParam String country, @RequestParam String city,
			@RequestParam int zone, @RequestParam long pricePerDayMin, @RequestParam long pricePerDayMax,@RequestParam Long id) {
		List<Accommodation> accomodations = accommodationRepository.findAll();
		List<Accommodation> accomodationsRet = new ArrayList<>();
		User u = userRepository.findOneById(id);
		Accommodation.Zone zoneEnum = null;
		
		//NA,CENTER,NORMAL,SUBURBS
		if(zone == 0) {
			zoneEnum = null;
		}else if(zone == 1) {
			zoneEnum = Accommodation.Zone.CENTER;
		}else if(zone == 2) {
			zoneEnum = Accommodation.Zone.NORMAL;
		}else {
			zoneEnum = Accommodation.Zone.SUBURBS;
		}
		for (Accommodation a : accomodations) {
			if (!a.getCity().equals(city) && !city.equals("")) {
				continue;
			}

			if (!a.getCountry().equals(country) && !country.equals("")) {
				continue;
			}
			if (!(a.getZone() == zoneEnum)) {
				continue;
			}
			if(a.getPricePerDay() > pricePerDayMax) {
				continue;
			}
			if(a.getPricePerDay() < pricePerDayMin){
				continue;
			}
			accomodationsRet.add(a);
		}
		List<Accommodation> accomodationsWithDisc = accommodationService.setAccommodationDiscount(u, accomodationsRet);
		return new ResponseEntity<>(accomodationsWithDisc, HttpStatus.OK);
	}

}
