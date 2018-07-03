package arvoresGenericas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class App {
	public static void main(String[] args) throws IOException {
        GeneralTreeOfInteger arv = new GeneralTreeOfInteger();
        arv.add(1, null);
        arv.add(2, 1);
        arv.add(3, 1);
        arv.add(4, 2);
        arv.add(6, 2);
        arv.add(0, null);
        arv.add(8, 6); 
        arv.add(7, 3); 
        arv.add(5, 2);
        Dados teste = new Dados();
            try {
                    ArrayList<String> lista = new ArrayList<>();
                    lista = teste.leituraArquivo();
                    Livro l1 = new Livro(lista);// instancia objeto livro com o array retornado de Dados

//                    for(String linha : lista) {
//                            if(!linha.equals("\n"))//quando for diferente de \n
//                                    System.out.print(linha + "\n");
//                            //Formato que retorna o ArrayList = "L" "Big Java" "\n"
//                    }

            }catch(Exception e) {
                    System.out.println("Exce��o"+e);
            }

        

        
        
        ArrayList<Integer> l = arv.positionsWidth();
        System.out.println(l);
        
    }
}
