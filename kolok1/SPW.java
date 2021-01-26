import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

public class SPW {
	
	private boolean[] marked;
	private Edge[] edgeTo;
	private double[] distanceTo;
	private Queue<Edge> que;
	
	public SPW (EdgeWeightedGraph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new Edge[G.V()];
		distanceTo = new double[G.V()];
		que = new LinkedList<Edge>();
		
		for (int v = 0; v < G.V(); v++)
			distanceTo[v] = Double.POSITIVE_INFINITY;
		distanceTo[s] = 0.0;
		
		relax(G, s);
		
		while(!que.isEmpty()) {
			Edge e = que.remove();
			//System.out.println("processing:"+e);
			int v = e.either();
			int w = e.other(v);
			if (!marked[v])
				relax(G, v);
			if (!marked[w])
				relax(G, w);
		}
	}
	
	private void relax(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (distanceTo[w] > distanceTo[v] + e.weight()) {
				distanceTo[w] = distanceTo[v] + e.weight();
				edgeTo[w] = e;
				que.add(e);
			}
		}
		//System.out.println("node:"+v+"  "+que);
		
	}
	
	public double distTo(int v) {
		return distanceTo[v];
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Edge> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Deque<Edge> path = new LinkedList<Edge>();
		
		int x = v;
		
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
			path.addFirst(e);
			x = e.other(x);
		}

		return path;
	}
	
	public static void main(String args[]) throws IOException {
		
		System.out.println("Enter source file: ");
		
		String defaultFile ="tinyEWG.txt";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName = br.readLine();
		
		if (fileName.equals("")) {
			fileName = defaultFile;
		}
		
		System.out.println("Enter source vertex: ");
		
		int s = Integer.parseInt(br.readLine());

		EdgeWeightedGraph G = new EdgeWeightedGraph(new In(fileName));
		
		System.out.println(G);
		
		SPW spw = new SPW(G, s);
		
		for (int v = 0; v < G.V(); v++) {
			System.out.print(s + " to " + v + ": ");
			if(spw.hasPathTo(v)) {
				for (Edge e : spw.pathTo(v))
					System.out.print(e + " ");
			}
			System.out.println();
		}
		
	}
}
