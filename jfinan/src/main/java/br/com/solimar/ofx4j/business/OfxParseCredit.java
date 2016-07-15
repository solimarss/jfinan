package br.com.solimar.ofx4j.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.domain.data.creditcard.CreditCardResponseMessageSet;
import net.sf.ofx4j.domain.data.creditcard.CreditCardStatementResponse;
import net.sf.ofx4j.domain.data.creditcard.CreditCardStatementResponseTransaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;

/**
 *
 * @author vielmond
 */
public class OfxParseCredit implements Serializable {

	private static final long serialVersionUID = 5088876124317022485L;
	private static InputStreamReader r;

	public static void main(String[] args) throws IOException, OFXParseException {
		try {

			//File file = new File("/home/solimarss/Downloads/extrato.ofx");
			
			File fileSource = new File("/home/solimarss/Downloads/OUROCARD.ofx");
			File fileTarget = new File("/home/solimarss/Downloads/OUROCARD-utf8.ofx");
			
			ConverterFile.transform(fileSource, "ISO-8859-1", fileTarget, "UTF-8");
			

			r = new InputStreamReader(new FileInputStream(fileTarget));
			System.out.println("Charset: "+r.getEncoding());

			

			AggregateUnmarshaller<ResponseEnvelope> a = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

			ResponseEnvelope envelope = (ResponseEnvelope) a.unmarshal(new FileInputStream(fileTarget));

			CreditCardResponseMessageSet messageSet = (CreditCardResponseMessageSet) envelope.getMessageSet(MessageSetType.creditcard);

			List<CreditCardStatementResponseTransaction> responses = messageSet.getStatementResponses();
			
			for (CreditCardStatementResponseTransaction response : responses) {
				
				CreditCardStatementResponse message = response.getMessage();
				
				String currencyCode = message.getCurrencyCode();
				List<Transaction> transactions = message.getTransactionList().getTransactions();
				for (Transaction transaction : transactions) {
					
					
					System.out.print(transaction.getId() + "         ");
					System.out.print(transaction.getTransactionType() + "         ");
					System.out.print(new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDatePosted()) +"        ");
					System.out.print(transaction.getMemo() + "         ");
					System.out.print(transaction.getAmount() + "        " + currencyCode);
					System.out.print("\n");
					
				}
			}
			
		} catch (OFXParseException | IOException e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}

	}
}
