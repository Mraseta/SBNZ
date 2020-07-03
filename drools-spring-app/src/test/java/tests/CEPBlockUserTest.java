package tests;

import static org.junit.Assert.assertEquals;

import org.drools.core.ClockType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import sbnz.integracija.example.event.ReservationEvent;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.model.User.Category;

public class CEPBlockUserTest {

	static KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks.newKieClasspathContainer();
		KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
		kconf.setOption(EventProcessingOption.STREAM);
		KieBase kieBase = kieContainer.newKieBase(kconf);
		KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
		kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		kSession = kieBase.newKieSession(kconfig1, null);
	}
	
	//@Test
	public void blockUserTest() {
		User u = new User();
		u.setStatus(User.Status.ACTIVE);
		u.setCategory(Category.BRONZE);
		
		Reservation r = new Reservation();
		r.setUser(u);
		
		ReservationEvent re1 = new ReservationEvent(r);
		ReservationEvent re2 = new ReservationEvent(r);
		ReservationEvent re3 = new ReservationEvent(r);
		ReservationEvent re4 = new ReservationEvent(r);
		ReservationEvent re5 = new ReservationEvent(r);
		ReservationEvent re6 = new ReservationEvent(r);
		
		kSession.insert(u);
		kSession.insert(re1);
		kSession.insert(re2);
		kSession.insert(re3);
		kSession.insert(re4);
		kSession.insert(re5);
		kSession.insert(re6);
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(User.Status.BLOCKED, u.getStatus());
	}
	
	//@Test
	public void notBlockedUserTest() {
		initKSession();
		User u = new User();
		u.setStatus(User.Status.ACTIVE);
		u.setCategory(Category.BRONZE);
		
		Reservation r = new Reservation();
		r.setUser(u);
		
		ReservationEvent re1 = new ReservationEvent(r);
		ReservationEvent re2 = new ReservationEvent(r);
		ReservationEvent re3 = new ReservationEvent(r);
		
		kSession.insert(u);
		kSession.insert(re1);
		kSession.insert(re2);
		kSession.insert(re3);
		kSession.fireAllRules();
		assertEquals(User.Status.ACTIVE, u.getStatus());
	}
	
	private void initKSession() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks.newKieClasspathContainer();
		KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
		kconf.setOption(EventProcessingOption.STREAM);
		KieBase kieBase = kieContainer.newKieBase(kconf);
		KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
		kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		kSession = kieBase.newKieSession(kconfig1, null);
	}
}
