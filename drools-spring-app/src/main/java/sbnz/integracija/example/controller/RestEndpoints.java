package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.RulesService;


@RestController
public class RestEndpoints {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RulesService rulesService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getQuestions() {
		
				User u = userRepository.getOne(1L);
				ArrayList<Reservation> reservations = new ArrayList<>();
				for(Reservation r : u.getReservations()) {
					reservations.add(rulesService.getClassifiedItem(r));
				}
		
				return new ResponseEntity<>(u.getReservations(),HttpStatus.ACCEPTED);
	}

}
