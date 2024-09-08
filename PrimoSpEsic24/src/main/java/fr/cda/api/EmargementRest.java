package fr.cda.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.cda.entity.Emargement;
import fr.cda.entity.EmargementContraint;
import fr.cda.repository.EmargementRepository;

@RestController
@CrossOrigin("*")

public class EmargementRest {

	@Autowired
	private EmargementRepository emargementRepos;
	
	
	@PatchMapping("emargement/{idUser}/{idCours}")
	public Optional<Emargement> justifierAbsence (
			@PathVariable Long idUser,
			@PathVariable Long idCours,
			@RequestBody Emargement emargement){
		
		EmargementContraint id = new EmargementContraint(idUser, idCours);
		Optional<Emargement> e = emargementRepos.findById(id);
		
		if (e.isPresent()) {
			e.get().setJustificatif(emargement.getJustificatif());
			emargementRepos.save(e.get());
			
		}
		
		return e;
	}
}
