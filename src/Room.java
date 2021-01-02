import java.util.ArrayList;
import java.util.Map;

public class Room {
    private ArrayList<Room> adjacentRoom;
    private String name;
    private ArrayList<Part> partRoom;
    private Card card;
    private ArrayList<Takers>  takers;
    private ArrayList<LevelUp> levelUpsList;
    private Takers getTaker;
    private int markers;
    private int x ;
    private int y ;
    private int h ;
    private int w ;
    boolean cardFace ;
    private int cardNumber;

    public Room(String name, int x , int y, int h , int w ) {
        this.name = name;
        this.x = x ;
        this.y = y ;
        this.h = h ;
        this.w = w ;
    }

    public ArrayList<Takers>  getTakers() {
        return takers;
    }

    public int getNumberOfTaker(){
        return this.markers;
    }

    public void setTakers(ArrayList<Takers>  takers) {
        this.markers = takers.size();
        this.takers = takers;
    }

    public ArrayList<LevelUp> getLevelUpsList() {
    return levelUpsList;
    }

    public void setLevelUpsList(ArrayList<LevelUp> levelUpsList) {
        this.levelUpsList = levelUpsList;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public void setCardNumber(int card ){
        this.cardNumber = card;
    }

    public void setCardFace(boolean cardFace) {
        this.cardFace = cardFace;
    }

    public boolean getCardFace(){
        return this.cardFace;
    }
    // should br in partRoom Arraylist
    public int getX(){
        return this.x;
   }
    public int getY(){
        return this.y;
    }
    public int getH(){
        return this.h;
    }
    public int getW(){
        return this.w;
    }
    // remove taker
    public void removeOneTaker(){
        this.markers = this.markers -1;
    }
    //settingCard for rooms
    public void setCard(Card card) {
        this.card = card;
    }

    public int getBudget() {
        return this.card.getBudget();
    }
    //print adjacent room
    public ArrayList<Room> getAdjacentRooms() {
        // loop through adjacentRoom
        // and print the name
        ArrayList<Room> list = new ArrayList<>() ;
        for (int i = 0; i < this.adjacentRoom.size(); i++) {
            var room = this.adjacentRoom.get(i);

            if (!room.getIsClosed() && !room.name.equals("trailer")||room.name.equals("office"))
                list.add(this.adjacentRoom.get(i));

        }
        return list;
    }
    //get rooms part, such as jobs.
    public ArrayList<String> getPartRooms(int level) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0 ; i<=level ; i++) {
         String levelToString = Integer.toString(i);
            for (Part partList : this.partRoom) {
                if (partList.toString().contains(levelToString)) {
                    list.add( partList.toString());
                }
            }
        }
        return list;
    }
    public void setPartRoom(ArrayList<Part> partList) {
        this.partRoom = partList;
    }

    public ArrayList<Part> getPartRoom() {
        return partRoom;
    }
    //get adjacent from room list
    public ArrayList<Room> getAdjacentRoom() {

        return adjacentRoom;
    }
    //setting Adjacent Room
    public void setAdjacentRoom(ArrayList<Room> rooms) {
        this.adjacentRoom = rooms;
    }
    //getting player name
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
    // getting the on card jobs.
    public ArrayList<Part> getCardPartRooms(int level) {
        var list = new ArrayList<Part>();
        // loop through adjacentRoom
        // and print the name
        for (int i = 0 ; i<= level ; i++) {
            String  levelToString = Integer.toString(i);
            for (Part partList : this.card.getCardPart()) {
                if (partList.toString().contains(levelToString)) {
                   list.add(partList);
                }
            }
        }
        return list;
    }

    public int  getPlayerOnCardJobNumber(Part job ) {
       int count = 1;
            for (Part partList : this.card.getCardPart()) {
                if (partList.toString().contains(job.toString())) {
                    break;
                }
                count ++;
        }
        return count;
    }

    public int  getNumberOfOnCardJob() {
       int count =0 ;
        for (int i = 0 ; i<= 6 ; i++) {
            String  levelToString = Integer.toString(i);
            for (Part partList : this.card.getCardPart()) {
                if (partList.toString().contains(levelToString)) {
                   count++;
                }
            }
        }
        return count;
    }

    //get closed room
    public boolean getIsClosed(){
        return this.markers <= 0;
    }
}

