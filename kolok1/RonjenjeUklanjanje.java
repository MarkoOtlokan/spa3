import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

public class RonjenjeUklanjanje {
	
	
	public static void main(String args[]) throws IOException{

		System.out.println("Enter source file: ");
		
		String defaultFile ="tinyEWG.txt";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName = br.readLine();
		
		if (fileName.equals("")) {
			fileName = defaultFile;
		}
		
		EdgeWeightedGraph G = new EdgeWeightedGraph(new In(fileName));
		
		System.out.println("Enter source vertex: ");
		
		int s = Integer.parseInt(br.readLine());

		System.out.println("Enter destination vertex: ");
		
		int d = Integer.parseInt(br.readLine());
		
		System.out.println("Enter max underwater time: ");
		
		double n = Double.parseDouble(br.readLine());
		
		System.out.println(G);
		
		EdgeWeightedGraph S = new EdgeWeightedGraph(G.V());
		
		for (Edge e : G.edges()) {
			if(e.weight() <= n)
				S.addEdge(e);
		}
		
		System.out.println(S);
		
		SPW snorkliong = new SPW(S, s);
		
		System.out.println("Snorkling possible: " + snorkliong.hasPathTo(d)); 
	}

}
