package arvoresGenericas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Dados {
	public ArrayList<String> leituraArquivo() throws IOException {
		String item = "";
		String desc = "";//descrição
		int cont = 0; //
		ArrayList<String> listaLinhas = new ArrayList<>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("livro.txt"));
			while(br.ready()){
				String linha = br.readLine();
				String[] teste = linha.split(" ");//quebra por espaços
				item = teste[0];
				desc = teste[1];
					for(int i=2;i<teste.length;i++) {//inicia em 2, pq a posição 0 e 1 já pegou
						if(teste[i]!=null) {
							desc += " " + teste[i];
						}
					}
				
				listaLinhas.add(item);
				listaLinhas.add(desc);
				listaLinhas.add("\n");
			}
			br.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		} 
		return listaLinhas;
	}
}
