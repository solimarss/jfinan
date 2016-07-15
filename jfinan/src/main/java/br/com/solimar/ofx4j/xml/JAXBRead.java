package br.com.solimar.ofx4j.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBRead {
	public static void main(String[] args) {

	 try {

		 
		//File file = new File("/home/solimarss/temp/xml/file.xml");
		 File file = new File("/home/solimarss/temp/xml/extrato.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(ResponseEnvelope.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ResponseEnvelope envelope = (ResponseEnvelope) jaxbUnmarshaller.unmarshal(file);
		
		System.out.println(envelope.getBankingResponseMessageSet().getStatementResponses().get(0).getUID());

	  } catch (JAXBException e) {
		e.printStackTrace();
	  }

	}
}
