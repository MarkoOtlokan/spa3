import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class DigrafKontura {

    private Digraph d;
    private boolean[] posecen;
    
    public DigrafKontura(Digraph d) {
        this.d = d;
        posecen = new boolean[d.V()];
    }
    
    public boolean postojiKontura(){
        boolean postoji = false;
        for(int i = 0; i < d.V(); i++) {
            postoji = postoji || kontura(i, i);
        }
        return postoji;
    }
    
    private boolean kontura(int i, int pocetni) {
        posecen[i] = true;
        boolean k = false;
        for(int s:d.adj(i)){
            if(posecen[s]){
                if(s == pocetni)
                    return true;
                else 
                    return false;
            } else {
                k = k ||kontura(s, pocetni);
            }
        }
        
        return k;
    }
    
    public static void main(String[] args){
        In in = new In("dag1.txt");
        Digraph d = new Digraph(in);
        DigrafKontura dk = new DigrafKontura(d);
        System.out.println(dk.postojiKontura());
    }
}
