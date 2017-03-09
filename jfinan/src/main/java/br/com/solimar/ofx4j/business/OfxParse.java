package br.com.solimar.ofx4j.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;

/**
 *
 * @author vielmond
 */
public class OfxParse implements Serializable {

	private static final long serialVersionUID = 5088876124317022485L;
	private static InputStreamReader r;

	public static void main(String[] args) throws IOException, OFXParseException {
		try {

			
			
			File fileSource = new File("/home/solimar/git/jfinan/jfinan/doc/corrente.ofx");
			File fileTarget = new File("/home/solimar/git/jfinan/jfinan/doc/corrente-utf8.ofx");
			
			//File fileSource = new File("/home/solimar/git/jfinan/jfinan/doc/poupanca.ofx");
			//File fileTarget = new File("/home/solimar/git/jfinan/jfinan/doc/poupanca-utf8.ofx");
			
			
			//Verifica se há a indicação de timezone no arquivo
			System.out.println("TEM TIME ZONE: "+Util.arquivoPossuiTesto(fileTarget, "[-3:BRT]"));
			
			
			ConverterFile.transform(fileSource, "ISO-8859-1", fileTarget, "UTF-8");
			

			r = new InputStreamReader(new FileInputStream(fileTarget));
			System.out.println("Charset: "+r.getEncoding());

			

			AggregateUnmarshaller<ResponseEnvelope> a = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

			ResponseEnvelope re = (ResponseEnvelope) a.unmarshal(new FileInputStream(fileTarget));

			ResponseMessageSet messageSet = re.getMessageSet(MessageSetType.banking);

			if (messageSet != null) {
				List<BankStatementResponseTransaction> bank = ((BankingResponseMessageSet) messageSet).getStatementResponses();
				
				for (BankStatementResponseTransaction b : bank) {
					System.out.println("Conta: " + b.getMessage().getAccount().getAccountNumber());
					System.out.println("Agência: " + b.getMessage().getAccount().getBranchId());
					System.out.println("Balanço Final: " + b.getMessage().getLedgerBalance().getAmount());
					System.out.println("Data do Arquivo: " + b.getMessage().getLedgerBalance().getAsOfDate());
					List<Transaction> list = b.getMessage().getTransactionList().getTransactions();
					System.out.println("\nTRANSAÇÕES\n");

					for (Transaction transaction : list) {
						System.out.println("TIPO:      " + transaction.getTransactionType().name());
						System.out.println("ID:        " + transaction.getId());
						System.out.println("DOCUMENTO: " + transaction.getReferenceNumber());
						System.out.println("DATA:      " + new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDatePosted()));
						System.out.println("DATA:      " + transaction.getDatePosted());
						System.out.println("VALOR:     " + transaction.getAmount());
						System.out.println("DESCRIÇÃO: " + transaction.getMemo());
						System.out.println("");
					}
				}
			}
		} catch (OFXParseException | IOException e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}

	}
}
