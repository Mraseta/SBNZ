package sbnz.integracija.example.service;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.SampleApp;
import sbnz.integracija.example.event.ReservationEvent;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.AccommodationRepository;
import sbnz.integracija.example.repository.ReservationRepository;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class CepService {

	private final KieContainer kieContainer;
	private KieSession kSession;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	public CepService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;

	}

	public void checkReservation(Reservation r) {
		ReservationEvent re = new ReservationEvent(r);
		
		KieSession kieSession = SampleApp.kieSessions.get("cep-reserve-controll-spam");
		if (kieSession == null) {
			KieServices ks = KieServices.Factory.get();
			KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
			kconf.setOption(EventProcessingOption.STREAM);
			KieBase kieBase = kieContainer.newKieBase(kconf);
			KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
			kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
			kieSession = kieBase.newKieSession(kconfig1, null);
			SampleApp.kieSessions.put("cep-reserve-controll-spam", kieSession);
		}
		kieSession.insert(re);
		User u = userRepository.getOne(r.getUser().getId());
		FactHandle handle = kieSession.insert(u);
		kieSession.fireAllRules();
		userRepository.save(u);
		kieSession.delete(handle);
	}

}
