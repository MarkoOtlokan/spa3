import java.io.*;

public class JavaDot{



  public static void write(String[] args) {
    try {
      FileWriter myWriter = new FileWriter("Test.dot");
      myWriter.write("graph {");
      myWriter.write("A -- B B -- C C -- A C -- C ");
      myWriter.write("}");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
