class Player {
    private int credit;
    private int dollars;
    private int tokens;
    private int rank;
    private Room location;
    private boolean OnCard;
    private boolean OffCard;
    private String name;
    private boolean work;
    private Part job;

    public void setJob(Part job ){
        this.job = job ;
    }

    public Part getJob(){
        return this.job;
    }

    public boolean getWork(){
        return work;
    }
    public void setWork(boolean work ){
        this.work = work;
    }

    public Player(String name){
        this.name = name ;
    }

    public void addCredit(int number  ){
        this.credit = this.credit +number;
    }
    public void addDollar( int number ){
        this.dollars = this.dollars +number;
    }

    public void move(Room room)
    {
        // special thing here.
        setLocation(room);
    }

    public void setLocation(Room location){
        this.location = location;
    }

    public Room getLocation(){
        return location;
    }

    public String getName(){
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {

        this.credit = credit;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setOnCard(boolean set){
        this.OnCard = set;
    }
    public void setOffCard(boolean set){
        this.OffCard =set;
    }
    public boolean getOnCard(){
        return this.OnCard;
    }
    public boolean getOffCard(){
        return this.OffCard;
    }

    //upgrade with credit
    public void upGrade_Credit( int credit) {
        this.credit = this.credit- credit;
    }

    //upgrade with dollar
    public void upGrade_dollar(int dollars) {
this.dollars = this.dollars- dollars;
    }


}