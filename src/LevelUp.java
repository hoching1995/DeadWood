public class LevelUp {


    protected int rank;
    protected int cost;
    protected LevelUpCurrency currency;


    // the class here actually did save the data but  i  just thought that making a map at the game class was way easier.
    public LevelUp(int rank , int cost, LevelUpCurrency currency) {
        this.rank = rank;
        this.cost = cost;
        this.currency = currency;
    }

    public String toString(){
        return "rank:" + this.rank + " cost:" + this.cost +" currency:"+this.currency;
    }


    public int creditLevelUp(int credit, int cost){
        credit = credit - cost;
        return credit;
    }
//    public int getCrefitLevelUpCost(int level){
//
//    }
    public int getDollarsLevelUp(int dollars, int cost){
        dollars = dollars- cost;
        return dollars;
    }

    public int checkDollarsCosts(int rank, int c) {
         return 0;
    }


}

