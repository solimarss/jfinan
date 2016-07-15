package br.com.solimar.ofx4j.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBWrite {
	public static void main(String[] args) {

		ResponseEnvelope envelope = new ResponseEnvelope();
		
		
		BankStatementResponseTransaction tr = new BankStatementResponseTransaction();
		tr.setUID("1");
		
		BankingResponseMessageSet rm = new BankingResponseMessageSet();
		
		List<BankStatementResponseTransaction> statementResponses = new ArrayList<BankStatementResponseTransaction>();
		
		statementResponses.add(tr);
		
		rm.setStatementResponses(statementResponses);
		envelope.setBankingResponseMessageSet(rm);

		
		

		try {

			File file = new File("/home/solimarss/temp/xml/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseEnvelope.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(envelope, file);
			jaxbMarshaller.marshal(envelope, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
