package fr.cda.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor @AllArgsConstructor @Data

public class EmargementContraint implements Serializable{
	
	private Long user;
	private Long cours;

}
