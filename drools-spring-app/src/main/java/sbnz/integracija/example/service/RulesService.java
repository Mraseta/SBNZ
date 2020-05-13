package sbnz.integracija.example.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.facts.Item;
import sbnz.integracija.example.model.Reservation;

@Service
public class RulesService {
	
	private final KieContainer kieContainer;
	@Autowired
	public RulesService(KieContainer kieContainer) {
		
		this.kieContainer = kieContainer;
	}
	public Reservation getClassifiedItem(Reservation r) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(r);
		kieSession.fireAllRules();
		kieSession.dispose();
		return r;
	}

}
