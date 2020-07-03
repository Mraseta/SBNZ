package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

public class UserCategoryTest {

	KieSession kSession = null;
    static KieContainer kieContainer;

    @BeforeClass
    public static void beforeClass(){
    	KieServices ks = KieServices.Factory.get();
        kieContainer = ks.newKieClasspathContainer();
    }
	
	@Test
	public void testUserCategorySilver() {
		kSession = kieContainer.newKieSession();
		//3
		User u = new User();
		u.setCategory(User.Category.BRONZE);
		
		Accommodation a = new Accommodation();
		
		Reservation r1 = new Reservation();
		r1.setAccommodation(a);
		r1.setStatus(Reservation.Status.RESERVED);
		r1.setUser(u);
		
		Reservation r2 = new Reservation();
		r2.setAccommodation(a);
		r2.setStatus(Reservation.Status.RESERVED);
		r2.setUser(u);
		
		Reservation r3 = new Reservation();
		r3.setAccommodation(a);
		r3.setStatus(Reservation.Status.RESERVED);
		r3.setUser(u);
		
		kSession.insert(u);
		kSession.insert(a);
		kSession.insert(r1);
		kSession.insert(r2);
		kSession.insert(r3);
		
		kSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		assertEquals(User.Category.SILVER, u.getCategory());
	}
	
	@Test
	public void testUserCategoryGold() {
		kSession = kieContainer.newKieSession();
		//6
		User u = new User();
		u.setCategory(User.Category.BRONZE);
		
		Accommodation a = new Accommodation();
		
		Reservation r1 = new Reservation();
		r1.setAccommodation(a);
		r1.setStatus(Reservation.Status.RESERVED);
		r1.setUser(u);
		
		Reservation r2 = new Reservation();
		r2.setAccommodation(a);
		r2.setStatus(Reservation.Status.RESERVED);
		r2.setUser(u);
		
		Reservation r3 = new Reservation();
		r3.setAccommodation(a);
		r3.setStatus(Reservation.Status.RESERVED);
		r3.setUser(u);
		
		Reservation r4 = new Reservation();
		r4.setAccommodation(a);
		r4.setStatus(Reservation.Status.RESERVED);
		r4.setUser(u);
		
		Reservation r5 = new Reservation();
		r5.setAccommodation(a);
		r5.setStatus(Reservation.Status.RESERVED);
		r5.setUser(u);
		
		Reservation r6 = new Reservation();
		r6.setAccommodation(a);
		r6.setStatus(Reservation.Status.RESERVED);
		r6.setUser(u);
		
		kSession.insert(u);
		kSession.insert(a);
		kSession.insert(r1);
		kSession.insert(r2);
		kSession.insert(r3);
		kSession.insert(r4);
		kSession.insert(r5);
		kSession.insert(r6);
		
		kSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kSession.fireAllRules();
		assertEquals(User.Category.GOLD, u.getCategory());
	}
	
	@Test
	public void testUserCategoryPlatinum() {
		kSession = kieContainer.newKieSession();
		//10
		User u = new User();
		u.setCategory(User.Category.BRONZE);
		
		Accommodation a = new Accommodation();
		
		Reservation r1 = new Reservation();
		r1.setAccommodation(a);
		r1.setStatus(Reservation.Status.RESERVED);
		r1.setUser(u);
		
		Reservation r2 = new Reservation();
		r2.setAccommodation(a);
		r2.setStatus(Reservation.Status.RESERVED);
		r2.setUser(u);
		
		Reservation r3 = new Reservation();
		r3.setAccommodation(a);
		r3.setStatus(Reservation.Status.RESERVED);
		r3.setUser(u);
		
		Reservation r4 = new Reservation();
		r4.setAccommodation(a);
		r4.setStatus(Reservation.Status.RESERVED);
		r4.setUser(u);
		
		Reservation r5 = new Reservation();
		r5.setAccommodation(a);
		r5.setStatus(Reservation.Status.RESERVED);
		r5.setUser(u);
		
		Reservation r6 = new Reservation();
		r6.setAccommodation(a);
		r6.setStatus(Reservation.Status.RESERVED);
		r6.setUser(u);
		
		Reservation r7 = new Reservation();
		r7.setAccommodation(a);
		r7.setStatus(Reservation.Status.RESERVED);
		r7.setUser(u);
		
		Reservation r8 = new Reservation();
		r8.setAccommodation(a);
		r8.setStatus(Reservation.Status.RESERVED);
		r8.setUser(u);
		
		Reservation r9 = new Reservation();
		r9.setAccommodation(a);
		r9.setStatus(Reservation.Status.RESERVED);
		r9.setUser(u);
		
		Reservation r10 = new Reservation();
		r10.setAccommodation(a);
		r10.setStatus(Reservation.Status.RESERVED);
		r10.setUser(u);
		
		kSession.insert(u);
		kSession.insert(a);
		kSession.insert(r1);
		kSession.insert(r2);
		kSession.insert(r3);
		kSession.insert(r4);
		kSession.insert(r5);
		kSession.insert(r6);
		kSession.insert(r7);
		kSession.insert(r8);
		kSession.insert(r9);
		kSession.insert(r10);
		
		kSession.getAgenda().getAgendaGroup("user-category").setFocus();
		kSession.fireAllRules();
		assertEquals(User.Category.PLATINUM, u.getCategory());
	}
}
