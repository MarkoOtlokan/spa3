import java.util.*;
import java.io.*;

public class Recnik{
  private Cvor koren;

  private class Cvor{
    Map<Character, Cvor> mapa = new TreeMap<>();
    int brojac = 0;
  }

  public Recnik(){
    koren = new Cvor();
  }

  public void ubaci(String s){
    ubaci(koren, new StringBuilder(s));
  }

  private void ubaci(Cvor koren, StringBuilder string){
    if(string.length() == 0){
      return;
    }
    Character c = string.charAt(0);
    Cvor cilj = koren.mapa.get(c);
    if(cilj == null){
      cilj = new Cvor();
      koren.mapa.put(c, cilj);
    }
    string.deleteCharAt(0);
    if(string.length() == 0){
      cilj.brojac++;
    }else{
      ubaci(cilj, string);
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

    public static void testiraj(String fajl) throws Exception{
      Recnik r = new Recnik();
      File f = new File(fajl);
      Scanner s = new Scanner(f);
      while(s.hasNextLine()){
        String tmpString = s.nextLine();
        r.ubaci(tmpString);
      }
      r.printAll();
    }

    public static void main(String[] args) {
      try {
        testiraj(args[0]);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }















}
