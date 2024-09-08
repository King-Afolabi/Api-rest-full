package fr.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.cda.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Optional<Role> findByLibelle(String libelle);
	
	@Query("SELECT r FROM Role r WHERE r.libelle = ?1")
	public Optional<Role> getByRoleName(String libelle);
	
}
