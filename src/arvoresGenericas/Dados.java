package arvoresGenericas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dados {

	public void carregaPaises() throws IOException {
		//System.out.println("### Carregando Aeroportos ###\n\n");
		Path path2 = Paths.get("livro.txt");
		try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.forName("utf8")))) {
		  sc.useDelimiter("[\b\n]"); // separadores: ; e nova linha
		  String header = sc.nextLine(); // pula cabeçalho
		  String codigo, nome;
		  while (sc.hasNext()) {
		    codigo = sc.next();
		    nome = sc.next().replaceAll("(\r)", "");
		    //Pais p = new Pais(codigo, nome);
		   // adicionar(codigo, p);
		   // System.out.format("%s - %s", codigo, nome);
		  }
		}
		catch (IOException x) {
		  System.err.format("Erro de E/S: %s%n", x);
		}
		//System.out.println("### Encerrado Carregento de Paises ###\n\n");
	}
}
