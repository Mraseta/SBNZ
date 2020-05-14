package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.RulesService;


@RestController
public class RestEndpoints {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccommodationRepository accommodationRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RulesService rulesService;
	
	@RequestMapping(value = "/testusercat", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testusercat() {
		
				User u = userRepository.getOne(1L);
				User u1 = rulesService.setUserCategory(u);
		
				return new ResponseEntity<>(u1,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/testacczone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testacczone() {
		
				List<Accommodation> accommodations = accommodationRepository.findAll();
				List<Accommodation> ret = rulesService.setAccommodationZone(accommodations);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/testaccdiscount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testaccdiscount() {
		
				List<Accommodation> accommodations = accommodationRepository.findAll();
				User u = userRepository.findOneById(1L);
				List<Accommodation> ret = rulesService.setAccommodationDiscount(u, accommodations);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/testresprice", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testresprice() {
		
				List<Accommodation> accommodations = accommodationRepository.findAll();
				User u = userRepository.findOneById(1L);
				
				Accommodation a = accommodationRepository.findOneById(1L); //ovo ce dolaziti sa fronta pa je ok cena.
				List<Reservation> reservations = reservationRepository.findAll();
				Reservation ret = rulesService.setReservationPrice(u, a, reservations);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	

}
