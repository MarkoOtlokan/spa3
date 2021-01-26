import java.util.*;
import java.io.*;

public class Main{
  public static void main(String[] args) {
    Mapa<String, String> m = new Mapa<String, String>();
    System.out.println("Ubacujem");
    try {
      File myObj = new File("reci");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] pod = data.split(";");
    //    System.out.println(data);
        m.add(pod[0], pod[0]);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    System.out.println("Zavrsio ubacivanje");
    m.maxDubina();
    m.minDubina();
    System.out.println("MinIMax");
    m.minVrednost();
    m.maxVrednost();
    System.out.println("Pocinjem rebalansiranje");
    m.rebalansiraj();
    System.out.println("Gotovo rebalansiranje");
    m.maxDubina();
    m.minDubina();
    System.out.println("MinIMax");
    m.minVrednost();
    m.maxVrednost();
<<<<<<< HEAD
    m.vratiVrednost("NS 60 BR");
    m.velicinaMape();
    m.deleteKey("NS 60 BR");
    m.velicinaMape();
    m.vratiVrednost("NS 60 BR");
=======
    System.out.println("Kraj");
//    m.vratiVrednost("NS 60 BR");
  //  m.deleteKey("NS 60 BR");
    //m.vratiVrednost("NS 60 BR");
>>>>>>> 5be288f1a3103e151eaace322eb90c6c4460d037
  //  m.printNode(m.koren);
  }


}
