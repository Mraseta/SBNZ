package sbnz.integracija.example.service;

import java.time.LocalDateTime;
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
public class ReservationService {
	
private final KieContainer kieContainer;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	public ReservationService(KieContainer kieContainer) { this.kieContainer = kieContainer; }
	
	
	public Reservation setReservationPrice(User u, Accommodation a, List<Reservation> reservations, String startDate,
			String endDate) {

		Reservation r = new Reservation();
		r.setUser(u);
		r.setAccommodation(a);

		int startYear = Integer.parseInt(startDate.split("-")[0]);
		int startMonth = Integer.parseInt(startDate.split("-")[1]);
		int startDay = Integer.parseInt(startDate.split("-")[2]);
		int endYear = Integer.parseInt(endDate.split("-")[0]);
		int endMonth = Integer.parseInt(endDate.split("-")[1]);
		int endDay = Integer.parseInt(endDate.split("-")[2]);
		LocalDateTime start = LocalDateTime.of(startYear, startMonth, startDay, 0, 0);
		LocalDateTime end = LocalDateTime.of(endYear, endMonth, endDay, 0, 0);

		r.setStartDate(start);
		r.setEndDate(end);
		r.setStatus(Reservation.Status.PENDING);

		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(u);
		kieSession.insert(a);
		kieSession.insert(r);
		int i = 0;
		for (Reservation rr : reservations) {
			if (rr.getAccommodation().getOwner().equals(a.getOwner()) && rr.getStatus().equals(Reservation.Status.RESERVED)) {
				i++;
				kieSession.insert(rr);

			}
		}
		System.out.println("Broj poseta " + i);
		kieSession.setGlobal("visits", i);
		kieSession.getAgenda().getAgendaGroup("reservation-price").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		reservationRepository.save(r);
		return r;

	}

}
