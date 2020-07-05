package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.AccommodationService;
import sbnz.integracija.example.service.ReservationService;
import sbnz.integracija.example.service.RulesService;
import sbnz.integracija.example.service.UserService;

@RestController
@RequestMapping(value = "auth")
public class AuthneticationController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RulesService rulesService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> testusercat(@RequestBody UserDTO user) {
			User u = userRepository.findOneByUsername(user.username);
			if(u == null) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); 
			}
			if(!u.getPassword().equals(user.password)) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); 
				
			}else {
				User u1 = userService.setUserCategory(u);
				return new ResponseEntity<>(u1,HttpStatus.ACCEPTED);
			}
		
				
	}
	@RequestMapping(value = "/register",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody UserDTO user){
		User a = userRepository.findOneByUsername(user.username);
		if(a != null) {
			return new ResponseEntity<>("vec postoji user sa ovim username",HttpStatus.BAD_REQUEST);
		}
		User u = new User();
		u.setUsername(user.username);
		u.setPassword(user.password);
		u.setFirstName(user.firstName);
		u.setLastName(user.lastName);
		u.setCategory(User.Category.BRONZE);
		u.setStatus(User.Status.ACTIVE);
		u.setType(User.Type.USER);
		userRepository.save(u);
		return new ResponseEntity<>(u,HttpStatus.CREATED);
	
	}
	
	public static class UserDTO {
		public String username;
		public String password;
		public String firstName;
		public String lastName;
	}

}
