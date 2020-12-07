public class CardPart {

    private String partName ;
    private int Level;
    public CardPart(String partName, int level){
        this.partName = partName;
        this.Level = level;
    }

    //override the print string
    public String toString(){
        return partName + " \n level:" + Level ;
    }

    public String getCardPartName(){
        return this.partName;
    }
    public int getCardPartLevel(){
        return this.Level;
    }
}
