import sheffield.*;
import java.lang.Math;



public class SeasonalForestV1 { 
    public static void main (String [] args){
      //Defining constants 
      final int CHAR_ROWS = 128;
      final int CHAR_COLUMN = 120;
      final int SEASONS = 3;
      final int  WIDTH_MULTIPLIER = 5;
      int treeScale, treeXPositon, amountOfTrees, rand;
      boolean is_sky;
      double fromBottom;
      


      //how much larger you want the image
      final int AREA_SCALE = 4;
      double scale = AREA_SCALE/2;
      
      //rounding because if the AREA_SCALE is a odd number the scale will be #.5
      int large_rows = (int) Math.round(CHAR_ROWS*scale);
      int large_column = (int) Math.round(CHAR_COLUMN*scale);

      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(large_column*WIDTH_MULTIPLIER, 
                                        large_rows*SEASONS);

      //Initializing arrys
      int [][] decryptedTreeData = new int[CHAR_ROWS][CHAR_COLUMN];

      //Reading and decrypting data from the tree file
      for (int row=CHAR_ROWS-1; row>=0; row--){
        for (int column=0; column<CHAR_COLUMN; column++){
          //Getting only the tree data 
          int decryptedChar = (int) treeFile.readChar();
          if (decryptedChar%2 != 0){
            decryptedTreeData[row][column] = decryptedChar;
          } else{
            //Making background data 0's
            decryptedTreeData[row][column] = 0;
          }
        }
      }

      //Displaying the trees
      
      for (int season=0; season<=SEASONS-1; season++){

        draw_background(season, CHAR_ROWS, CHAR_COLUMN, large_rows, large_column, 
              scale, WIDTH_MULTIPLIER, graphics);

        //getting number of trees between 15 and 25
        amountOfTrees = (int) ((Math.random()*(25-15))+15);
        System.out.println("Number of trees = "+amountOfTrees);
        fromBottom = 0;
        for (int tree=0; tree<=amountOfTrees-1; tree++){

          //Getting random horizontal position
          treeXPositon = (int) (Math.random()*(large_column*WIDTH_MULTIPLIER)-(CHAR_COLUMN/2));

          //Getting the tree scale
          if(tree < amountOfTrees*0.8){
            //80% will be larger
            treeScale = (int) ((Math.random()*(4-3))+3);
          }else{
            //20% will be smaller
            treeScale = 2;
          }
          System.out.println("treeScale = "+treeScale);

          if (tree > amountOfTrees*0.5){
            fromBottom = large_rows*0.3;
          }

          //Getting the different colours and drawing the tree
          for (int row=0; row<=CHAR_ROWS-1; row++){
            for (int column=0; column<=CHAR_COLUMN-1; column++){
              is_sky = false;
              if (decryptedTreeData[row][column] != 0){
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
                    //Don't want to plot the sky
                    is_sky = true;
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
                
                //Only plotting the tree
                if (!is_sky){
                  //plot the tree based on the scale and new possition
                  for (int n=0; n<treeScale; n++){
                    for (int c=0; c<treeScale; c++){
                      double newRow = row*treeScale;
                      graphics.plot((int) Math.round(column*treeScale+c+(treeXPositon)), 
                                (int) Math.round((newRow+n+(season*large_rows))+fromBottom));
                    }
                  }
                }
              }
            }
          }
        }
      }

    }

    public static void draw_background (int season, int char_rows, int char_column, int large_rows, int large_column,
                          double scale, int width_multiplier, EasyGraphics graphics){
      for (int row=0; row<=char_rows-1; row++){
        for (int column=0; column<=char_column-1; column++){
          if (row > char_rows*0.4){
            //Sky
            graphics.setColor(153, 255, 255);
          }
          else{
            //Grass
            int rand = (int) (Math.random()*100);
            if (rand <= 20 || season != 0){
              graphics.setColor(0, 204, 0);
            } else{
              rand = (int) ((Math.random()*105)+80);
              graphics.setColor(rand, 51, 0);
            }
          }
          for (int n=0; n<scale; n++){
            for (int c=0; c<scale; c++){
              for (int x=width_multiplier-1; x>=0; x--){
                double newRow = row*scale;
                double newColumn = column*scale;
                graphics.plot((int) Math.round(newColumn+c+(x*large_column)), 
                          (int) Math.round((newRow+n+(season*large_rows))));
              }
            }
          }
        }
      }
    }
}
