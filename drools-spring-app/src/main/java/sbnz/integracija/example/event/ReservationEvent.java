package sbnz.integracija.example.event;

import sbnz.integracija.example.model.Reservation;

@org.kie.api.definition.type.Role(org.kie.api.definition.type.Role.Type.EVENT)
public class ReservationEvent {
	
	private Reservation reservation;
	
	public ReservationEvent() {
		
	}
	
	public ReservationEvent(Reservation r) {
		this.reservation = r;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	

}
