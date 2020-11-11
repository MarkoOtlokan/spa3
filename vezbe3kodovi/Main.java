
import com.pmf.spa3.components.GraphComponent;
import com.pmf.spa3.cycle.DigraphCycle;
import edu.princeton.cs.algs4.Digraph;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Digraph digraph1 = new Digraph(3);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(0, 2); 
        digraph1.addEdge(1, 0);
//        digraph1.addEdge(1, 2);
//        digraph1.addEdge(2, 0);
//        digraph1.addEdge(2, 1);

        /* prebrojati koliko ima komponenti u usmerenom grafu */
        GraphComponent graphComponent = new GraphComponent(digraph1);
        graphComponent.findAllComponents();
        List<Set<Integer>> components = graphComponent.getComponents();
        System.out.println("Broj komponenti je: " + components.size());


        /* ispisati koje su komponente (grupe cvorova) */
        System.out.println("*** KOMPONENTE GRAFA ***");
        for(Set<Integer> c : components){
            System.out.println(c);
        }

        /* konture i topoloski sort */
        DigraphCycle digraphCycle = new DigraphCycle(digraph1);
        if (digraph1.V() > 1) {
            boolean cycled = digraphCycle.findCycle(0, 1);

            /* da li postoji kontura (cycle) i dati jednu ako postoji */
            System.out.println(cycled ? "Povezani graf IMA konturu!" : "Povezani graf NEMA konturu!");
            if (cycled) {
                System.out.println("*** KONTURA ***");
                System.out.println(digraphCycle.getGraphCycle().toString());
            }

            /* topoloski sort digrafa, ako je moguce (tj ako nema kontura) */
            System.out.println(!cycled ? "POSTOJI topoloski sort!" : "NE POSTOJI topoloski sort!");

        }
    }

}
