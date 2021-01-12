import java.util.*;
import java.io.*;

public class Imenik {
	// brinemo se o dve strukture paraleleno u metodima

	// jedna struktura cuva mapiranje osoba->telefon
	// bitno je da je sortirano, uzimamo RBR stablo
	private TreeMap<String, String> osobe;
	// druga struktura cuva telefon->osoba
	// posto nije bitna sortiranost mozemo imati o(n)
	private HashMap<String, String> telefoni;

	public Imenik() {
		osobe = new TreeMap<String, String>();
		telefoni = new HashMap<String, String>();
	}

	public String getTel(String osoba) {
		return osobe.get(osoba);
	}

	public String getOsoba(String tel) {
		return telefoni.get(tel);
	}

	public List<String> getOsobe() {
		List<String> lista = new ArrayList<String>();
		lista.addAll(osobe.keySet());
		return lista;
	}

	public Set<String> getBrojevi() {
		HashSet<String> skup = new HashSet<String>();
		skup.addAll(telefoni.keySet());
		return skup;
	}


	public boolean put(String osoba, String tel) {
		if (!telefoni.containsKey(tel) && !osobe.containsValue(osoba)) {
			telefoni.put(tel, osoba);
			osobe.put(osoba, tel);
			return true;
		}
		return false;
	}

	private static Imenik ucitajImenik(String fajl) {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(fajl));
			Imenik imenik = new Imenik();
			String t;
			while ((t = buf.readLine()) != null) {
				String[] par = t.split(";");
				String ime = par[0];
				String tel = par[1];
				if (!imenik.put(ime, tel)) {
					System.out.println("greska; postoji vec tel ili osoba:"+ime+" / "+tel);
				}
			}
			buf.close();
			return imenik;
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) {
			Imenik im = ucitajImenik("telefoni.txt");
			//System.out.println(im.getOsobe());
			System.out.println(im.getTel("Aksentije Azarić"));
			System.out.println(im.getOsoba("062/256-41-37"));
	}
}
