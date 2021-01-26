import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

import java.util.Scanner;

import edu.princeton.cs.algs4.Edge;


public class PuteviRonjenje {

	public static void main(String[] args) {
		// Poslednji zadatak sa ronjenjem
		In in=new In("graf.txt");
		EdgeWeightedGraph graph=new EdgeWeightedGraph(in);		
		Scanner sc=new Scanner(System.in);
		System.out.print("Unesite koliko je dugacak dah: ");
		double dah=sc.nextDouble();
		int a=0;int b=3;
		MyDfs mydfs=new MyDfs(graph, a, b, dah);		
		
		System.out.println("Takav put( za dah="+dah+" )"+" izmedju "+a+" i "+b+" "+ (mydfs.isImaPuta()?" postoji":" ne postoji"));
		
		
		
	}
	static class MyDfs{
		private boolean[] marked;
		private EdgeWeightedGraph g; 
		private boolean imaPuta;
		private int goal;
		private double dah;
		
		
		public MyDfs(EdgeWeightedGraph g, int start, int goal, double dah) {
			marked=new boolean[g.V()];
			this.g=g;
			this.goal=goal;
			this.dah=dah;
			dfs(start, 0.0);
		}
		public boolean isImaPuta() {
			return imaPuta;
		}
		private void dfs(int x, double dahDosad) {
			if(x == goal) {
				imaPuta=true;
			}
			if(imaPuta)
				return;
			marked[x]=true;
			for(Edge e: g.adj(x)) {
				int w=e.other(x);
				double potrebanDah=e.weight()+dahDosad;
				if(potrebanDah<=dah  && !marked[w]) {
					if(dahDosad % 2 ==0)
						dfs(w, 0.0);
					else
						dfs(w, potrebanDah);
				}				
			}
			marked[x]=false;
		}
	}
}
