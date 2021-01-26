import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;
    
    private Stack<Integer> reversePost;

    public DirectedCycle(Digraph di) {
        int n = di.V();
        
        marked = new boolean[n];
        edgeTo = new int[n];
        onStack = new boolean[n];
        
        reversePost = new Stack<Integer>();        
        for (int v = 0; v < n; v++) {
            if (!marked[v]) {
                dfs(di, v);
            }
        }
    }
    
    private void dfs(Digraph di, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : di.adj(v)) {
            if (this.hasCycle())
                return;
            else if (!marked[w]) {
               edgeTo[w] = v;
                dfs(di, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
       }
       onStack[v] = false;
       reversePost.push(v);
    }
    
    public boolean hasCycle() {
        return cycle != null;
    }
    
    public Iterable<Integer> cycle() {
        return cycle;
    }
    
    public static void main(String[] a) {
        In in = new In("digraf-komp5.txt");
        Digraph di = new Digraph(in);
        
        DirectedCycle dc = new DirectedCycle(di);
        
        Svetovid.out.println(dc.cycle());
    }
}
