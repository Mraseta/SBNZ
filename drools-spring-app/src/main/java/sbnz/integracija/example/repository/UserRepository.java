package sbnz.integracija.example.repository;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();
	
	User findOneById(Long id);
	User findOneByUsername(String username);

}
