import java.util.*;
import java.io.*;

public class Bin<K extends Comparable<K>, V>{
  private Comparator<K> komparator;
  private Cvor koren;

  private class Cvor{
    K kljuc;
    V vrednost;
    Cvor levi, desni;

    public Cvor(K k, V v){
      kljuc = k;
      vrednost = v;
      levi = null;
      desni = null;
    }

    public String toString(){
      return "Kljuc "+kljuc+" Vrednost"+vrednost;
    }
  }

  public Bin(){
    this(Comparator.naturalOrder());
  }

  public Bin(Comparator<K> c){
    komparator = c;
    koren = null;
  }

  public void put(K kljuc, V vrednost){
    if(koren == null){
      Cvor c = new Cvor(kljuc, vrednost);
      koren = c;
      return;
    }
    put(koren, kljuc, vrednost);
  }

  public void put(Cvor cvor, K kljuc, V vrednost){
    if(cvor == null){
      return;
    }
    int poredjenje = komparator.compare(kljuc, cvor.kljuc);
    if(poredjenje == 0){
      cvor.vrednost = vrednost;
      return;
    }
    if(poredjenje > 0){
      if(cvor.levi == null){
        Cvor nov = new Cvor(kljuc, vrednost);
        cvor.levi = nov;
      }
      else{
        put(cvor.levi, kljuc, vrednost);
      }
      return;
    }
    else{
      if(cvor.desni == null){
        Cvor nov = new Cvor(kljuc, vrednost);
        cvor.desni = nov;
      }
      else{
        put(cvor.desni, kljuc, vrednost);
      }
    }
  }

  public V get(K kljuc){
    return get(koren, kljuc);
  }

  public V get(Cvor c, K kljuc){
      if(c == null){
        return null;
      }
      int upored = komparator.compare(c.kljuc, kljuc);
      if(upored == 0){
        return c.vrednost;
      }
      if(upored > 0){
        return get(c.levi, kljuc);
      }
      else{
        return get(c.desni, kljuc);
      }
  }

  public String toString() {
    StringBuilder st = new StringBuilder();
    strRun(koren,st);
    return st.toString();
  }

  private void strRun(Cvor c, StringBuilder st){
  	if (c == null)
  	    return;
  	strRun(c.levi, st);
  	st.append(c);
  	strRun(c.desni, st);
  }

  public List<K> keysInRange(K a, K b){
    LinkedList<K> lista = new LinkedList<>();
    keysInRange(koren, a, b, lista);
    return lista;
  }

  public void keysInRange(Cvor c, K a, K b, LinkedList<K> lista){
      if(c == null){
        return;
      }
      int poredjenje = komparator.compare(c.kljuc, a);
      if(poredjenje < 0){
        keysInRange(c.levi, a, b, lista);
      }
      int poredjenje2 = komparator.compare(c.kljuc, b);
      if(poredjenje > 0){
        keysInRange(c.desni, a, b, lista);
      }
      if(poredjenje <= 0 && poredjenje2 >=0){
        lista.add(c.kljuc);
      }
  }

  public void printSideways() {
    printSideways(koren,0);
  }

  private String sep="+--";

  private void printSideways(Cvor c, int level) {
    if (c == null)
      return;
    printSideways(c.desni,level+1);
    for (int i = 0; i < level; i++)
      System.out.print(sep);
    System.out.println(c);
    printSideways(c.levi,level+1);
  }

  public static void unosITest(String fajl) throws Exception{
    Bin<String, String> b = new Bin<>();
    File f = new File(fajl);
    Scanner s = new Scanner(f);
    while(s.hasNextLine()){
      String[] tmpString = s.nextLine().split(";");
      b.put(tmpString[0], tmpString[1]);
    }
    b.printSideways();
    System.out.println(b.get("NS 479 KS"));
    System.out.println(b.keysInRange("AG 141 KK", "ZU 13 JJ"));

  }

  public static void main(String[] args) {
    try {
      unosITest("kola");
    }catch (Exception e) {
      e.printStackTrace();
    }
  }






























































}
