package br.com.solimar.ofx4j.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.solimar.ofx4j.enums.LancamentoTipoEnum;

@Entity
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRICAO", length = 300)
	private String descricao;

	@Column(name = "MEMO", length = 300)
	private String memo;

	@Column(name = "VALOR")
	private BigDecimal valor;

	// O lançamento tem seu valor desconsiderado quando
	// trata-se de uma tranferecia de valores entre contas
	// ou o valor foi dividido entre outros lançamentos.
	// Uma RECEITA será uma entrada que tem o valor considerado.
	// Uma DESPESA será uma saída que tem o valor considerado.
	@Column(name = "VALOR_CONSIDERADO")
	private boolean valorConsiderado = true;

	@Column(name = "DATA")
	@Temporal(value = TemporalType.DATE)
	private Date data;

	// No caso de uma despesa a data do pagamento,
	// Nas despesas de cartão de credito a data do pagamento,
	// da fatura
	@Column(name = "DATA_EFETIVACAO")
	@Temporal(value = TemporalType.DATE)
	private Date dataPagamento;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false, length = 1)
	private LancamentoTipoEnum tipo;

}
