package fr.cda.repository;

import org.springframework.data.repository.CrudRepository;

import fr.cda.entity.Emargement;
import fr.cda.entity.EmargementContraint;

public interface EmargementRepository extends CrudRepository<Emargement, EmargementContraint>{
	public Iterable<Emargement> findByCoursId(Long id);
	

}
