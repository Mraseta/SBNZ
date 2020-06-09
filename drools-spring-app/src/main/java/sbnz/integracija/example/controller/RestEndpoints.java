package sbnz.integracija.example.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
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

		return new ResponseEntity<>(u1, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/testacczone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testacczone() {

		List<Accommodation> accommodations = accommodationRepository.findAll();
		List<Accommodation> ret = rulesService.setAccommodationZone(accommodations);

		return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/testaccdiscount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testaccdiscount() {

		List<Accommodation> accommodations = accommodationRepository.findAll();
		User u = userRepository.findOneById(1L);
		List<Accommodation> ret = rulesService.setAccommodationDiscount(u, accommodations);

		return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
	}

	@Scheduled(initialDelay = 1000, fixedRate = 1000000)
	public void scheduleFixedRateWithInitialDelayTask() {

		List<Accommodation> accommodations = accommodationRepository.findAll();
		List<Accommodation> ret = rulesService.setAccommodationZone(accommodations);
		}

}
