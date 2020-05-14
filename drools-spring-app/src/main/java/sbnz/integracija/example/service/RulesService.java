package sbnz.integracija.example.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

@Service
public class RulesService {
	
	private final KieContainer kieContainer;
	@Autowired
	public RulesService(KieContainer kieContainer) { this.kieContainer = kieContainer; }
	
	public Reservation getClassifiedItem(Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}
	public User setUserCategory(User u) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(u);
		kieSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return u;
	}
	

}
