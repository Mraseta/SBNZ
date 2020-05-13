package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	List<Reservation> findAll();

}
