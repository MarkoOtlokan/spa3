import java.util.*;

public class Main{
  public static void main(String[] args) {
    //JavaDot.write(null);
    int size = 10;
    boolean[][] matrix = generateMatrix(size);
    JavaDot.writeFromMatrix(matrix, size);

  }

  private static boolean[][] generateMatrix(int size){
    boolean[][] matrix = new boolean[size][size];
    Random rand = new Random();
    for(int i = 0; i < size; i++){
      for(int j = i; j < size; j++){
        matrix[i][j] = rand.nextBoolean();
      }
    }
    return matrix;
  }
}
