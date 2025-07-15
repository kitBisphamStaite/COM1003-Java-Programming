import sheffield.*;

public class Suspect {

   private final static int MAX_NUMBER=10;

   private static Suspect [] allTheSuspects;
   private static int numberOfSuspects;

   public static void initializeEveryone() {
    //create temp array
    Suspect [] tempArray = new Suspect[MAX_NUMBER];

    //read file
    EasyReader file = new EasyReader("suspects.txt");
    numberOfSuspects = 0;
    while (!file.eof() && (numberOfSuspects < MAX_NUMBER)){
      tempArray[numberOfSuspects] = new Suspect(file.readString());
      numberOfSuspects += 1;
    }

    //initialize and add to allTheSuspects array
    allTheSuspects = new Suspect[numberOfSuspects];
    System.arraycopy(tempArray, 0, allTheSuspects, 0, numberOfSuspects);

    //list the suspects
    listTheSuspects();
   }

   public static Suspect askWho(EasyReader keyboard)  {
    String suspectInput =
              keyboard.readString("Who do you accuse? ").toUpperCase();
     // change to upper case and take "the " out
     if (suspectInput.length() > 4 &&
          suspectInput.substring(0, 4).equals("the ")){
      suspectInput = suspectInput.substring(4, suspectInput.length());
    }

    // check if its a valid suspect
    for (int i = 0; i<numberOfSuspects; i++){
      if (allTheSuspects[i].name.toUpperCase().equals(suspectInput)){
        return allTheSuspects[i];
      }
    }
    return null;
   }

   public static void listTheSuspects()  {
     System.out.print("The suspects are "+allTheSuspects[0]);
     for (int i = 1; i<numberOfSuspects-1; i++)
        System.out.print(", "+allTheSuspects[i]);
     System.out.println(" and "+allTheSuspects[numberOfSuspects-1]);
   }

   public static Suspect pickedAtRandom() {
     //Getting random index from 0 to numberOfSuspects-1
     int randIndex = (int) (Math.random()*numberOfSuspects);
     return allTheSuspects[randIndex];
   }

   private String name;

   private Suspect (String n)  {  name = n;  }

   public String toString()  {  return name;  }

}
