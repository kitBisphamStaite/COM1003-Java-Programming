import sheffield.*;

public enum Room {

   KITCHEN,
   BALLROOM,
   CONSERVATORY,
   BILLIARD_ROOM,
   LIBRARY,
   STUDY,
   HALL,
   LOUNGE,
   DINING_ROOM,
   BEDROOM;

   static int roomCount = Room.values().length;

   static Room askWhichOne(EasyReader keyboard) {
    // user input
    String roomInput = keyboard.readString("Where are you? ").toUpperCase();
    // change to the enum format
    roomInput = roomInput.replace(" ", "_");
    if (roomInput.length() > 4 && roomInput.substring(0, 4).equals("THE_")){
      roomInput = roomInput.substring(4, roomInput.length());
    }

    // check if its a valid room
    Room [] rooms = values();
    for (int i = 0; i<rooms.length; i++){
      if (rooms[i].name().equals(roomInput)){
        return rooms[i];
      }
    }
    return null;
   }

   static Room pickedAtRandom() {
    //Getting random index
    int randIndex = (int) (Math.random()*roomCount);
    return values()[randIndex];
   }

   public static void listThem() {
    Room [] rooms = values();
    System.out.print("The rooms are "+rooms[0]);
    for (int i = 1; i<rooms.length-1; i++)
       System.out.print(", "+rooms[i]);
    System.out.println(" and "+rooms[rooms.length-1]);
   }

   public String toString() {
     return "the "+name().toLowerCase().replace("_"," ");
   }

}
