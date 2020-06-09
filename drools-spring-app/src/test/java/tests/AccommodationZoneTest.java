package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.User;

public class AccommodationZoneTest {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
        kieContainer = ks.newKieClasspathContainer();
	}
	
	@Test
	public void testZoneCenter() {
		kSession = kieContainer.newKieSession();
		
		Accommodation a = new Accommodation();
		a.setZone(Accommodation.Zone.NA);
		a.setDistanceFromCenter(1.5);
		
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-zone").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(Accommodation.Zone.CENTER, a.getZone());
	}
	
	@Test
	public void testZoneNormal() {
		kSession = kieContainer.newKieSession();
		
		Accommodation a = new Accommodation();
		a.setZone(Accommodation.Zone.NA);
		a.setDistanceFromCenter(7.0);
		
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-zone").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(Accommodation.Zone.NORMAL, a.getZone());
	}
	
	@Test
	public void testZoneSuburbs() {
		kSession = kieContainer.newKieSession();
		
		Accommodation a = new Accommodation();
		a.setZone(Accommodation.Zone.NA);
		a.setDistanceFromCenter(15);
		
		kSession.insert(a);
		
		kSession.getAgenda().getAgendaGroup("accommodation-zone").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(Accommodation.Zone.SUBURBS, a.getZone());
	}
}
