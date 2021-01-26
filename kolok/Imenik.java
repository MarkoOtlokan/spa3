import java.util.*;
import java.io.*;

public class Imenik{
  private TreeMap<String, String> osobe;
  private HashMap<String, String> telefoni;

  public Imenik(){
    osobe = new TreeMap<String, String>();
    telefoni = new HashMap<String, String>();
  }

  public String getOsoba(String t){
    return telefoni.get(t);
  }

  public String getTelefon(String o){
    return osobe.get(o);
  }

  public void put(String osoba, String telefon){
    String osobaTmp = getOsoba(osoba);
    String telefonTmp = getTelefon(telefon);
    if(osobaTmp != null || telefonTmp != null){
      System.out.println("vec postoji");
      return;
    }
    osobe.put(osoba, telefon);
    telefoni.put(telefon, osoba);
  }

  public static void testiraj(String fajl) throws IOException{
    Imenik i = new Imenik();
    File f = new File(fajl);
    Scanner s = new Scanner(f);
    while(s.hasNextLine()){
      String[] tmpString = s.nextLine().split(";");
      i.put(tmpString[0], tmpString[1]);
    }
    System.out.println(i.getTelefon("Aćim Andić"));
  }

  public static void main(String[] args) {
    try {
      testiraj("telefoni");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
