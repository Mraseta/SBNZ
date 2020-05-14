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
				User u1 = rulesService.setUserCategory(u);
		
				return new ResponseEntity<>(u1,HttpStatus.ACCEPTED);
	}

}
