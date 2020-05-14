package sbnz.integracija.example.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class RulesService {
	
	private final KieContainer kieContainer;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	public RulesService(KieContainer kieContainer) { this.kieContainer = kieContainer; }
	
	public Reservation getClassifiedItem(Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		kieSession.fireAllRules();
		kieSession.dispose();
		reservationRepository.save(r);
		
		return r;
	}
	
	public User setUserCategory(User u) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(u);
		kieSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		userRepository.save(u);
		return u;
	}
	
	public List<Accommodation> setAccommodationZone(List<Accommodation> accommodations) {
		KieSession kieSession = kieContainer.newKieSession();
		for(Accommodation a : accommodations) {
			kieSession.insert(a);
			accommodationRepository.save(a);
		}
		kieSession.getAgenda().getAgendaGroup("accommodation-zone").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return accommodations;
	}
	
	public List<Accommodation> setAccommodationDiscount(User u,List<Accommodation> accommodations) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(u);
		for(Accommodation a : accommodations) {
			kieSession.insert(a);
			
		}
		kieSession.getAgenda().getAgendaGroup("accommodation-discount").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return accommodations;
		
	}
	

}
