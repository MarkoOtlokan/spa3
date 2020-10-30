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

  public static void writeFromMatrix(boolean[][] matrix, int size){
    try{
      FileWriter myWriter = new FileWriter("Test.dot");
      myWriter.write("graph {");
      //myWriter.write("A -- B B -- C C -- A C -- C ");
      for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
          if(matrix[i][j]){
            myWriter.write((i+1)+" -- "+(j+1)+"\n");
          }
        }
      }
      myWriter.write("}");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    }catch(IOException e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
