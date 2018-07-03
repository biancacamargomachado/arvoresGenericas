package arvoresGenericas;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

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
                
                paginaParaSumario.add(getPagina(cont));
                System.out.println(arquivo[0]);
                contTitulos++;
            }
            if (arquivo[0].equals(capitulo)) {
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
                System.out.println(arquivo[0]);
                contCapitulos++;
            }
            if (arquivo[0].equals(secao)) {
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
                System.out.println(arquivo[0]);
                contSecoes++;
            }
            if (arquivo[0].equals(subsecao)){
                cont = cont + 1;
                paginaParaSumario.add(getPagina(cont));
                System.out.println(arquivo[0]);
                contSubsecoes++;
            }
            if (arquivo[0].equals(paragrafo)) {
                int tamParag = Integer.parseInt(arquivo[1]);
                cont = cont + tamParag;
                paginaParaSumario.add(getPagina(cont));
                System.out.println(arquivo[0]);
                contParagrafos++;
            }

        }
        
        int cc= 0;
        System.out.println("SUMÁRIO");
        for(String b: sumario) {
        	cc++;
        	System.out.print(b);
        	if(cc==2) {
        		System.out.println("\n");
        		cc=0;
        	}
        }
        int paginas;
        paginas = getPagina(cont);
    
        System.out.println("Linhas: "+cont);
        System.out.println("Páginas: "+paginas);

        geraSumario();      
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
        
    	ArrayList<String> linhas = livro.positionsPre();
        
        String[] arquivo= null;
        
        for (String a : linhas) {
            arquivo = a.split("&");
            if (arquivo[0].equals(capitulo)) {
                contC++;
                
                sumario.add(contC+". ");
                sumario.add(arquivo[1]+"....."+paginaParaSumario.get(aux));
                aux++;
                contS = 0;//reinicio cont de seção
                contSS=0;//reinicio cont de SS
            }
            if (arquivo[0].equals(secao)) {
                contS++;
                sumario.add(" "+contC+"."+contS+". ");
                sumario.add(arquivo[1]+"....."+paginaParaSumario.get(aux));
                aux++;
            }
            if (arquivo[0].equals(subsecao)){
            	contSS++;
            	sumario.add("  "+contC+"."+contS+"."+contSS+". ");
                sumario.add(arquivo[1]+"....."+paginaParaSumario.get(aux));
                aux++;
            }
            if (arquivo[0].equals(paragrafo)) {
                contP++;
                aux++;
            }

        }
        
        int cc= 0;
        System.out.println("SUMÁRIO");
        for(String b: sumario) {
        	cc++;
        	System.out.print(b);
        	if(cc==2) {
        		System.out.println("\n");
        		cc=0;
        	}
        }        
    }
}
