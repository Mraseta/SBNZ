package sbnz.integracija.example.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class UserService {
	
private final KieContainer kieContainer;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	public UserService(KieContainer kieContainer) { this.kieContainer = kieContainer; }
	
	public User setUserCategory(User u) {
		KieSession kieSession = kieContainer.newKieSession();
		List<Reservation> reservations = reservationRepository.findAll();
		for(Reservation r : reservations) {
			kieSession.insert(r);
		}
		kieSession.insert(u);
		kieSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		userRepository.save(u);
		return u;
	}
	public Reservation updateUserCategory(Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		User u = userRepository.getOne(r.getUser().getId());
		kieSession.insert(u);
		List<Reservation> reservations = reservationRepository.findAll();
		for (Reservation rr : reservations) {
			kieSession.insert(rr);
			
		}
		kieSession.insert(r);
		
		kieSession.fireAllRules();
		kieSession.dispose();
		userRepository.save(u);
		return reservationRepository.save(r);

	}

}
