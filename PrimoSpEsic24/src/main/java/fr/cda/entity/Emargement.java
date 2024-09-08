package fr.cda.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
@IdClass(EmargementContraint.class)
public class Emargement {
	@Id
	@ManyToOne
	private User user;
	
	@Id
	@ManyToOne
	private Cours cours;
	
	private boolean isPresent;
	
	@CreationTimestamp // permet de générer la date et l'heure automatiquement
	private Date dateEmargement;
	private String justificatif;

}
