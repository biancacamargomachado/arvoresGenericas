package arvoresGenericas;


import java.util.ArrayList;
import java.util.LinkedList;

public class Livro {

    private GeneralTreeOfString livro = new GeneralTreeOfString();;
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

    public void geraArvore(){
    	
        String capAnterior = "";//ultimo capitulo acessado
        String secAnterior = "";//ultima secao acessada
        String subAnterior = "";//ultima secao acessada
        String titAnterior = "";//ultimo titulo acessado
        String paragAnterior = "";//ultimo paragrafo acessado
        String ultimo = "";//salvo ultimo item acessado, L, P, S, SS...
        
        for (String este : dados) {
            // equals "/n"
        	if(!este.equals("\n")) {
	        	if (este.equals(titulo) || este.equals(capitulo) || este.equals(secao) || este.equals(paragrafo) || este.equals(subsecao)) {
	            	ultimo = este;
	            	continue;//vai pra próxima iteração do foreach
	//                titAnterior = este;
	        	} else if (ultimo.equals(titulo)) {//se for um título
	        		livro.add("L&"+este,null);//add o título que será root
	        	}else if(ultimo.equals(capitulo)) {//se for um capitulo
	        		livro.add("C&"+este, livro.getRoot());//filho da raiz
	                capAnterior = "C&"+este;//ultimo capitulo acessado
	            }else if (ultimo.equals(secao)) {//se for uma secao
	                livro.add("S&"+este, capAnterior);//filho do capitulo anterior
	                secAnterior="S&"+este;// ultima secao acessada
	                subAnterior = null;//limpa a subsecao, pq se chegou aqui é pq já fechou a sub
	            }else if (ultimo.equals(paragrafo)) {
	            	if(subAnterior==null)
	            		livro.add("P&"+este, secAnterior);//filho da sessao anterior
	            	else //se tiver subseção
	            		livro.add("P&"+este, subAnterior);//filho da sessao anterior
	                paragAnterior = "P&"+este;
	            }else if (ultimo.equals(subsecao)) {
	                livro.add("SS&"+este, secAnterior);
	                subAnterior = "SS&"+este;
	            }
        	} else continue;//se achar um \n continua
          

//            if (linhas.get(key).equals(paragrafo)) paragrafos.add(linhas.get(key));
//            else if (linhas.get(key).equals(secao)) secoes.add(linhas.get(key));
//            else if (linhas.get(key).equals(subsecao)) subsecoes.add(linhas.get(key));
//            else if (linhas.get(key).equals(capitulo)) capitulos.add(linhas.get(key));
//            this.cont++;

        }
        
        for(String a: livro.positionsPre()) {
        	System.out.println(a);
        }
    }

    public void geraSumario (GeneralTreeOfString livro){
        ArrayList<String> linhas = livro.positionsPre();
        for (String a :linhas) {
            String[] arquivo = a.split("&");
            if (arquivo[0].equals(titulo)){
                cont=15;
            }
            if (arquivo[0].equals(capitulo)){
                cont=1;
            }
            if (arquivo[0].equals(secao)){}
            if (arquivo[0].equals(paragrafo)){}

        }
    }

    public void imprimeLivro (){}
    // if ttiulo imprime uma pagina
    // imprime pÃ¡gina 1
    // imprimeSumario()

}
