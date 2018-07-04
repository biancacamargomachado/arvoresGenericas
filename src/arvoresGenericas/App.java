package arvoresGenericas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class App {
	public static void main(String[] args) throws IOException {
        Dados teste = new Dados();
            try {
                    ArrayList<String> lista = new ArrayList<>();
                    lista = teste.leituraArquivo();
                    Livro l1 = new Livro(lista);// instancia objeto livro com o array retornado de Dados
                    l1.geraArvore();
//                    for(String linha : lista) {
//                            if(!linha.equals("\n"))//quando for diferente de \n
//                                    System.out.print(linha + "\n");
//                            //Formato que retorna o ArrayList = "L" "Big Java" "\n"
//                    }

            }catch(Exception e) {
                    System.out.println("Excecao"+e);
            }

        

        
        
//        ArrayList<Integer> l = arv.positionsWidth();
//        System.out.println(l);
        
    }
}
