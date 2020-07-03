package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.User;

public class AccommodationDiscountTest {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
        kieContainer = ks.newKieClasspathContainer();
	}
	
	//@Test
	public void testDiscountSilver() {
		kSession = kieContainer.newKieSession();
		
		User u = new User();
		u.setCategory(User.Category.SILVER);
		
		Accommodation a = new Accommodation();
		a.setPricePerDay(100);
		
		double startPrice = 100;
		
		kSession.insert(u);
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-discount").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(a.getPricePerDay(), 0.97*startPrice, 0.01);
	}
	
	//@Test
	public void testDiscountGold() {
		kSession = kieContainer.newKieSession();
		
		User u = new User();
		u.setCategory(User.Category.GOLD);
		
		Accommodation a = new Accommodation();
		a.setPricePerDay(100);
		
		double startPrice = 100;
		
		kSession.insert(u);
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-discount").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(a.getPricePerDay(), 0.95*startPrice, 0.01);
	}
	
	//@Test
	public void testDiscountPlatinum() {
		kSession = kieContainer.newKieSession();
		
		User u = new User();
		u.setCategory(User.Category.PLATINUM);
		
		Accommodation a = new Accommodation();
		a.setPricePerDay(100);
		
		double startPrice = 100;
		
		kSession.insert(u);
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-discount").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(a.getPricePerDay(), 0.9*startPrice, 0.01);
	}
}
