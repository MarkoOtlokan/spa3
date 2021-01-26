package zadatak4;

import java.util.LinkedList;

import edu.princeton.cs.algs4.IndexMinPQ;

public class LimitedDijkstra {
	private IndexMinPQ<Double> pq;
	private Edge[] edgeTo;
	private double[] distTo;
	
	public LimitedDijkstra(EdgeWeightedGraph g, int s, double limit) {
		pq = new IndexMinPQ<>(g.V());
		edgeTo = new Edge[g.V()];
		distTo = new double[g.V()];
		
		for(int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty()) {
			relax(g, pq.delMin(), limit);
		}
	}
	
	public void relax(EdgeWeightedGraph g, int v, double limit) {
		for(Edge e : g.adj(v)) {
			int w = e.other(v);
			if(distTo[w] > distTo[v] + e.weight() && e.weight() <= limit) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(pq.contains(w)) pq.changeKey(w, distTo[w]);
				else pq.insert(w, distTo[w]);
			}
		}
	}
	
	public boolean pathExists(int w) {
		return distTo[w] < Double.POSITIVE_INFINITY;
	}
	
	public LinkedList<Edge> path(int w) {
		if(!pathExists(w)) return null;
		LinkedList<Edge> path = new LinkedList<>();
		for(Edge e = edgeTo[w]; e != null; e = edgeTo[w]) {
			w = e.other(w);
			path.push(e);
		}
		
		return path;
	}
}
