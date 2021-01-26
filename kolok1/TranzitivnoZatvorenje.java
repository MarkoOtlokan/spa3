import edu.princeton.cs.algs4.*;
import java.util.*;

public class TranzitivnoZatvorenje {
    private Digraph dg;
    private boolean marked[];
    private Digraph res;
    
    TranzitivnoZatvorenje(Digraph dg) {
        this.dg=dg;
        marked=new boolean[dg.V()];
        res=new Digraph(dg.V());
        tran();
    }  
    
    private void restartMarked() {
        for(int i=0; i<dg.V(); i++)
            marked[i]=false;
    }  
    
    private void addEdges(int v) {
        for(int i=0; i<dg.V(); i++) {
            if(marked[i]) {
                res.addEdge(v, i);
            }
        }
    }
    
    private void tran() {
        for(int v=0; v<dg.V(); v++) {
            dfs(v);
            addEdges(v);
            restartMarked();
        }
    }
    
    private void dfs(int v) {
         marked[v]=true;
        for(int w : dg.adj(v)) {
            if(!marked[w]) {
                dfs(w);
            }
        }
    }
    
    public Digraph getRes() {
        return res;
    }
    
    public static void main(String [] arguments) {
        Digraph dg=new Digraph(new In("dag1.txt"));
        TranzitivnoZatvorenje tz=new TranzitivnoZatvorenje(dg);
        System.out.println(dg+"\n\n\n");
        System.out.println(tz.getRes());
    }
}