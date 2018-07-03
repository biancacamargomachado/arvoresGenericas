package arvoresGenericas;


import java.util.ArrayList;
import java.util.LinkedList;

public class Livro {

    private GeneralTreeOfString livro = new GeneralTreeOfString();
    ;
    private ArrayList<String> paragrafos;
    private String titulos;
    private String paragrafo;
    private String titulo;
    private String capitulo;
    private String secao;
    private String subsecao;
    int cont = 0;
    ArrayList<String> dados;

    public Livro(ArrayList<String> listaDados) {
        titulo = "L";
        capitulo = "C";
        secao = "S";
        paragrafo = "P";
        subsecao = "SS";
        this.dados = listaDados;

    }

    public void geraArvore() {

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
	            	continue;//vai pra pr�xima itera��o do foreach
	//                titAnterior = este;
	        	} else if (ultimo.equals(titulo)) {//se for um t�tulo
	        		livro.add("L&"+este,null);//add o t�tulo que ser� root
	        	}else if(ultimo.equals(capitulo)) {//se for um capitulo
	        		livro.add("C&"+este, livro.getRoot());//filho da raiz
	                capAnterior = "C&"+este;//ultimo capitulo acessado
	            }else if (ultimo.equals(secao)) {//se for uma secao
	                livro.add("S&"+este, capAnterior);//filho do capitulo anterior
	                secAnterior="S&"+este;// ultima secao acessada
	                subAnterior = null;//limpa a subsecao, pq se chegou aqui � pq j� fechou a sub
	            }else if (ultimo.equals(paragrafo)) {
	            	if(subAnterior==null)
	            		livro.add("P&"+este, secAnterior);//filho da sessao anterior
	            	else //se tiver subse��o
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

//        for (String a : livro.positionsPre()) {
//            System.out.println(a);
//        }
        geraLivro();
    }

    public void geraSumario(GeneralTreeOfString livro) {


    }


    public void geraLivro() {
        int contTitulos = 0;
        int contCapitulos = 0;
        int contSecoes = 0;
        int contSubsecoes = 0;
        int contParagrafos = 0;

        ArrayList<String> linhas = livro.positionsPre();
        String[] arquivo= null;
        for (String a : linhas) {
            arquivo = a.split("&");
            if (arquivo[0].equals(titulo)) {
                cont = cont + 15;
                System.out.println(arquivo[0]);
                contTitulos++;
            }
            if (arquivo[0].equals(capitulo)) {
                cont = cont + 1;
                System.out.println(arquivo[0]);
                contCapitulos++;
            }
            if (arquivo[0].equals(secao)) {
                cont = cont + 1;
                System.out.println(arquivo[0]);
                contSecoes++;
            }
            if (arquivo[0].equals(subsecao)){
                cont = cont + 1;
                System.out.println(arquivo[0]);
                contSubsecoes++;
            }
            if (arquivo[0].equals(paragrafo)) {
                int tamParag = Integer.parseInt(arquivo[1]);
                cont = cont + tamParag;
                System.out.println(arquivo[0]);
                contParagrafos++;
            }


        }

        int paginas;
        if (cont%15==0) paginas=cont/15;
        else paginas=(cont/15)+1;
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Carregando arquivo do Livro.txt...ok");
        System.out.println("Gerando a arvore...ok");
        System.out.println(" Capitulos...: "+contCapitulos);
        System.out.println(" Secoes...: "+contSecoes);
        System.out.println(" Subsecoes...: "+contSubsecoes);
        System.out.println(" Paragrafos...: "+contParagrafos);
        System.out.println("Gerando Sumario... ok");
        System.out.println("Imprimindo o livro para o arquivo livro_prod.txt...ok");
        System.out.println("-------------------------");

        // if ttiulo imprime uma pagina
        // imprime página 1
        // imprimeSumario()

    }
}
