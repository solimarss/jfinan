package br.com.solimar.ofx4j.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CartaoCreditoFatura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "VALOR")
	private BigDecimal valor;

	@Column(name = "DATA_VENCIMENTO")
	@Temporal(value = TemporalType.DATE)
	private Date dataVencimento;

}
