package br.com.solimar.ofx4j.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;




//BANKMSGSRSV1

public class BankingResponseMessageSet {
	
	private List<BankStatementResponseTransaction> statementResponses;

	
	
	@XmlElement(name="STMTTRNRS")
	public List<BankStatementResponseTransaction> getStatementResponses() {
		return statementResponses;
	}

	public void setStatementResponses(List<BankStatementResponseTransaction> statementResponses) {
		this.statementResponses = statementResponses;
	}
	
	
	

}
