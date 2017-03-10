package br.com.solimar.ofx4j.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.solimar.ofx4j.enums.ContaTipoEnum;

@Entity
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CONTA_TIPO", nullable = false, length = 30)
	private ContaTipoEnum contaTipo;
	
	@Column(name = "CONTA_NUMERO")
	private String contaNumero;

	@Column(name = "AGENCIA_NUMERO")
	private String agenciaNumero;
	
	@Column(name = "CARTAO_CREDTO_NUM")
	private String cartaoCreditoNumero;
	
	
	

	
	
	

}
