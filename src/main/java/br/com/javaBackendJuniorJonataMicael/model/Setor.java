package br.com.javaBackendJuniorJonataMicael.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setor")
public class Setor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6407479116555186480L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descricao", nullable = false, unique = true)
	private String descricao;
	
	public Setor() {
		
	}
	
	public Setor(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}