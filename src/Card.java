import java.util.ArrayList;

public class Card {
    private ArrayList<Part> cardPartList;
    private String cardName;
    private String img;
    private int budget;
    public Card(String cardName, String img, int budget){
        this.cardName = cardName;
        this.img = img;
        this.budget = budget;
    }
    //get card name
    public String getCardName(){
        return cardName;
    }
  //  public List<CardPart> getCardPartRoomList(){
  //      return cardPartList;
  //  }

    // get budget for the card
    public int getBudget(){
        return budget;
    }

    public void setCardPart( ArrayList<Part> cardPartList) {
        this.cardPartList = cardPartList;
    }
    public ArrayList<Part> getCardPart(){
        return this.cardPartList;

    }
  //  public void setPartRoom(List<CardPart> cardPartList){
  //      this.cardPartList=cardPartList;
  //  }



}
