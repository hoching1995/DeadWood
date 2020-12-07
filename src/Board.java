import org.w3c.dom.Document;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    List<Card> cardList;
    List<Room> roomList;
    private int cardNumber;
    //calling the reader
    public Board(String xml, String  cardxml ){
        Document doc = null;
        XMLBoardReader parsing = new XMLBoardReader();
        try{

            doc = parsing.getDocFromFile(xml);
            roomList = parsing.readBoardData(doc);

        }catch (Exception e){

            System.out.println("Error = "+e);

        }
        readCards(cardxml);
        for (Room room: roomList)
        {

            room.setCard(getRandomCard());
            room.setCardNumber(this.cardNumber);


        }
    }
    //getting the card randomly
    private Card getRandomCard (){
        int startRandomNum = ThreadLocalRandom.current().nextInt(1, 20);
        int endRandoNum =ThreadLocalRandom.current().nextInt(21,39 );
        int randomRandomNum = ThreadLocalRandom.current().nextInt(startRandomNum , endRandoNum );
        this.cardNumber= randomRandomNum;
        return cardList.get(this.cardNumber-1);
    }
    public List<Room> getRoomList(){
        return roomList;
    }
    private void readCards(String cardxml){
        Document doc = null;
        XMLCardsReader parsing = new XMLCardsReader();
        try{

            doc = parsing.getDocFromFile(cardxml);
            cardList= parsing.readCardData(doc);


        }catch (Exception e){

            System.out.println("Error = "+e);

        }
    }
//setting start room
    public Room getStartingRoom()
    {
        for ( var room : roomList)
        {
            if (room.getName().equals("trailer")) {
                //  System.out.println(" found neighbor = " + possibleNeighbor.getName());
                // put this room in the adjacent
                return room;

            }
        }
        return null;
    }
//setting office
    public Room getOffice(){
        for( var room : roomList)
        {
            if ( room.getName().equals("office")){
                return room;
            }
        }
        return null;
    }
}
