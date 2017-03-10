package br.com.solimar.ofx4j.enums;

public enum ContaTipoEnum {
	
	CREDIT_CARD("Cartão de Crédito"), 
	CHECKING_ACCOUNT("Conta Corrente"), 
	SAVINGS_ACCOUNT("Conta poupança"),
	WALLET("carteira");

	private ContaTipoEnum(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return this.descricao;
	}
}
