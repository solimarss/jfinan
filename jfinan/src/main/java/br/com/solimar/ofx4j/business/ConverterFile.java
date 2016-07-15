package br.com.solimar.ofx4j.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConverterFile {

	public static void main(String[] args) throws IOException {
		File fileSource = new File("/home/solimarss/Downloads/extrato.ofx");
		File fileTarget = new File("/home/solimarss/Downloads/extrato-utf8.ofx");
		ConverterFile.transform(fileSource, "ISO-8859-1", fileTarget, "UTF-8");
	}
	
	public static void transform(File source, String srcEncoding, File target, String tgtEncoding) throws IOException {
	    try (
	      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), tgtEncoding)); ) {
	          char[] buffer = new char[16384];
	          int read;
	          while ((read = br.read(buffer)) != -1)
	              bw.write(buffer, 0, read);
	    } 
	}

}
