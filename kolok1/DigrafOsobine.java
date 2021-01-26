import edu.princeton.cs.algs4.Digraph;
import java.util.*;
public class DigrafOsobine {

 public static HashSet<Integer> poseceni = null;
 public static HashSet<Integer> cvorovi = null;
 
 public static void main(String [] args) {
      Digraph usmereniGraf = ucitajGraf("digraf-komp5.txt");
        System.out.println("Graf n:\n" +usmereniGraf);
        Digraph tranZat = tranzitivnoZatvorenje(usmereniGraf);
        System.out.println("Tranzitivno zatvorenje grafa n:\n"+tranZat);
        postojiCvorIzKogaSeMozeSticiDoSvihOstalih(tranZat);
        postojiCvorDoKogaSeMozeSticiIzSvihOstalig(tranZat);
 }
 public static void postojiCvorDoKogaSeMozeSticiIzSvihOstalig(Digraph g){
     Digraph g1  = g.reverse();
     for(int i =0; i<g.V(); i++){
         if(g1.outdegree(i)==g.V()-1){
             System.out.println("U cvor "+i+" moze se stici iz svih ostalih cvorova");
         }else{
             System.out.println("U cvor "+i+" ne moze se stici iz svih ostalih cvorova");
         }
     }
 }
 public static void postojiCvorIzKogaSeMozeSticiDoSvihOstalih(Digraph g){
     for(int i =0; i<g.V(); i++){
         if(g.outdegree(i)==g.V()-1){
             System.out.println("Od cvora "+i+" postoje grane do svih ostalih cvorova");
         }else{
             System.out.println("Od cvora "+i+" ne postoje grane do svih ostalih cvorova");
         }
     }
 }
 public static Digraph tranzitivnoZatvorenje(Digraph g){
      Digraph usmereniGraf = new Digraph(g);
      poseceni = new HashSet<Integer>();
      for(int i =0; i<g.V(); i++){
          poseceni = new HashSet<Integer>();
          poseceni.add(i);
          Iterator<Integer> susedi = g.adj(i).iterator();
          while(susedi.hasNext()){
              int sused = susedi.next();
              poseceni.add(sused);
              dfs(sused,i,usmereniGraf,g);
          }
      }
      return usmereniGraf;
  }
  
   public static void dfs(int tekuci,int i,Digraph g, Digraph g1){
        Iterator<Integer> susedi = g1.adj(tekuci).iterator();
        while(susedi.hasNext()){
            int sused = susedi.next();
            if(!poseceni.contains(sused)){
                g.addEdge(i, sused);
                poseceni.add(sused);
                dfs(sused, i, g ,g1);
            }
        }
    }
    public static Digraph ucitajGraf(String fileName){
        int brCvorova = Svetovid.in(fileName).readInt();
        cvorovi = new HashSet<Integer>(brCvorova);
         int brGrana = Svetovid.in(fileName).readInt();
         Digraph g = new Digraph(brCvorova);
         for(int i =0; i<brGrana; i++){
             int node1 = Svetovid.in(fileName).readInt();
             int node2 = Svetovid.in(fileName).readInt();
             cvorovi.add(node1);
             cvorovi.add(node2);
             g.addEdge(node1, node2);
         }
         return g;
    }
   
}
