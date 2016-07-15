package br.com.solimar.ofx4j.xml;

import javax.xml.bind.annotation.XmlElement;



//STMTTRNRS

public class BankStatementResponseTransaction {
	
	
	private String UID;

	@XmlElement(name="TRNUID")
	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}
	
	
	
	

}
