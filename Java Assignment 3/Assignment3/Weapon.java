import sheffield.*;
import java.lang.Math;

public class Weapon {

   private final static int MAX_NUMBER=6;
   private static Weapon [] allTheWeapons;

   public static void initializeThem() {
    //initialize the weapons array
    allTheWeapons = new Weapon[MAX_NUMBER];
    //read file
    EasyReader file = new EasyReader("weapons.txt");
    int n = 0;
    //add the weapons to allTheWeapons
    while (!file.eof() && (n < MAX_NUMBER)){
      allTheWeapons[n] = new Weapon(file.readString());
      n += 1;
    }

    //list the weapons
    listTheWeapons();
   }

   public static Weapon askWhichOne(EasyReader keyboard)  {
    // user input
    String weaponInput = keyboard.readString("With what weapon? ").toUpperCase();
    // change to upper case and take "the " out
    if (weaponInput.length() > 4 && weaponInput.substring(0, 4).equals("the ")){
      weaponInput = weaponInput.substring(4, weaponInput.length());
    }
    
    // check if its a valid weapon
    for (int i = 0; i<MAX_NUMBER; i++){
      if (allTheWeapons[i] != null && allTheWeapons[i].weapon.toUpperCase().equals(weaponInput)){
        return allTheWeapons[i];
      }
    }
    return null;
   }

   public static void listTheWeapons()  {
     System.out.print("The weapons are "+allTheWeapons[0]);
     for (int i = 1; i<MAX_NUMBER-1; i++)
        System.out.print(", "+allTheWeapons[i]);
     System.out.println(" and "+allTheWeapons[MAX_NUMBER-1]);
   }

   public static Weapon pickedAtRandom() {
     //Getting random index
     int randIndex = (int) ((Math.random()*MAX_NUMBER));
     return allTheWeapons[randIndex];
   }

   private String weapon;

   private Weapon (String n)  {  weapon = n;  }

   public String toString()  {  return "the "+weapon.toLowerCase();  }

}
