import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph di) {
        DirectedCycle dc = new DirectedCycle(di);
        if (!dc.hasCycle()) {
            DFO dfo = new DFO(di);
            order = dfo.reversePost();
        }
    }
    
    public Iterable<Integer> order() {
        return order;
    }
    
    public boolean isDAG() {
        return order == null;
    }
    
    public static void main(String[] a) {
        In in = new In("dag2.txt");
        Digraph di = new Digraph(in);
        
        System.out.println(di);
        
        Topological top = new Topological(di);
        
        System.out.println(top.isDAG());
        
    }
}
