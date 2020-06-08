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
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.RulesService;

@RestController
@RequestMapping(value = "reserve")
public class ReserveController {
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ReservationRepository reservationRepository;
	
	@Autowired 
	private RulesService rulesService;
	
	@RequestMapping(value = "one", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> reserve(@RequestBody ReserveDTO requestDTO) {
		 
				List<Accommodation> accommodations = accommodationRepository.findAll();
				User u = userRepository.findOneById(requestDTO.userId);
				
				Accommodation a = accommodationRepository.findOneById(requestDTO.accommodationId);
				a.setPricePerDay(requestDTO.pricePerDay);
				List<Reservation> reservations = reservationRepository.findAll();
				Reservation ret = rulesService.setReservationPrice(u, a, reservations,requestDTO.startDate,requestDTO.endDate);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	
	public static class ReserveDTO {
		public Long userId;
		public Long accommodationId;
		public double pricePerDay;
		public String startDate;
		public String endDate;
	}

}
