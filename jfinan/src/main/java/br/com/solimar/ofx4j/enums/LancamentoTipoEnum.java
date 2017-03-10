package br.com.solimar.ofx4j.enums;

public enum LancamentoTipoEnum {
	
	E("Entrada"), 
	S("Saída");

	private LancamentoTipoEnum(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return this.descricao;
	}
}
