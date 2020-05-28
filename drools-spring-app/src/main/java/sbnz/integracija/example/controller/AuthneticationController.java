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

@RestController()
@RequestMapping(value = "auth")
public class AuthneticationController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> testusercat(@RequestBody UserDTO user) {
			User u = userRepository.findOneByUsername(user.username);
			if(!u.getPassword().equals(user.password)) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); //status 400
				
			}else {
				user.firstName = u.getFirstName();
				user.lastName = u.getLastName();
				return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
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
