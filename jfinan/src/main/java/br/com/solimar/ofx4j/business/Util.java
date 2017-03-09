package br.com.solimar.ofx4j.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Util {

	static public boolean arquivoPossuiTesto(File arquivo, String texto) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		String linha;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(texto)) {
				return true;
			}
			
		}
		return false;

	}
}
