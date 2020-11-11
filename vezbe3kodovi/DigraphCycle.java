
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DigraphCycle {

    private Digraph digraph;
    private Stack<Integer> graphCycle;
    private List<Integer> visitedNodes = new ArrayList<>();
    private HashMap<Integer, Integer> parentOfNode = new HashMap<>();


    public DigraphCycle(Digraph digraph) {
        this.digraph = digraph;
    }

    //metoda prolanazi konturu u grafu ako postoji
    public boolean findCycle(int u, int v) {

        //oznaci cvor V da je posecen
        visitedNodes.add(v);
        //za svako dete W cvora V
        for (int w : digraph.adj(v)) {

            //ako je vec pronadjena neka kontura, prekini izvrsavanje
            if (graphCycle != null) {
                return true;
            }

            //ako dete W nije poseceno do sada, oznaci ko je njegov roditelj, nastavi dalje
            if (!visitedNodes.contains(w)) {
                parentOfNode.put(w, v);
                findCycle(v, w);
            }
            //ako je dete Wi vec bilo poseceno, ali nije paralelna grana (jer to onda nije kontura)
            // tj. nije U -> V -> {W1, W2, ...}, gde je U neko iz skupa {W1, W2, ...}
            //onda je nadjena jedna kontura
            else if (w != u) {
                graphCycle = new Stack<Integer>();
                for (int p = v; p != w; p = parentOfNode.get(p)) {
                    graphCycle.push(p);
                }
                graphCycle.push(w);
                graphCycle.push(v);
            }
        }
        return false;
    }


    public Stack<Integer> getGraphCycle() {
        return graphCycle;
    }
}
