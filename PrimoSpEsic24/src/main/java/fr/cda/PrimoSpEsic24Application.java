package fr.cda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.cda.entity.Classe;
import fr.cda.entity.Cours;
import fr.cda.entity.Emargement;
import fr.cda.entity.Role;
import fr.cda.entity.User;
import fr.cda.repository.ClasseRepository;
import fr.cda.repository.CoursRepository;
import fr.cda.repository.EmargementRepository;
import fr.cda.repository.RoleRepository;
import fr.cda.repository.UserRepository;
import fr.cda.services.UserServices;
 
@SpringBootApplication
public class PrimoSpEsic24Application implements CommandLineRunner {
	
	// Nous disposons de données tests ici pour nous permettre d'utiliser notre back-End.
	
	// Il faut déclarer Autowired à chaque fois on utilise un repository
	
	@Autowired
	private UserRepository userRepos;
	@Autowired
	private RoleRepository roleRepos;
	@Autowired
	private UserServices userService;
	@Autowired
	private ClasseRepository classeRepos;
	@Autowired
	private CoursRepository coursRepos;
	@Autowired
	private EmargementRepository emargementRepos;
 
	public static void main(String[] args) {
		SpringApplication.run(PrimoSpEsic24Application.class, args);
		System.out.println("lancement terminé");
	}
 
	
	// celui ci sera exécuté avant le main à cause de @Override
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("lancement...");
		
		Role r1 = new Role(null, "administrateur");
		Role r2 = new Role(null, "formateur");
		Role r3 = new Role(null, "utilisateur");
		roleRepos.save(r1); // application de la persistance
		roleRepos.save(r2);
		roleRepos.save(r3);
 
		/* Sans hashage
		userRepos.save(new User(null, "DROGBA", "Cheal", "perte@titre.com", "but", r1));
		
		User u2 = new User(null, "Mbappe", "part", "paris@magic.com", "paris", r3);
		User u3 = new User(null, "POER", "John", "jo", "test", r2);
		
		userRepos.save(u2);
		userRepos.save(u3);
		*/
		
		
		
		Classe c1 = new Classe(null, "BTS-2023-2024", null);
		Classe c2 = new Classe(null, "CDA-2023-2024", null);
		Classe c3 = new Classe(null, "ESIS-1-2023-2024", null);
		classeRepos.save(c1); // application de la persistance
		classeRepos.save(c2);
		classeRepos.save(c3);

		// Avec Hashage
		
		User u1 = new User(null, "TYUIO", "Jeff", "azerty", "serie", r1, c1);
		User u2 = new User(null, "YRFIO", "Remy", "iro", "agent", r1, c1);
		User u3 = new User(null, "POER", "John", "jo", "test", r2, null);
		userService.saveWithPwdEncoder(u1);
		userService.saveWithPwdEncoder(u2);
		userService.saveWithPwdEncoder(u3);
		
		
		
		List<User> apprenantsBTS = new ArrayList<>();
		apprenantsBTS.add(u1);
		apprenantsBTS.add(u2);
		
		c1.setApprenants(apprenantsBTS);
		classeRepos.save(c1); // application de la persistance
		
		/*
		Cours cours1 = new Cours(null, "Mérise", LocalDate.of(2023, 10, 10), LocalTime.of(10, 0), LocalDate.of(2015, 7, 10), c2, u3);
		coursRepos.save(cours1);
		*/
		
		SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy HH:mm") ; // Réglage du format rien de doit déborder ni manquer
		// Date date = d.parse ("18/06/2024 09:00") ;
		Cours crs1 = new Cours (null, "java", d.parse ("18/06/2024 09:00"),  null, c1, u3) ;
		Cours crs2 = new Cours (null, "sql", d.parse ("19/06/2024 09:00"),  null, c1, u3) ;
		coursRepos. save (crs1) ; // application de la persistance
		coursRepos. save (crs2) ;
		
		
		Emargement e1 = new Emargement (u1, crs1, false, null, "il est malade") ;
		Emargement e2 = new Emargement (u2, crs1, true, null, "") ;
		emargementRepos.save (e1) ; // application de la persistance
		emargementRepos. save (e2) ;
		
		
		
	}
 
}