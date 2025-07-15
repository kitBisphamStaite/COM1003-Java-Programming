import sheffield.*;
import java.lang.Math;


public class SeasonalForestV2 { 
    public static void main (String [] args){
      //Defining constants 
      final int CHAR_ROWS = 128;
      final int CHAR_COLUMN = 120;
      final int SEASONS = 3;
      final int  WIDTH_MULTIPLIER = 5;
      final int  HEIGHT_MULTIPLIER = 2;

      //Defining up variables
      int treeScale, treeXPositon, amountOfTrees, decryptedChar;
      boolean isSky, isSmallTree;
      double newRow, newColumn, fromBottom;
      int rand = 0;
      
      //rounding because if the AREA_SCALE is a odd number the scale will be #.5
      int largeRows = (int) Math.round(CHAR_ROWS*HEIGHT_MULTIPLIER);
      int largeColumn = (int) Math.round(CHAR_COLUMN);


      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(largeColumn*WIDTH_MULTIPLIER, 
                                        largeRows*SEASONS);

      //Initializing arrys
      int [][] decryptedTreeData = new int[CHAR_ROWS][CHAR_COLUMN];

      //Reading and decrypting data from the tree file
      for (int row=CHAR_ROWS-1; row>=0; row--){
        for (int column=0; column<CHAR_COLUMN; column++){
          //Getting only the tree data 
          decryptedChar = (int) treeFile.readChar();
          if (decryptedChar%2 != 0){
            decryptedTreeData[row][column] = decryptedChar;
          } else{
            //Making background data 0's
            decryptedTreeData[row][column] = 0;
          }
        }
      }

      //Displaying The Seasonal Forest
      for (int season=0; season<=SEASONS-1; season++){

        //Displaying Background
        for (int row=0; row<=CHAR_ROWS-1; row++){
          for (int column=0; column<=CHAR_COLUMN-1; column++){
            if (row > CHAR_ROWS*0.4){
              //Sky
              graphics.setColor(153, 255, 255);
            }
            else{
              //Grass
              rand = (int) (Math.random()*100);
              if (rand <= 20 || season != 0){
                //Green bits
                graphics.setColor(0, 204, 0);
              } else{
                //Brown bits
                rand = (int) ((Math.random()*105)+80);
                graphics.setColor(rand, 51, 0);
              }
            }
            //plotting the background
            //pixel size 4 by 4
            for (int n=0; n<HEIGHT_MULTIPLIER; n++){
              for (int c=0; c<HEIGHT_MULTIPLIER; c++){
                //5 times the width
                for (int x=WIDTH_MULTIPLIER-1; x>=0; x--){
                  newRow = row*HEIGHT_MULTIPLIER;
                  newColumn = column*HEIGHT_MULTIPLIER;
                  //trasnlating the x and y coordinates based on new x, y position and season
                  graphics.plot((int) Math.round(newColumn+c+(x*largeColumn)), 
                            (int) Math.round((newRow+n+(season*largeRows))));
                }
              }
            }
          }
        }

        //Getting number of trees between 15 and 25
        amountOfTrees = (int) ((Math.random()*(25-15))+15);
        fromBottom = (largeRows*0.4)*0.3;

        //Displaying the trees
        for (int tree=0; tree<=amountOfTrees-1; tree++){
    
          //Getting the tree scale
          if(Math.random() < 0.8){
            //80% will be larger
            treeScale = 2;
            //Getting random horizontal position if big
            treeXPositon = (int) (Math.random()*(CHAR_COLUMN*WIDTH_MULTIPLIER)-(CHAR_COLUMN));
            isSmallTree = false;
          }else{
            //20% will be smaller
            treeScale = 1;
            //Getting random horizontal position if small
            treeXPositon = (int) (Math.random()*(CHAR_COLUMN*WIDTH_MULTIPLIER)-(CHAR_COLUMN/2));
            isSmallTree = true;
          }
          
          //Making the first half start from the bottom
          if (tree > amountOfTrees*0.5){
            fromBottom = 0;
          }else{
            //Making the secont half start from 30% bellow the horrizon and 30% above the bottom
            fromBottom = (largeRows*0.4)*0.3;
          }
          //Making the small trees inline with the big trees
          if (isSmallTree){
            fromBottom += 5*HEIGHT_MULTIPLIER;
          }

          //Getting the different colours and drawing the tree
          for (int row=0; row<=CHAR_ROWS-1; row++){
            for (int column=0; column<=CHAR_COLUMN-1; column++){
              isSky = false;
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
                    isSky = true;
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
                if (!isSky){
                  //plot the tree based on the treescale and new possition
                  for (int n=0; n<HEIGHT_MULTIPLIER; n++){
                    for (int c=0; c<HEIGHT_MULTIPLIER; c++){
                      newRow = row*treeScale;
                      //trasnlating the x and y coordinates based on treescale and new position and season
                      graphics.plot((int) Math.round(column*treeScale+c+(treeXPositon)), 
                                (int) Math.round((newRow+n+(season*largeRows))+fromBottom));
                    }
                  }
                }
              }
            }
          }
        }
      }

    }
}
