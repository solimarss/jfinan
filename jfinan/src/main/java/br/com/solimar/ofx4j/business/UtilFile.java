package br.com.solimar.ofx4j.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UtilFile {

	static public boolean arquivoPossuiTexto(File arquivo, String texto) throws IOException {

		boolean result = false;
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		String linha;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(texto)) {
				result = true;
			}

		}
		reader.close();
		return result;

	}
}
