package br.com.solimar.ofx4j.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", length = 300)
	private String nome;

	@Column(name = "LOGIN", length = 100)
	private String login;

	@Column(name = "SENHA", length = 200)
	private String senha;

	@Column(name = "EMAIL", length = 300)
	private String email;

}
