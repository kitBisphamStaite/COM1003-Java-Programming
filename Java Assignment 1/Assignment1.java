import sheffield.*;

public class Assignment1 {
    public static void main (String [] args) {
      //Task 1
      //convertion rate constant
      double POUND_TO_KG_CONVERION_RATE = 0.453592;
      //initialize input/output using Easyreader and EasyWriter classes
      EasyReader keyboard = new EasyReader();
      EasyWriter screen = new EasyWriter();
      int weightInPounds = keyboard.readInt(
      "Please type in a weight in pounds : ");
      //calculating Kg on earth from the pounds given
      double WeightInKgOnEarth = weightInPounds*POUND_TO_KG_CONVERION_RATE;
      //printing the converted pounds
      screen.print("That is ");
      screen.print(WeightInKgOnEarth, 3);
      screen.println(" kilograms");
      screen.println();

      //Task 2
      //initialize file reader using Easyreader class
      EasyReader filePlanets = new EasyReader("planets.txt");
      String lineOne = filePlanets.readString();
      //Getting planet name from file
      String planetOneName = lineOne.substring(
            0, lineOne.indexOf("'s gravity"));
      //using string minipuation to get the first planets gravity
      //The number has "'s gravity is " just before it
      //plus converting it into a double type.
      double firstGravity = Double.valueOf(lineOne.substring(
            lineOne.indexOf("'s gravity is ")+14, lineOne.length()-1));
      double WeightInKgOnFirstPlanet = WeightInKgOnEarth*firstGravity;
      //printing the kg on earth and jupiter form the converted pounds
      screen.println(lineOne);
      screen.print(weightInPounds+" pounds on Earth weighs ");
      screen.print(WeightInKgOnEarth, 4);
      screen.println(" kilograms");

      screen.print(weightInPounds+" pounds on "+planetOneName+" weighs ");
      screen.print(WeightInKgOnFirstPlanet, 4);
      screen.println(" kilograms");
      screen.println();
      
      //Task 3
      String lineTwo = filePlanets.readString();
      //Getting planet name from file
      String planetTwoName = lineTwo.substring(
        0, lineTwo.indexOf("'s gravity"));
      double secondGravity = Double.valueOf(lineTwo.substring(
            lineTwo.indexOf("'s gravity is ")+14, lineTwo.length()-1));
      double WeightInKgOnSecondPlanet = WeightInKgOnEarth*secondGravity;
      //printing the kg on earth and the other 2 planets the  form the converted
      // pounds in a simpler format
      screen.print("Earth  ");
      screen.println(WeightInKgOnEarth, 2, 13);
      screen.print(planetOneName);
      //Use 20-length so all the numbers line up and each line is 20 char long
      screen.println(WeightInKgOnFirstPlanet, 2, (20-planetOneName.length()));
      screen.print(planetTwoName);
      screen.println(WeightInKgOnSecondPlanet, 2, (20-planetTwoName.length()));
      screen.println();

    }
}
