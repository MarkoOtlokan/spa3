import java.util.*;

public class UsmereniCiklus {

	private boolean[] poseceni;
	private int[] granaDo;
	private Stack<Integer> ciklus;
	private boolean[] naSteku;
	private Stack<Integer> obrnuti;

	public UsmereniCiklus(DAG g) {
		poseceni = new boolean[g.getBrojCvorova()];
		granaDo = new int[g.getBrojCvorova()];
		naSteku = new boolean[g.getBrojCvorova()];
		obrnuti = new Stack<Integer>();
		for (int i = 0; i < g.getBrojCvorova(); i++) {
			if (!poseceni[i])
				dfs(g, i);
		}
		if (!postojiCiklus())
			System.out.println("Topoloski sort je " + vratiObrnuti().toString());
		else
			System.out.println("Postoji kontura u grafu");

	}

	public Iterable<Integer> vratiObrnuti() {
		return obrnuti;
	}

	public boolean postojiCiklus() {
		return ciklus != null;
	}

	public Iterable<Integer> vratiCiklus() {
		return ciklus;
	}

	private void dfs(DAG g, int tek) {
		naSteku[tek] = true;
		poseceni[tek] = true;
		for (int w : g.getSusedi().get(tek)) {
			if (postojiCiklus())
				return;
			else if (!poseceni[w]) {
				granaDo[w] = tek;
				dfs(g, w);
			} else if (naSteku[w]) {
				ciklus = new Stack<Integer>();
				for (int x = tek; x < w; x = granaDo[x])
					ciklus.push(x);
				ciklus.push(w);
				ciklus.push(tek);
			}
			obrnuti.push(tek);
			naSteku[tek] = false;
		}

	}

}
