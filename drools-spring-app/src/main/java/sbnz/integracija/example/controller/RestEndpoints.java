package sbnz.integracija.example.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.AccommodationService;
import sbnz.integracija.example.service.ReservationService;
import sbnz.integracija.example.service.RulesService;
import sbnz.integracija.example.service.UserService;


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
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/testusercat", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testusercat() {
		
				User u = userRepository.getOne(1L);
				User u1 = userService.setUserCategory(u);
		
				return new ResponseEntity<>(u1,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/testacczone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testacczone() {
		
				List<Accommodation> accommodations = accommodationRepository.findAll();
				List<Accommodation> ret = accommodationService.setAccommodationZone(accommodations);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/testaccdiscount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testaccdiscount() {
		
				List<Accommodation> accommodations = accommodationRepository.findAll();
				User u = userRepository.findOneById(1L);
				List<Accommodation> ret = accommodationService.setAccommodationDiscount(u, accommodations);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}

	@Scheduled(initialDelay = 1000,fixedRate = 1000000)
	public void scheduleFixedRateWithInitialDelayTask() {
	  
		List<Accommodation> accommodations = accommodationRepository.findAll();
		List<Accommodation> ret = accommodationService.setAccommodationZone(accommodations);
	    System.out.println(
	      "Fixed rate task with one second initial delay - MRS");
	}
	

}