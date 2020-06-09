package sbnz.integracija.example.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.apache.tomcat.jni.Local;
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
	public RulesService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Reservation getClassifiedItem(Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		kieSession.fireAllRules();
		kieSession.dispose();
		reservationRepository.save(r);

		return r;
	}
}
