import sheffield.*;

public class Scenario {

   private Suspect attacker;
   private Room attackedIn;
   private Weapon attackedWith;

   public void setAtRandom() {
      attackedWith = Weapon.pickedAtRandom();
      attacker = Suspect.pickedAtRandom();
      attackedIn = Room.pickedAtRandom();
   }

   public void setByAsking(EasyReader keyboard) {
      attackedIn = Room.askWhichOne(keyboard);
      attacker = Suspect.askWho(keyboard);
      attackedWith = Weapon.askWhichOne(keyboard);
   }

   public void askAboutWrongOnes(Scenario rightOne, EasyReader keyboard) {
     if  (  attackedIn != rightOne.attackedIn )
          attackedIn = Room.askWhichOne(keyboard);
     if  (  attacker != rightOne.attacker  )
          attacker = Suspect.askWho(keyboard);
     if  (  attackedWith != rightOne.attackedWith  )
          attackedWith = Weapon.askWhichOne(keyboard);
   }

   public boolean hasBeenDiscovered(Scenario guess) {
      String output = "The victim was not attacted";
      boolean discovered = true;
      
      //validating the guess and creating the output
      if  (attackedIn != guess.attackedIn){
         output += " in " + guess.attackedIn;
         discovered = false;
      }
      if  (attacker != guess.attacker){
         output += " by " + guess.attacker;
         discovered = false;
      }
      if  (attackedWith != guess.attackedWith){
         output += " with " + guess.attackedWith;
         discovered = false;
      }
      if (!discovered){
         System.out.println(output);
      }
      return discovered;
  }
  
  public boolean isUnknown() {
      return attacker == null || attackedIn == null || attackedWith == null;
  }

  public String toString() {
    return  "The victim was attacked in " + attackedIn + " by " +
                attacker + " with "+ attackedWith;
  }

}
