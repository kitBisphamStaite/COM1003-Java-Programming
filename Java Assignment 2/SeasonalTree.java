import sheffield.*;
import java.lang.Math;



public class SeasonalTree { 
    public static void main (String [] args){
      //Defining constants 
      final int CHAR_ROWS = 128;
      final int CHAR_COLUMN = 120;
      final int SEASONS = 3;
      int rand = 0;
      //how much larger you want the image
      final int AREA_SCALE = 4;
      double scale = AREA_SCALE/2;
      
      //rounding because if the AREA_SCALE is a odd number the scale will be #.5
      int large_rows = (int) Math.round(CHAR_ROWS*scale);
      int large_column = (int) Math.round(CHAR_COLUMN*scale);

      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(large_column, large_rows*SEASONS);

      //Initializing arrys
      int [][] decryptedTreeData = new int[CHAR_ROWS][CHAR_COLUMN];

      //Reading and decrypting data from the tree file
      for (int row=CHAR_ROWS-1; row>=0; row--){
        for (int column=0; column<CHAR_COLUMN; column++){
          decryptedTreeData[row][column] = (int) treeFile.readChar();
        }
      }

      //Displaying the tree
      for (int season=SEASONS-1; season>=0; season--){
        System.out.println(season);
        for (int row=0; row<=CHAR_ROWS-1; row++){
          for (int column=0; column<=CHAR_COLUMN-1; column++){
            if (decryptedTreeData[row][column]%2 == 0){
              if (row > CHAR_ROWS*0.3){
                //Sky
                graphics.setColor(153, 255, 255);
              }
              else{
                //Grass
                rand = (int) (Math.random()*100);
                if (rand <= 20 || season != 0){
                  graphics.setColor(0, 204, 0);
                } else{
                  rand = (int) ((Math.random()*105)+80);
                  graphics.setColor(rand, 51, 0);
                }
              }
            } else{
              if (decryptedTreeData[row][column]%5 == 0){
                //Trunk
                graphics.setColor(102, 51, 0);
              }
              else if (season == 0){
                //branch
                if (decryptedTreeData[row][column]%7 == 0){
                  graphics.setColor(102, 51, 0);
                }
                else{
                  //Sky
                  graphics.setColor(153, 255, 255);
                }
              }
              else{
                //Leaf
                if (season == 2){
                  //Green leaf
                  rand = (int) ((Math.random()*155)+100);
                  graphics.setColor(0, rand, 0);
                }
                else if (season == 1){
                  //Brown leaf
                  rand = (int) ((Math.random()*105)+80);
                  graphics.setColor(rand, 51, 0);
                }
              }
            }
            //plot the pixels based on the scale
            for (int n=0; n<scale; n++){
              for (int c=0; c<scale; c++){
                double newRow = row*scale;
                graphics.plot((int) Math.round(column*scale+c), 
                          (int) Math.round((newRow+n+(season*large_rows))));
              }
            }
          }
        }
      }

    }
}
