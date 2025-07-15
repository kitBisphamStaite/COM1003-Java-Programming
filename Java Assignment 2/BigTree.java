import sheffield.*;
import java.lang.Math;

public class BigTree {
    public static void main (String [] args){
      //Defining constants 
      final int CHAR_ROWS = 128;
      final int CHAR_COLUMN = 120;
      //how much larger you want the image
      final int AREA_SCALE = 10;
      double scale = AREA_SCALE/2;
      
      //rounding because if the AREA_SCALE is a odd number the scale will be #.5
      int large_rows = (int) Math.round(CHAR_ROWS*scale);
      int large_column = (int) Math.round(CHAR_COLUMN*scale);

      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(large_column, large_rows);

      //Initializing arrys
      int [][] decryptedTreeData = new int[CHAR_ROWS][CHAR_COLUMN];

      //Reading and decrypting data from the tree file
      for (int row=CHAR_ROWS-1; row>=0; row--){
        for (int column=0; column<CHAR_COLUMN; column++){
          decryptedTreeData[row][column] = (int) treeFile.readChar();
        }
      }

      //Displaying the tree
      for (int row=0; row<=CHAR_ROWS-1; row++){
        for (int column=0; column<=CHAR_COLUMN-1; column++){
          if (decryptedTreeData[row][column]%2 == 0){
            if (row > CHAR_ROWS*0.3){
              //Sky
              graphics.setColor(153, 255, 255);
              //plot the pixels based on the scale
              plot_pixels(scale, column, row, graphics);
            }
            else{
              //Grass
              graphics.setColor(0, 204, 0);
              plot_pixels(scale, column, row, graphics);
            }
          }
          else{
            if (decryptedTreeData[row][column]%5 == 0){
              //Trunk
              graphics.setColor(102, 51, 0);
              plot_pixels(scale, column, row, graphics);
            }
            else{
              //Leaf
              int rand = (int) ((Math.random()*155)+100);
              graphics.setColor(0, rand, 0);
              plot_pixels(scale, column, row, graphics);
            }
          }
        }
      }

    }
    public static void plot_pixels (double scale, int column, int row, EasyGraphics g){
      //plotting the pixels
      for (int n=0; n<scale; n++){
        for (int c=0; c<scale; c++){
          g.plot((int) Math.round(column*scale+c), (int) Math.round(row*scale+n));
        }
      }
    }
}
