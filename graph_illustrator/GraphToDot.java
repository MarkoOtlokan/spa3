import edu.princeton.cs.algs4.*;
import java.nio.file.*;
import java.io.*;

public class GraphToDot {

    public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("params: type infile [outfile]");
			System.out.println("type: g d");
			return;
		}
		PrintWriter out = null;
		if (args.length > 2) {
			out = new PrintWriter(
				Files.newBufferedWriter(Paths.get(args[2])));
		} else {
			out = new PrintWriter(System.out);
		}
		String type = args[0].toLowerCase();
		switch (type){
		case "g": {
			Graph d = new Graph(new In(args[1]));
			out.println("graph G {");
			out.println(" # suggest it fits on an A4 page");
			out.println(" ratio=\"fill\";\n size=\"8.3,11.7!\";\n margin=0;");
			for (int a=0; a< d.V(); a++) {
				if (d.degree(a) == 0) {
					out.printf("\t%1d%n",a);
				} else {
					for (int b: d.adj(a))
						if (a<b)
							out.printf("\t%1d -- %2d%n", a,b);
				}
			}
			out.println("}");out.close();
		}
			break;
		case "d": {
			Digraph d = new Digraph(new In(args[1]));
			out.println("digraph D {");
			out.println(" # suggest it fits on an A4 page");
			out.println(" ratio=\"fill\";\n size=\"8.3,11.7!\";\n margin=0;");
			for (int a=0; a< d.V(); a++) {
				if ((d.indegree(a) == 0) && (d.outdegree(a) == 0)) {
					out.printf("\t%1d%n",a);
				} else {
					for (int b: d.adj(a))
						out.printf("\t%1d -> %2d%n", a,b);
				}
			}
			out.println("}");
			out.close();
		}
			break;
		case "dw": {
			EdgeWeightedDigraph d =
				new EdgeWeightedDigraph(new In(args[1]));
			out.println("digraph D {");
			out.println(" # suggest it fits on an A4 page");
			out.println(" ratio=\"fill\";\n size=\"8.3,11.7!\";"
						+"\n margin=0;");
			for (DirectedEdge de : d.edges()){
				out.printf("\t%1d -> %2d [label=\"%3f\"] %n",
						   de.from(),
						   de.to(),
						   de.weight());
			}
			out.println("}");
			out.close();
		}
			break;
		case "gw": {
			EdgeWeightedGraph d =
				new EdgeWeightedGraph(new In(args[1]));
			out.println("graph G {");
			out.println(" # suggest it fits on an A4 page");
			out.println(" ratio=\"fill\";\n size=\"8.3,11.7!\";"
						+"\n margin=0;");
			for (Edge de : d.edges()){
			    int a = de.either();
			    int b = de.other(a);
				out.printf("\t%1d -- %2d [label=\"%3f\"] %n",
					   a, b, de.weight());
			}
			out.println("}");
			out.close();
		}
			break;
			default:
				System.err.println("unknown type:"+args[0]);
		}
    }
}
