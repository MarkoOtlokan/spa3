import java.util.*;
import java.io.*;

public class Imenik{
	Cvor koren;

	private class Cvor{
		Map<Character, Cvor> mapa = new TreeMap<>();
		int brojac=0;
	}

	Imenik(){
		koren = new Cvor();
	}

	public void ubaci(String s){
		ubaci(koren, new StringBuilder(s));
	}

	private void ubaci(Cvor k, StringBuilder s){
		if(s.length() == 0)
			return;
		Character c = s.charAt(0);
		Cvor cvor = k.mapa.get(c);
		if(cvor == null){
			cvor = new Cvor();
			k.mapa.put(c, cvor);
		}
		s.deleteCharAt(0);
		if(s.length() == 0){
			cvor.brojac++;
		}else{
			ubaci(cvor, s);
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
		System.out.printf(" %1d -- %2s%n",c.brojac, c.mapa.keySet());
		for (Cvor dete: c.mapa.values())
		    printInternal(dete,dubina+1);
    }

    public void printAll() {
		printAll(koren, new StringBuilder());
    }

    public void printAll(Cvor c, StringBuilder sb) {
		if (c.brojac > 0)
		    System.out.printf("%1s : %2d%n",sb.toString(),c.brojac);
		for (Map.Entry<Character, Cvor> e: c.mapa.entrySet()) {
		    sb.append(e.getKey());
		    printAll(e.getValue(), sb);
		    sb.deleteCharAt(sb.length()-1);
		}
    }

    public void loadFile(String naziv) throws Exception{
    	File f = new File(naziv);
    	Scanner s = new Scanner(f);
    	while(s.hasNextLine()){
    		ubaci(s.nextLine());
    	}
    	printAll();
    }

    public static void main(String[] args) {
    	Imenik imenik = new Imenik();
    	try{
    		imenik.loadFile("prezimena");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}