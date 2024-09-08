package fr.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByLogin(String login);
	
	public Optional<User> findByLoginAndPassword(String login, String password);
	
}
