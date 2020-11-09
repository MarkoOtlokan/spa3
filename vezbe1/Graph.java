import java.util.*;
import java.io.*;

public class Graph {

    private int v;
    private int e;
    // koristimo listu skupova suseda
    private List<HashSet<Integer>> neighbours;
    private boolean[] niz;
    private List<ArrayList<Integer>> path;
    private Stack<Integer> stack = new Stack<Integer>();

    public Graph(int v){
        this.v = v;
        neighbours = new ArrayList<>(v);
    }

    public Graph(String imef) {
    	BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(new File(imef)));
            v = Integer.parseInt(br.readLine());
            e = Integer.parseInt(br.readLine());

            neighbours = new ArrayList<>(v);
            niz = new boolean[v];

            // inicijalizacija svih skupova suseda
            for(int i = 0; i < v; i++) {
                neighbours.add(new HashSet<Integer>());
            }

            String pom;

            while((pom = br.readLine()) != null) {
                String[] par = pom.split(" ");
                int v1 = Integer.parseInt(par[0]);
                int v2 = Integer.parseInt(par[1]);

                addEdge(v1, v2);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                br.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean addEdge(int a, int b){

        try{
            neighbours.get(a).add(b);
            neighbours.get(b).add(a);

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public HashSet<Integer> getNeighbours(int vertex){
        //moglo da se napravi da vraca Iterable<Integer>

        /*
        ako je bitno da niko ne dira internu strukturu,
        moze se koristiti Collections.unmodifiableSet, koji
        ce napraviti omotac, i nece dati da se menja original
         */
        return neighbours.get(vertex);
    }

    public int getV(){
        return v;
    }

    public boolean findDR(int vertex, int goal){//dfs recursion
      for(int i=0; i < niz.length; i++){
        niz[i] = false;
      }
      //System.out.println(vertex);
      return dfs(vertex, goal);

      //return false;
    }

    private boolean dfs(int vertex, int goal){
//      System.out.println(vertex);
//      for(int i=0; i < niz.length; i++){
//        System.out.println(niz[i]);
//      }
      if(vertex == goal) {
        System.out.println(vertex);
        return true;
      }
      if(niz[vertex]) return false;
      niz[vertex] = true;
      HashSet<Integer> next= neighbours.get(vertex);
      System.out.println(vertex);
      System.out.println(next);
      Iterator it = next.iterator();
      while(it.hasNext()){
        Integer n = (Integer)it.next();
        if(dfs(n, goal)){
          System.out.println(vertex);
          return true;
        };
      }
      return false;
    }

    public boolean findDI(int vertex, int goal){
      for(int i=0; i < niz.length; i++){
        niz[i] = false;
      }
      stack.clear();
      stack.add(vertex);
      while(!stack.empty()){
        //if(stack.empty()) return false;
        Integer current = stack.pop();
        if(niz[current]) continue;
        niz[current] = true;
        if(current == goal) {
      //    System.out.println(vertex);
          return true;
        }

        HashSet<Integer> next= neighbours.get(current);
      //  System.out.println(current);
      //  System.out.println(next);
        Iterator it = next.iterator();
        while(it.hasNext()){
          Integer n = (Integer)it.next();
        //  niz[n] = true;
          stack.add(n);
        }

      }
      return false;
    }

    public boolean findBI(Integer vertex, Integer goal){
      Queue<Integer> q = new LinkedList<>();
      for(int i=0; i < niz.length; i++){
        niz[i] = false;
      }
      q.add(vertex);
      while(!q.isEmpty()){
        Integer current = q.remove();
        if(niz[current]) continue;
        niz[current] = true;
        if(current == goal){
          System.out.println(vertex);
          return true;
        }

        HashSet<Integer> next= neighbours.get(current);
        Iterator it = next.iterator();
        while(it.hasNext()){
          Integer n = (Integer)it.next();
          q.add(n);
        }

      }
      return false;
    }

    public void printGraph(){
        System.out.println("Graph has " + v + " vertices.");
        for(int i = 0; i < v; i++) {
            Set<Integer> n = neighbours.get(i);
            for(int j : n) {
                System.out.println("edge: " + i + "-" + j);
            }
        }
    }

    public static void main(String [] arguments) {
        Graph g = new Graph("tinyG.txt");
      //  g.printGraph();
        int x = 2;
        int y = 10;
        g.findDR(x, y);
        System.out.println(g.findDI(x,y));
        System.out.println(g.findBI(x,y));
    }
}
