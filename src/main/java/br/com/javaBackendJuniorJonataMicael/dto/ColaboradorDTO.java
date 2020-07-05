package br.com.javaBackendJuniorJonataMicael.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.javaBackendJuniorJonataMicael.model.Setor;

public class ColaboradorDTO {
	
	private Integer id;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private Date dataNascimento;
	private Setor setor;
	
	public ColaboradorDTO() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull(message = "CPF é uma informação obrigatória")
	@Length(min = 11, max = 11, message = "CPF deve conter onze números")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotNull(message = "Nome é uma informação obrigatória")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Telefone é uma informação obrigatória")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotNull(message = "Email é uma informação obrigatória")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull(message = "Data de Nascimento é uma informação obrigatória")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
