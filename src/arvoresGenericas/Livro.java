package arvoresGenericas;


import java.util.ArrayList;
import java.util.LinkedList;

public class Livro {

    private GeneralTreeOfString livro;
    private ArrayList<String> paragrafos;
    private String titulos;
    private String paragrafo;
    private String titulo;
    private String capitulo;
    private String secao;
    private String subsecao;
    int cont = 0;
    ArrayList<String> dados;
    public Livro(ArrayList <String> listaDados) {
        titulo = "L";
        capitulo = "C";
        secao = "S";
        paragrafo = "P";
        subsecao = "SS";
        this.dados = listaDados;

    }

    public void  geraArvore (){
        String capAnterior = "";
        String secAnterior = "";

        for (String este : dados) {
            // equals "/n"


            if (este.equals(titulo)) {
                livro.setRoot(este);

            }
            if (este.equals(capitulo)) {
                livro.add(este, livro.getRoot());
                capAnterior = este;//ultimo capitulo acessado
            }
            if (este.equals(secao)) {
                livro.add(este, livro.getFaher(capAnterior));
                secAnterior=este;// ultima secao acessada
            }
            if (este.equals(paragrafo)) {
                livro.add(este, livro.getFaher(secAnterior));

            }
//            if (este.equals(subsecao)) {
//                livro.add(este, livro.getFaher(secAnterior)); // fazer depois
//
//            }
            if (este.equals(paragrafo)) {
                livro.add(este, livro.getFaher(secAnterior));

            }




//            if (linhas.get(key).equals(paragrafo)) paragrafos.add(linhas.get(key));
//            else if (linhas.get(key).equals(secao)) secoes.add(linhas.get(key));
//            else if (linhas.get(key).equals(subsecao)) subsecoes.add(linhas.get(key));
//            else if (linhas.get(key).equals(capitulo)) capitulos.add(linhas.get(key));
//            this.cont++;

        }
    }

    public void imprimeSumario (){}

    public void imprimeLivro (){}
    // if ttiulo imprime uma pagina
    // imprime página 1
    // imprimeSumario()
}
