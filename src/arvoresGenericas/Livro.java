package arvoresGenericas;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Livro {

    private GeneralTreeOfString livro = new GeneralTreeOfString();
    private String titulos;
    private String paragrafo;
    private String titulo;
    private String capitulo;
    private String secao;
    private String subsecao;
    int cont = 0;
    ArrayList<String> dados;
    ArrayList<String> sumario = new ArrayList<>();
    ArrayList<Integer> paginaParaSumario = new ArrayList<>();
    ArrayList<String> livroConsole = new ArrayList<>();

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
        	if(!este.equals("\n")) {
	        	if (este.equals(titulo) || este.equals(capitulo) || este.equals(secao) || este.equals(paragrafo) || este.equals(subsecao)) {
	            	ultimo = este;
	            	continue;//vai pra prï¿½xima iteraï¿½ï¿½o do foreach
	//                titAnterior = este;
	        	} else if (ultimo.equals(titulo)) {//se for um tï¿½tulo
	        		livro.add("L&"+este,null);//add o tï¿½tulo que serï¿½ root
	        	}else if(ultimo.equals(capitulo)) {//se for um capitulo
	        		livro.add("C&"+este, livro.getRoot());//filho da raiz
	                capAnterior = "C&"+este;//ultimo capitulo acessado
	            }else if (ultimo.equals(secao)) {//se for uma secao
	                livro.add("S&"+este, capAnterior);//filho do capitulo anterior
	                secAnterior="S&"+este;// ultima secao acessada
	                subAnterior = null;//limpa a subsecao, pq se chegou aqui ï¿½ pq jï¿½ fechou a sub
	            }else if (ultimo.equals(paragrafo)) {
	            	if(subAnterior==null)
	            		livro.add("P&"+este, secAnterior);//filho da sessao anterior
	            	else //se tiver subseï¿½ï¿½o
	            		livro.add("P&"+este, subAnterior);//filho da sessao anterior
	                paragAnterior = "P&"+este;
	            }else if (ultimo.equals(subsecao)) {
	                livro.add("SS&"+este, secAnterior);
	                subAnterior = "SS&"+este;
	            }
        	} else continue;//se achar um \n continua

        }

        System.out.println("Gerando a arvore...ok");
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
  
        String[] arquivo= null;
       
        for (String a : livro.positionsPre()) {
            arquivo = a.split("&");
            if (arquivo[0].equals(titulo)) {
                cont = cont + 15;
                
                paginaParaSumario.add(getPagina(cont));
                //System.out.println(arquivo[0]);
                contTitulos++;
            }
            if (arquivo[0].equals(capitulo)) {
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
                //System.out.println(arquivo[0]);
                contCapitulos++;
            }
            if (arquivo[0].equals(secao)) {
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
               // System.out.println(arquivo[0]);
                contSecoes++;
            }
            if (arquivo[0].equals(subsecao)){
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
               // System.out.println(arquivo[0]);
                contSubsecoes++;
            }
            if (arquivo[0].equals(paragrafo)) {
                int tamParag = Integer.parseInt(arquivo[1]);
                cont = cont + tamParag;
                paginaParaSumario.add(getPagina(cont));
                //System.out.println(arquivo[0]);
                contParagrafos++;
            }

        }
        int paginas;
        paginas = getPagina(cont);
    
        System.out.println(" Capitulos...: "+contCapitulos);
        System.out.println(" Secoes...: "+contSecoes);
        System.out.println(" Subsecoes...: "+contSubsecoes);
        System.out.println(" Paragrafos...: "+contParagrafos);
        System.out.println("Gerando Sumario... ok");
        System.out.println("Imprimindo o livro para o arquivo livro_prod.txt...ok");
        System.out.println("-------------------------");

        geraSumario();
        System.out.println();
        System.out.println();
        System.out.println("Este livro foi para o arquivo:");
        imprimeLivroConsole ();
    }
    
    public Integer getPagina(Integer num) {
    	int paginas = 0;
    	
    	if (num%15==0) 
    		paginas=num/15;
        else 
        	paginas=(num/15)+1;
    	
    	return paginas;
    }
    
    public void geraSumario() {        
        int contC = 0;
        int contS = 0;
        int contSS = 0;
        int contP = 0;
        int contPag = 0;
        int aux = 1;
        String pontos;
    	ArrayList<String> linhas = livro.positionsPre();
        
        String[] arquivo= null;
        
        for (String a : linhas) {
            arquivo = a.split("&");
            if (arquivo[0].equals(capitulo)) {
                contC++;
                
               pontos = geraPontos(arquivo[1].length());
                sumario.add(contC+". "+arquivo[1]
                		+pontos
                		+paginaParaSumario.get(aux));
                aux++;
                contS = 0;//reinicio cont de seï¿½ï¿½o
                contSS=0;//reinicio cont de SS
            }
            if (arquivo[0].equals(secao)) {
                contS++;
       
                pontos = geraPontos(arquivo[1].length()+3);
                sumario.add(" "+contC+"."+contS+". "+arquivo[1]
                		+pontos
                		+paginaParaSumario.get(aux));
                aux++;
            }
            if (arquivo[0].equals(subsecao)){
            	contSS++;

            	pontos = geraPontos(arquivo[1].length()+6);
                sumario.add("  "+contC+"."+contS+"."+contSS+". "+arquivo[1]
                		+pontos
                		+paginaParaSumario.get(aux));
                aux++;
            }
            if (arquivo[0].equals(paragrafo)) {
                contP++;
                aux++;
            }

        }
        
//        int cc= 0;
//        System.out.println("SUMÁRIO");
//        for(String b: sumario) {
//        	cc++;
//        	System.out.print(b);
//        	if(cc==2) {
//        		System.out.println("\n");
//        		cc=0;
//        	}
//        }        
    }

    
    public String geraPontos(int tam){
    	String pontos = ".";
    	for(int i=0;i<(30-tam);i++) {
    		pontos = pontos.concat(".");
    	}
    	return pontos;
    }
    
    public void imprimeLivroConsole () {
        int contC = 0;
        int contS = 0;
        int contSS = 0;
        int contP = 0;
        int contPag = 0;
        int aux = 1;

        String[] arquivo= null;
        
        livroConsole.add("------------------------------");
        for (String a : livro.positionsPre()) {
            arquivo = a.split("&");
            if (arquivo[0].equals(titulo)){
                for (aux=1;aux<=15;aux++){
                    if(aux==7){
                        String espaco = geraEspacos(arquivo[1].length());
                        livroConsole.add(aux+espaco+arquivo[1]+espaco);
                    }
                    else livroConsole.add(aux+"");

                }
                if (aux>=15){
                    livroConsole.add("------------------------------ Capa.");
                    aux = 1;
                    //contPag++;
                }

            }
            if (arquivo[0].equals(capitulo)) {
                contC++;
                livroConsole.add(aux+"   "+contC+". "+arquivo[1]);
                contS = 0;//reinicio cont de seï¿½ï¿½o
                contSS=0;//reinicio cont de SS
                if (aux>=15){
                    livroConsole.add("------------------------------ Pg." + ++contPag);
                    aux = 1;
                    //contPag++;
                }
                aux++;
            }
            if (arquivo[0].equals(secao)) {
                contS++;
                if(aux>9)
                	livroConsole.add(aux+"  "+contC+"."+contS+". "+arquivo[1]);
                if(aux<=9)
                	livroConsole.add(aux+"   "+contC+"."+contS+". "+arquivo[1]);
                if (aux>=15){
                    livroConsole.add("------------------------------ Pg." + ++contPag);
                    aux = 1;
                   // contPag++;
                }
                aux++;
            }
            if (arquivo[0].equals(subsecao)){
                contSS++;
                if(aux>9)//só para formatação do que vai ser impresso no arq
                	livroConsole.add(aux+"  "+contC+"."+contS+"."+contSS+". "+arquivo[1]);
                else if(aux<=9)
                	livroConsole.add(aux+"   "+contC+"."+contS+"."+contSS+". "+arquivo[1]);
                if (aux>=15){
                    livroConsole.add("------------------------------ Pg." + ++contPag);
                    aux = 1;
                    //contPag++;
                }
                aux++;

            }
            if (arquivo[0].equals(paragrafo)) {
                contP++;
                int numLinhas = Integer.parseInt(arquivo[1]);

                for (int f=1; f<=numLinhas;f++){
                	 if(aux>9)//só para formatação do que vai ser impresso
                		 livroConsole.add(aux+"  "+"Lorem Ipsum " + f);
                	 else if(aux<=9)//só para formatação do que vai ser impresso
                		 livroConsole.add(aux+"   "+"Lorem Ipsum " + f);
                	 
                    if (aux>=15){
                        livroConsole.add("------------------------------ Pg." + ++contPag);
                        aux = 1;
                       // contPag++;
                    }
                    aux++;
                }

            }

        }
        
        livroConsole.add("------------------------------ Pg." + ++contPag);
        livroConsole.add("SUMÁRIO");
        for(String b: sumario) {
        	livroConsole.add(b);
        } 
        
        for(String b: livroConsole) {

            System.out.println(b);
        }
        
        try {
			salvaArqLivro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void salvaArqLivro() throws IOException{
        FileWriter arq = new FileWriter("final.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
   
        for(String esse : livroConsole) {
        	gravarArq.printf(esse+"%n");
        }
     
        arq.close();
     
        System.out.printf("\nGravado");
    }

    public String geraEspacos(int tam) {
        String espaco = " ";
        for (int i = 0; i < (20 - tam); i++) {
            espaco = espaco.concat(" ");
        }
        return espaco;
    }
}
