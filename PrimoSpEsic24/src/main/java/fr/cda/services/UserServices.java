package fr.cda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.cda.entity.User;
import fr.cda.repository.UserRepository;


@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepos; // Element essentiel sans laquelle je ne peux pas faire de persistance 
	
	// Déclaration d'une variable BCryptPasswordEncoder qui permet de cripter les données
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
	
	public User saveWithPwdEncoder(User user) { // fonction qui reçoit un user et qui return son password hashé
		String hashedPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPwd);
		
		return userRepos.save(user);
		
	}
	
	
	
	public Optional<User> login(String login, String pwd){
		Optional<User> user = userRepos.findByLogin(login);
		if(user.isPresent() && passwordEncoder.matches(pwd, user.get().getPassword()) ) {
			return user;
		}else {
			return Optional.empty();
		}
	}

}
