package sbnz.integracija.example.controller;

import java.util.ArrayList;
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
import sbnz.integracija.example.service.AccommodationService;
import sbnz.integracija.example.service.ReservationService;
import sbnz.integracija.example.service.CepService;
import sbnz.integracija.example.service.RulesService;
import sbnz.integracija.example.service.UserService;

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
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;

  @Autowired
	private CepService cepService;

	
	@RequestMapping(value = "one", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> reserve(@RequestBody ReserveDTO requestDTO) {
		 
				List<Accommodation> accommodations = accommodationRepository.findAll();
				User u = userRepository.findOneById(requestDTO.userId);
				if(u.getStatus().equals(User.Status.BLOCKED)) {
					return new ResponseEntity<>("ne mozete da rezervisete blokirani ste",HttpStatus.BAD_REQUEST);
				}
				
				Accommodation a = accommodationRepository.findOneById(requestDTO.accommodationId);
				a.setPricePerDay(requestDTO.pricePerDay);
				List<Reservation> reservations = reservationRepository.findAll();

				Reservation ret = reservationService.setReservationPrice(u, a, reservations,requestDTO.startDate,requestDTO.endDate);
				cepService.checkReservation(ret);
		
				return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "get-confirmed", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getConfirmed(@RequestBody Id id) {
		List<Reservation> reservations = reservationRepository.findAll();
		List<Reservation> ret = new ArrayList<Reservation>();
		User owner = userRepository.findOneById(id.id);
		
		for(Reservation r : reservations) {
			if(r.getAccommodation().getOwner().equals(owner.getUsername()) && r.getStatus().equals(Reservation.Status.CONFIRMED)) {
				ret.add(r);
			}
		}
		
		return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "approve", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> approveReservation(@RequestBody Id id) {
		Reservation r = reservationRepository.findOneById(id.id);
		
		r.setStatus(Reservation.Status.RESERVED);
		r = userService.updateUserCategory(r);
		reservationRepository.save(r);
		
		return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
	}
	
	public static class Id {
		public Long id;
	}
	
	public static class ReserveDTO {
		public Long userId;
		public Long accommodationId;
		public double pricePerDay;
		public String startDate;
		public String endDate;
	}

}
