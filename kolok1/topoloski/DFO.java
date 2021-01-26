import java.util.*;
import edu.princeton.cs.algs4.Digraph;

public class DFO {

    private boolean[] marked;
    
    private Stack<Integer> reversePost;

    public DFO(Digraph di) {
        reversePost = new Stack<Integer>();
        int n = di.V();
        
        marked = new boolean[n];
        
        for (int v = 0; v < n; v++) {
            if (!marked[v])
                dfs(di, v);
        }
    }
    
    private void dfs(Digraph di, int v) {
        marked[v] = true;
        
        for (int w : di.adj(v)) {
            if (!marked[w]) {
                dfs(di, w);
             }
        }
        
        reversePost.push(v);
    }
    
    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
