package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.model.Accommodation;
import sbnz.integracija.example.model.User;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{

	List<Accommodation> findAll();
	Accommodation findOneById(Long id);
}
