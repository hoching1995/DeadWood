public class LevelUp {


    protected int rank;
    protected int cost;


    // the class here actually did save the data but  i  just thought that making a map at the game class was way easier.
    public LevelUp(int rank) {
        this.rank = rank;
    }

    public String toString(){
        return "rank: " + this.rank + " cost" + this.cost;
    }


    public int creditLevelUp(int credit, int cost){
        credit = credit - cost;
        return credit;
    }
    public int getDollarsLevelUp(int dollars, int cost){
        dollars = dollars- cost;
        return dollars;
    }

    public int checkDollarsCosts(int rank, int c) {
         return 0;
    }


}
class DollarLevelUp extends LevelUp{

    public DollarLevelUp(int rank) {
        super(rank);
    }
    public void dollarCost(int cost){
        this.cost =cost;
    }
    public String toString(){
        return "rank: " + this.rank + " cost" + this.cost ;
    }
}
class CreditLevelUp extends LevelUp{
    public CreditLevelUp(int rank) {
        super(rank);
    }
    public void creditCost(int cost){
        this.cost = cost;
    }
    public String toString(){
        return "rank: " + this.rank + " cost" + this.cost;
    }
}