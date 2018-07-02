package arvoresGenericas;

import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
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
        ArrayList<Integer> l = arv.positionsWidth();
        System.out.println(l);
        
    }
}
