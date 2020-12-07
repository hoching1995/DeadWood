import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    GameButtonController button;
    GameButtonController button1;
    GameButtonController button2;
    GameButtonController button3;
    GameButtonController button4;

    PlayerInformationDisplayController playerDP1;
    PlayerInformationDisplayController playerDP2;
    PlayerInformationDisplayController playerDP3;
    PlayerInformationDisplayController playerDP4;
    PlayerInformationDisplayController playerDP5;
    PlayerInformationDisplayController playerDP6;
    PlayerInformationDisplayController playerDP7;
    ArrayList<PlayerDiceDisplayManger> diceList;

    InformationBoard informationBoard;

    public Game() {
        showStage();
        diceList = new ArrayList<PlayerDiceDisplayManger>();
    }
  //  add it as button
    private void showStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, 1500, 950);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }
    public Stage getGameStage(){
        return this.gameStage;
    }
    public AnchorPane getGamePane(){return this.gamePane;}
    public void createGame() {
        Image backgroundImage = new Image("sample/pngs/files/board.jpg", 1200, 900, false, true);

        BackgroundImage backgroundImageSet = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(backgroundImageSet));
        gameStage.show();

    }
    //    public void gameStart(){
//        GameController game = new GameController();
//        int gameDays= game.getPlayer();
//        while(gameDays !=0) {
//            game.startGame();
//            game.takeTurn();
//            gameDays--;
//        }
//    }
    public String PopWindow(String message) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText(message);
        td.showAndWait();

        return td.getResult();
    }
    public void displayMessage(String message){
       Image img = new Image("sample/pngs/files/board.jpg");

    }
    public void informationBoard(){
        try {
            informationBoard = new InformationBoard("");
            informationBoard.setTranslateX(1200);
            informationBoard.setTranslateY(750);
            informationBoard.setPrefSize(300,150);
            gamePane.getChildren().add(informationBoard);

        }catch (Exception e){

        }
    }
    public void button() {
        try {

            button = new GameButtonController("");
            button.setTranslateX(0);
            button.setTranslateY(900);
            gamePane.getChildren().add(button);

            button1 = new GameButtonController("");
            button1.setTranslateX(200);
            button1.setTranslateY(900);
            gamePane.getChildren().add( button1);

            button2 = new GameButtonController("");
            button2.setTranslateX(400);
            button2.setTranslateY(900);
            gamePane.getChildren().add( button2);

            button3= new GameButtonController("");
            button3.setTranslateX(600);
            button3.setTranslateY(900);
            gamePane.getChildren().add(  button3);

            button4 = new GameButtonController("");
            button4.setTranslateX(800);
            button4.setTranslateY(900);
            gamePane.getChildren().add( button4);
        } catch (FileNotFoundException e) {

        }
    }
    public void Player1Display(String message) {
        try {
            this.playerDP1 = new PlayerInformationDisplayController(message);
            playerDP1.setTranslateX(1200);
            playerDP1.setTranslateY(0);
            gamePane.getChildren().add(playerDP1);

        } catch (FileNotFoundException e) {
        }
    }
    public void Player2Display(String message) {
        try {

            this.playerDP2 = new PlayerInformationDisplayController(message);
            playerDP2.setTranslateX(1200);
            playerDP2.setTranslateY(130);
            gamePane.getChildren().add(playerDP2);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player3Display(String message) {
        try {

            this.playerDP3 = new PlayerInformationDisplayController(message);
            playerDP3.setTranslateX(1200);
            playerDP3.setTranslateY(260);
            gamePane.getChildren().add(playerDP3);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player4Display(String message) {
        try {

            this.playerDP4 = new PlayerInformationDisplayController(message);
            playerDP4.setTranslateX(1200);
            playerDP4.setTranslateY(390);
            gamePane.getChildren().add(playerDP4);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player5Display(String message) {
        try {
            this.playerDP5 = new PlayerInformationDisplayController(message);
            playerDP5.setTranslateX(1200);
            playerDP5.setTranslateY(520);
            gamePane.getChildren().add(playerDP5);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player6Display(String message) {
        try {

            this.playerDP6 = new PlayerInformationDisplayController(message);
            playerDP6.setTranslateX(1200);
            playerDP6.setTranslateY(650);
            gamePane.getChildren().add(playerDP6);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player7Display(String message) {
        try {

            this.playerDP7 = new PlayerInformationDisplayController(message);
            playerDP7.setTranslateX(1200);
            playerDP7.setTranslateY(780);
            gamePane.getChildren().add(playerDP7);
        } catch (FileNotFoundException e) {
        }
    }
    public void Player8Display(String message) {
        try {

            this.playerDP2 = new PlayerInformationDisplayController(message);
            playerDP2.setTranslateX(1200);
            playerDP2.setTranslateY(910);
            gamePane.getChildren().add(playerDP2);
        } catch (FileNotFoundException e) {
        }
    }
    //setting player dice  xy
    public void playerDice (int x, int y, int h ,int w, int level, int player){

        String Player ="Player";
        Player = Player+player+level;

        var dice = new PlayerDiceDisplayManger();
        diceList.add(dice);

        String url = "-fx-background-image: url('"+dice.getString(Player)+"');";

        diceSetUp(dice,x,y,h,w,url);

    }
    // passing player dice xy
    public void diceSetUp(PlayerDiceDisplayManger player, int x, int y, int h, int w , String url){

        player.setPrefSize(40,40);

        player.setTranslateY(y);
        player.setTranslateX(x);
        player.setStyle(url);

        gamePane.getChildren().add(player);
    }
    //setting player card  xy
    public void card(int x, int y ,int h , int w, String url){

        var card = new CardDisplayManger();

        String newUrl = "-fx-background-image: url('"+card.getString(url)+"');";
        cardImageSetUp(card, x , y ,newUrl );


    }
    // passing player card xy
    public  void cardImageSetUp(CardDisplayManger card, int x , int y ,String url ){
    card.setTranslateX(x);
    card.setTranslateY(y);
    card.setPrefSize(205,115);
    card.setStyle(url);
    gamePane.getChildren().add(card);

    }
    public void removeDice() {
        for (var dice :this.diceList)
        {
            gamePane.getChildren().remove(dice);
        }
    }
    public Button addTaker(int x, int y, int w, int h) {
        Button taker = new Button();
        taker.setTranslateX(x+3);
        taker.setTranslateY(y);
        taker.setPrefSize(w-2,h-3);
       taker.setStyle("-fx-background-image: url('sample/pngs/files/shot.png'); -fx-background-color: transparent");
        gamePane.getChildren().add(taker);

        return taker;
    }
}