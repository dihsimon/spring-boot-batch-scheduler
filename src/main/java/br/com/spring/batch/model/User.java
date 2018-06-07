package br.com.spring.batch.model;

import java.sql.Timestamp;

/**
 * Entidade que representa um usuário
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Carmona</a>
 * 6 de jun de 2018
 */
public class User {

	private String name;
	private Timestamp dateCreated;
	
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
