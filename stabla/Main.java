import java.util.*;
import java.io.*;

public class Main{
  public static void main(String[] args) {
    Mapa<String, String> m = new Mapa<String, String>();
    try {
      File myObj = new File("kola2s.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] pod = data.split(";");
    //    System.out.println(data);
        m.add(pod[0], pod[1]);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    m.maxDubina();
    m.minDubina();
    m.minVrednost();
    m.maxVrednost();
    m.rebalansiraj();
    m.maxDubina();
    m.minDubina();
    m.minVrednost();
    m.maxVrednost();
    m.vratiVrednost("NS 60 BR");
    m.deleteKey("NS 60 BR");
    m.vratiVrednost("NS 60 BR");
  //  m.printNode(m.koren);
  }


}
