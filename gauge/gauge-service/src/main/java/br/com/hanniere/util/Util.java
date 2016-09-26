package br.com.hanniere.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;


/**
 * Classe que possui metodos utilitarios da aplicacao.
 * @author Hanniere
 *
 */
public class Util implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Esse metodo faz a leitura de um arquivo .json que esteja dentro da pasta resources "src/main/resources".
	 * @param fileName - Nome do arquivo json a ser lido
	 * @return JSONArray - Array contido dentro do json.
	 */
	public JSONArray readJsonFile(String fileName){

		InputStream jsonFile = getClass().getResourceAsStream("/" + fileName + ".json");
		InputStreamReader crunchifyReader = new InputStreamReader(jsonFile, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(crunchifyReader);
		String line;
		String string = "";
		try {
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new JSONArray(string);
	}
}
