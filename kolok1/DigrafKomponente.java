import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class DigrafKomponente {

    private Digraph dig;
    private List<List<Integer>> komponente;
    private int trKomp;
    private boolean[] poseceno;

    public DigrafKomponente(Digraph dig) {
        this.dig = dig;
        komponente = new LinkedList<List<Integer>>();
        trKomp = 0;
        poseceno = new boolean[dig.V()];
    }
    
    public void komponente(){
        for(int i = 0; i < dig.V(); i++) {
            if(!poseceno[i]){
                nadjiKomponentu(i, -1);
                trKomp++;
            }
        }
    }
    
    private void nadjiKomponentu(int i, int nadjenaKomp){
    System.out.println("Cvor " + i + " nadjena komp " + nadjenaKomp);
        poseceno[i] = true;
        if(nadjenaKomp == -1) {
            for(int cv:dig.adj(i)){
                for(int j = 0; j < komponente.size(); j++){
                    List<Integer> komp = komponente.get(j);
                    if(komp.contains(cv)){
                        nadjenaKomp = j;
                    }
                }
            }
        }
        
        if(nadjenaKomp != -1) {
            System.out.print(nadjenaKomp);
            komponente.get(nadjenaKomp).add(i);
        } else {
            LinkedList<Integer> k = new LinkedList<>();
            k.add(i);
            System.out.println("usao u else " +trKomp);
            komponente.add(k);
            nadjenaKomp = trKomp;
        }
        
        for(int cv:dig.adj(i)) {
            if(!poseceno[cv]){
                nadjiKomponentu(cv, nadjenaKomp);
            }
        }
        
    }
    
    public void ispisiKomponente(){
        for(int i = 0; i < komponente.size(); i++) {
            System.out.println("Komponenta " + (i+1));
            for(int j = 0; j < komponente.get(i).size(); j++) {
                System.out.println(komponente.get(i).get(j));
            }
        }
    }
    
    
    public static void main(String[] args) {
        In in = new In("digraf-komp5.txt");
        Digraph d = new Digraph(in);
        DigrafKomponente dk = new DigrafKomponente(d);
        dk.komponente();
        dk.ispisiKomponente();
    }
    
}
