package fr.cda.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import fr.cda.entity.Classe;
import fr.cda.entity.User;
import fr.cda.repository.ClasseRepository;
import fr.cda.repository.UserRepository;
import fr.cda.services.UserServices;


// Nous sommes dans le ---------------------------------------Controller
// Toujours y ajouter @RestController et ensuite @CrossOrigin("*")

@RestController
@CrossOrigin("*")


public class UserRest {

	// /*
	@Autowired 
	// partout où on utilise un repository. Un repository nous permet de faire de la persistance
	private UserRepository userRepos;
	@Autowired
	private UserServices userServices;
	@Autowired
	private ClasseRepository classeRepos;
	
	
	/*
	 * Les @ sont multiples on a : GetMapping(" le lien qui apparaitra dans le navigateur ") --> ce lien c'est nous qui le déffinissons 
	 * Comme on fait un GET il n'est pas rare que l'utilisateur saisisse des infos pour cela il faut prévoit dans le lien du navigateur
	 * des variables " /{nomVariable} " qui seront récupéré par les END POINT pour leurs fonctionnements
	 * Il faut éviter qu'il ne soit identique que celui que data rest nous fournie.
	 * 
	
	
	@GetMapping("users") 
	// Ici le lien sera http://localhost:8080/users (c'est exactement ce que data rest nous fourni ce qui 
	// crée une redondance raison pour laquelle on a mis les END POINT en commmentaire.
	
	
	public Iterable<User> getAllUsers() { 
	// Cette fonction retourne une Class User à la fin mais cet User à une particularité il est
	// le resultat d'une itération sur la table user mais cela est fait grâce à userRepos.findAll

		return userRepos.findAll();
		// userRepos.findAll() Nous vient de UserRepository qui lui a hérité de JpaRepository ou CrudRepository par extends.
	}

	@GetMapping("users/{idUser}") 
	// Le lien sera " http://localhost:8080/users/{idUser} " {idUser} sera remplacé par ce que l'utilisateur va saisir.
	// idUser est un nom que nous avons donné de manière aléatoire qu'on utlisera dans notre END POINT en dessous.
	
	public Optional<User> getById(@PathVariable Long idUser) {
	// Cette fonction retourne une Class User à la fin mais cet User à une particularité il est
	// le resultat d'une itération sur la table user mais cela est fait grâce à userRepos.findAll
	
	
		return userRepos.findById(idUser);
	}

	@PostMapping("users")
	public User saveUser(@RequestBody User u) {
		return userRepos.save(u);
	}

	@DeleteMapping("users/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		Optional<User> user = userRepos.findById(id);
		if (user.isPresent()) {
			userRepos.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@PutMapping("users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User u) {
		u.setId(id);
		return userRepos.save(u);
	}

	@PatchMapping("users/{id}")
	public Optional<User> updatePassword(@PathVariable Long id, @RequestBody User u) {
		Optional<User> user = userRepos.findById(id);
		if (user.isPresent()) {
			user.get().setPassword(u.getPassword());
			userRepos.save(user.get());
		}
		return user;
	}

	@GetMapping("users/login/{login}")
	public Optional<User> getByLogin(@PathVariable String login) {
		return userRepos.findByLogin(login);
	}
	*/
	@PostMapping("users/connexion")
	public Optional<User> connexion(@RequestBody User u) {	
	//	return userRepos.findByLoginAndPassword(u.getLogin(), u.getPassword());
		return userServices.login(u.getLogin(), u.getPassword());
	}
	
	@PostMapping("user")
	public User saveUser(@RequestBody User u) {
		return userServices.saveWithPwdEncoder(u);
	}
	@PatchMapping("user/classe/apprenant/{id}")
	public Optional<User> addApprenantInClass(@PathVariable Long id, @RequestBody User u) {
		Optional<User> user = userRepos.findById(id);
		if(user.isPresent()) {
			Optional<Classe> c = classeRepos.findById(u.getClasse().getId());
			if(c.isPresent()) {
				user.get().setClasse(c.get());
				userRepos.save(user.get());
				c.get().getApprenants().add(user.get());
				classeRepos.save(c.get());
			}
		}
		return user;
	}

	// */
}
