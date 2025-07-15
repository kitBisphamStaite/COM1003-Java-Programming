import sheffield.*;
import java.lang.Math;


public class Assignment2 {
    public static void main (String [] args){
      //Defining constants
      final int CHAR_ROWS = 128;
      final int CHAR_COLUMN = 120;
      final int SEASONS = 3;
      final int WIDTH_MULTIPLIER = 5;
      final int HEIGHT_MULTIPLIER = 2;

      //Defining variables
      int treeScale, treeXPositon, amountOfTrees, decryptedChar,
          newRow, newColumn;
      boolean isSky, isSmallTree;
      double fromBottom;
      int rand = 0;

      //Char row with MULTIPLIER
      int largeRows = CHAR_ROWS*HEIGHT_MULTIPLIER;


      //Opening the data from file
      EasyReader treeFile = new EasyReader("tree.txt");
      EasyGraphics graphics = new EasyGraphics(CHAR_COLUMN*WIDTH_MULTIPLIER,
                                        largeRows*SEASONS);

      //Initializing matrix
      int [][] decryptedTreeData = new int[CHAR_ROWS][CHAR_COLUMN];

      //Reading and decrypting data from the tree file
      for (int row=CHAR_ROWS-1; row>=0; row--){
        for (int column=0; column<CHAR_COLUMN; column++){
          //Getting only the tree data, background will be 0's
          decryptedChar = (int) treeFile.readChar();
          if (decryptedChar%2 != 0){
            decryptedTreeData[row][column] = decryptedChar;
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
              //20% green bits for winter
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
            for (int pixelRow=0; pixelRow<HEIGHT_MULTIPLIER; pixelRow++){
              for (int pixelColumn=0;
                    pixelColumn<HEIGHT_MULTIPLIER; pixelColumn++){
                //5 times the width
                for (int width=WIDTH_MULTIPLIER-1; width>=0; width--){
                  newRow = row*HEIGHT_MULTIPLIER;
                  newColumn = column*HEIGHT_MULTIPLIER;
                  //trasnlating the x and y coordinates based on
                  //    new x, y position and season
                  graphics.plot(
                            newColumn+pixelColumn+(width*CHAR_COLUMN),
                            newRow+pixelRow+(season*largeRows));
                }
              }
            }
          }
        }

        //Getting number of trees between 15 and 25
        amountOfTrees = (int) ((Math.random()*(25-15))+15);

        //Displaying the trees
        for (int tree=0; tree<=amountOfTrees-1; tree++){

          //Getting the tree scale
          if(Math.random() < 0.8){
            //80% will be larger
            treeScale = 2;
            //Getting random horizontal position if big
            treeXPositon = (int)
                (Math.random()*(CHAR_COLUMN*WIDTH_MULTIPLIER)-(CHAR_COLUMN));
            isSmallTree = false;
          }else{
            //20% will be smaller
            treeScale = 1;
            //Getting random horizontal position if small
            treeXPositon = (int)
                (Math.random()*(CHAR_COLUMN*WIDTH_MULTIPLIER)-(CHAR_COLUMN/2));
            isSmallTree = true;
          }

          //Making the first half start from the bottom
          if (tree > amountOfTrees*0.5){
            fromBottom = 0;
          }else{
            //Making the secont half start from 30% bellow the horrizon
            //    and 30% above the bottom
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
                    //Don't want to plot the sky over other trees
                    isSky = true;
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
                  for (int pixelRow=0; pixelRow<HEIGHT_MULTIPLIER; pixelRow++){
                    for (int pixelColumn=0;
                          pixelColumn<HEIGHT_MULTIPLIER; pixelColumn++){
                      newRow = row*treeScale;
                      //trasnlating the x and y coordinates based on treescale
                      //           and new position and season
                      graphics.plot(
                                    column*treeScale+pixelColumn+(treeXPositon),
                                (int) Math.round((newRow+pixelRow+
                                                  (season*largeRows))+
                                                  fromBottom));
                    }
                  }
                }
              }
            }
          }
        }
      }

      //Drawing pixel thick horizontal black lines between the seasons
      graphics.setColor(0, 0, 0);
      //Line one
      graphics.drawLine(
                0, CHAR_ROWS*HEIGHT_MULTIPLIER,
                CHAR_COLUMN*WIDTH_MULTIPLIER, CHAR_ROWS*HEIGHT_MULTIPLIER);
      //Line two
      graphics.drawLine(
                0, CHAR_ROWS*HEIGHT_MULTIPLIER*2,
                CHAR_COLUMN*WIDTH_MULTIPLIER, CHAR_ROWS*HEIGHT_MULTIPLIER*2);
    }

}
