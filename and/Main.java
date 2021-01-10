import java.util.*;
import java.io.*;
public class Main{
   public static void main(String[] args) throws IOException  {
      //BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
      //  String name = reader.readLine();
      Automobil a1 = new Automobil(130,100000,5,7, "Dacia", "Logan");
      Automobil a2 = new Automobil(180,100000,5,7, "Citroen", "c5");
      Covek c = new Covek("Marko","Otlokan",2);
      System.out.println(c);
      c.dodajAutomobil(a1);
      System.out.println(c);
      c.dodajAutomobil(a2);
      System.out.println(c);

   }






}
