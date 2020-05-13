package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();

}
