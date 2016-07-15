package br.com.solimar.ofx4j.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="OFX")
public class ResponseEnvelope {
	
	
	
	private BankingResponseMessageSet bankingResponseMessageSet;

	
	
	
	//@XmlElementWrapper(name="records")
	//@XmlElement(name="data")
	
	@XmlElement(name="BANKMSGSRSV1")
	public BankingResponseMessageSet getBankingResponseMessageSet() {
		return bankingResponseMessageSet;
	}

	public void setBankingResponseMessageSet(BankingResponseMessageSet bankingResponseMessageSet) {
		this.bankingResponseMessageSet = bankingResponseMessageSet;
	}
	
	
	

}
