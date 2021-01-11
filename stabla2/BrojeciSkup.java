import java.util.*;
import java.io.*;

public class BrojeciSkup {

    Cvor koren;

    private class Cvor {
	Map<Character, Cvor> deca = new TreeMap<>();
	int brojac;
    }

    public BrojeciSkup() {
	koren = new Cvor();
    }

    public void ubaci(String s) {
	ubaci(koren, new StringBuilder(s));
    }

    private void ubaci(Cvor c, StringBuilder s) {
	if (s.length() == 0)
	    return;
	Character zn = s.charAt(0);
	Cvor cilj = c.deca.get(zn);
	if (cilj == null) {
	    cilj = new Cvor();
	    c.deca.put(zn,cilj);
	}
	s.deleteCharAt(0);
	if (s.length() == 0) {
	    cilj.brojac ++;
	} else {
	    ubaci(cilj,s);
	}
    }

    public void printInternal() {
	printInternal(koren,0);
    }

    private void printInternal(Cvor c,int dubina) {
	if (c == null)
	    return;
	for (int i=0;i<dubina;i++)
	    System.out.print(" + ");
	System.out.printf(" %1d -- %2s%n",c.brojac, c.deca.keySet());
	for (Cvor dete: c.deca.values())
	    printInternal(dete,dubina+1);
    }

    public void printAll() {
	printAll(koren, new StringBuilder());
    }

    public void printAll(Cvor c, StringBuilder sb) {
	if (c.brojac > 0)
	    System.out.printf("%1s : %2d%n",sb.toString(),c.brojac);
	for (Map.Entry<Character, Cvor> e: c.deca.entrySet()) {
	    sb.append(e.getKey());
	    printAll(e.getValue(), sb);
	    sb.deleteCharAt(sb.length()-1);
	}
    }

    public void loadAndPrintFile(String fn) throws Exception{
	//SvetovidReader sr = Svetovid.in(fn);
	File f = new File(fn);
	Scanner s = new Scanner(f);
	while (s.hasNextLine()) {
	    ubaci(s.nextLine());
	}
	printAll();
    }

    public static void main(String[] args) {
	BrojeciSkup bs = new BrojeciSkup();
	if (args.length == 0) {
	    bs.ubaci("abakus");
	    bs.ubaci("albatros");
	    bs.ubaci("albatros");
	    bs.ubaci("albatros");
	    bs.ubaci("albatros");
	    bs.ubaci("bala");
	    bs.ubaci("balavac");
	    bs.ubaci("papagaj");
	    bs.printInternal();
	    bs.printAll();
	} else {
		try{
			bs.loadAndPrintFile(args[0]);
		}catch(Exception e){
			e.printStackTrace();
		}
	    
	}
    }

}
