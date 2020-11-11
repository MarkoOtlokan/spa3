import java.util.*;

public class UsmereniDFS {

	private boolean[] poseceni;

	public UsmereniDFS(DAG g, int s) {
		poseceni = new boolean[g.getBrojCvorova()];
		Ddfs(g, new ArrayList<Integer>(g.getBrojCvorova()));

	}

	public void Ddfs(DAG g, Iterable<Integer> cvorovi) {
		for (int s : cvorovi) {
			if (!poseceni[s])
				dfs(g, s);
		}
	}

	private void dfs(DAG g, int s) {
		poseceni[s] = true;
		for (Integer t : g.getSusedi().get(s)) {
			if (!poseceni[t])
				dfs(g, t);
		}
	}

}
