import sheffield.*;
import java.lang.Math;

public class SmallTree {
    public static void main (String [] args){
      //Defining constants 
      final int ROWS = 120;
      final int PIXELS = 120;

      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(PIXELS, ROWS);

      //Initializing arrys
      int [][] decryptedTreeData = new int[ROWS][PIXELS];

      //Reading and decrypting data from the tree file
      for (int row=ROWS-1; row>=0; row--){
        for (int column=0; column<PIXELS; column++){
          decryptedTreeData[row][column] = (int) treeFile.readChar();
        }
      }

      //Displaying the tree
      for (int row=ROWS-1; row>=0; row--){
        for (int column=0; column<PIXELS; column++){
          if (decryptedTreeData[row][column]%2 == 0){
            if (row > ROWS*0.3){
              //Sky
              graphics.setColor(153, 255, 255);
              graphics.plot(column, row);
            }
            else{
              //Grass
              graphics.setColor(0, 204, 0);
              graphics.plot(column, row);
            }
          }
          else{
            if (decryptedTreeData[row][column]%5 == 0){
              //Trunk
              graphics.setColor(102, 51, 0);
              graphics.plot(column, row);
              System.out.println("row = "+row);
            }
            else{
              //Leaf
              graphics.setColor(0, 255, 0);
              graphics.plot(column, row);
            }
          }
        }
      }

    }
}
