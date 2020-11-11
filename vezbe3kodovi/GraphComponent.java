
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphComponent {

    private Digraph digraph;
    private Stack<Integer> graphCycle;
    private List<Integer> visitedNodes = new ArrayList<>();
    private List<Integer> allNodes = new ArrayList<>();
    private List<Set<Integer>> components = new ArrayList<>();
    private List<Set<Integer>> newComponents = new ArrayList<>();


    public GraphComponent(Digraph digraph) {
        this.digraph = digraph;
    }

    //metoda prolanazi kroz graf i generise
    // posecene cvorove u kolekciji "visited"
    private void dfs(int u) {

        //oznaci cvor U da je posecen
        visitedNodes.add(u);

        //za svako dete W cvora U
        for (int w : digraph.adj(u)) {
            if (!visitedNodes.contains(w)) {
                dfs(w);
            }
        }
    }

    public void findAllComponents() {

        //kreiranje liste svih cvorova u grafu
        int n = digraph.V();
        for (int i = 0; i < n; i++) {
            allNodes.add(i);
        }

        int root = 1;
        while (!allNodes.isEmpty()) {
            dfs(root);
            components.add(new HashSet<Integer>(visitedNodes));
            allNodes.removeAll(visitedNodes);
            visitedNodes.clear();
            if (!allNodes.isEmpty()) {
                root = allNodes.get(0);
            }
        }

        //proveriti da li se postoji presek medju cvorovima komponenti
        // jer onda su to iste komponente i treba ih spojiti u jednu
        for (int i = 0; i < components.size(); i++) {
            for (int j = i + 1; j < components.size(); j++) {
                Set<Integer> intersection = new HashSet<>(components.get(i));
                intersection.retainAll(components.get(j));
                if (!intersection.isEmpty()) {
                    Set tmp = new HashSet(components.get(i));
                    tmp.addAll(components.get(j));
                    newComponents.add(tmp);
                }
            }
        }

    }

    public List<Set<Integer>> getComponents() {
        return !newComponents.isEmpty() ? newComponents : components;
    }
}
