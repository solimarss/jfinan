package br.com.solimar.ofx4j.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import org.apache.derby.tools.sysinfo;

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

			File fileSource = new File("/home/solimar/git/jfinan/jfinan/doc/cartao.ofx");
			File fileTarget = new File("/home/solimar/git/jfinan/jfinan/doc/cartao-utf8.ofx");
			
			
			//Verifica se há a indicação de timezone no arquivo
			if(!Util.arquivoPossuiTesto(fileTarget, "[-3:BRT]")){
				System.out.println("Não tem indicação de time zone");
				//Alterar a time zone para o timezone zero, pois no arquivo não há indicação de timezone nas datas
				//sem  isso o ofx4j converte as datas com 3 horas a menos pois essa é a timezone do brasil (-3)  
				 TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
				//System.out.println("TIME ZONE DEFAULT: "+TimeZone.getDefault());
			}
			
			

			ConverterFile.transform(fileSource, "ISO-8859-1", fileTarget, "UTF-8");

			r = new InputStreamReader(new FileInputStream(fileTarget));
			System.out.println("Charset: " + r.getEncoding());

			AggregateUnmarshaller<ResponseEnvelope> a = new AggregateUnmarshaller<ResponseEnvelope>(
					ResponseEnvelope.class);

			ResponseEnvelope envelope = (ResponseEnvelope) a.unmarshal(new FileInputStream(fileTarget));

			CreditCardResponseMessageSet messageSet = (CreditCardResponseMessageSet) envelope
					.getMessageSet(MessageSetType.creditcard);

			System.out.println("TIPO DE CARTÃO: " + messageSet.getType());
			System.out.println(
					"INSTITUIÇÃO: " + envelope.getSignonResponse().getFinancialInstitution().getOrganization());

			

			
			System.out.println("");

			List<CreditCardStatementResponseTransaction> responses = messageSet.getStatementResponses();

			for (CreditCardStatementResponseTransaction response : responses) {

				CreditCardStatementResponse message = response.getMessage();

				System.out.println("NÚMERO DO CARTÃO: " + message.getAccount().getAccountNumber());
				System.out.println("VALOR TOTAL : " + message.getLedgerBalance().getAmount());
				System.out.println("DATA VENCIMENTO : " + message.getLedgerBalance().getAsOfDate());
				
						
				
				SimpleDateFormat isoFormat = new SimpleDateFormat("dd/MM/yyyy");
				//isoFormat.setTimeZone(TimeZone.getTimeZone("+3"));
				System.out.println("DATA VENCIMENTO : " + isoFormat.format(message.getLedgerBalance().getAsOfDate()));

				System.out.println("");

				String currencyCode = message.getCurrencyCode();
				List<Transaction> transactions = message.getTransactionList().getTransactions();

				for (Transaction transaction : transactions) {

					System.out.println("ID:    " + transaction.getId());
					System.out.println("TIPO:  " + transaction.getTransactionType());
					System.out.println(
							"DATA:  " + new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDatePosted()));
					System.out.println("MEMO   " + transaction.getMemo());
					System.out.println("VALOR: " + transaction.getAmount() + " " + currencyCode);
					System.out.println("");

				}
			}

		} catch (OFXParseException | IOException e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}

	}
}
