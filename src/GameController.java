import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



// the entire game controller
public class GameController {

    int numbersOfPlayer = 0;
    List<Player> playerList;
    Board board;
    Game view;
    private List<Room>  list ;
    //passing the xml and
    public GameController() {
     //giving the xml file
        board = new Board("board.xml", "cards.xml");
        view = new Game();
    }
    //getting numbers of player with a view box
    public int showViewAndGetDays(){
        this.view.createGame();
        this.view.button();
        this.view.informationBoard();
        this.numbersOfPlayer = Integer.parseInt(this.view.PopWindow("how many players ?"));
        while(numbersOfPlayer<=1 || numbersOfPlayer >8) {
           this.numbersOfPlayer = Integer.parseInt(this.view.PopWindow("please type in a number between 2 - 8 , this game can only handle " +
                    "up to 8 player and must be more than 2 player "));
        }

        this.playerList = new LinkedList<>();
        // create players
        for (int i = 1; i < numbersOfPlayer + 1; i++) {
            //System.out.println("please enter player " + i + " name");
            String playerName = this.view.PopWindow("please enter player " + i + " name");
            //String playerName = input.next();
            playerList.add(new Player(playerName));
        }

        if(numbersOfPlayer<=3){
            return 3;
        }
    return 4;
    }
    //setting the card for each room and setting the level of player based on the number of player
    public int startGame() {
        int x = 0;
        int y = 0;
        int h = 0;
        int w = 0;
        setList();
        // initializing the players
        for (int i = 0; i < numbersOfPlayer; i++) {
            // move most of this to Player constructor
            playerList.get(i).setCredit(100);
            playerList.get(i).setDollars(100);
            playerList.get(i).setRank(1);
            playerList.get(i).setTokens(0);
            playerList.get(i).setOffCard(false);
            playerList.get(i).setOffCard(false);
            playerList.get(i).setJob(null);
            playerList.get(i).setWork(false);
            playerList.get(i).setLocation(board.getStartingRoom());

        }
            //set takers and set card face
        for (Room room: this.list) {
            room.setCardFace(false);
            if (room.getTakers() != null) {

            for (Takers takers : room.getTakers()) {
                x = takers.getX();
                y = takers.getY();
                h = takers.getH();
                w = takers.getH();
                var takerButton = this.view.addTaker(x,y,w,h);
                takers.setView(takerButton);
            }
        }
    }
        setRoomCardDisplay();

        if(numbersOfPlayer==5){
            for (int i = 0; i < numbersOfPlayer; i++) {
                playerList.get(i).setCredit(2);
            }
        }else if(numbersOfPlayer==6) {
            for (int i = 0; i < numbersOfPlayer; i++) {
                playerList.get(i).setCredit(4);
            }
        }else if(numbersOfPlayer==8){
            for (int i = 0; i < numbersOfPlayer; i++) {
                playerList.get(i).setRank(2);
            }
        }
return numbersOfPlayer;
    }
    public void setList(){
        this.list =board.getRoomList();
    }
    // this will print current player information
    public void DisplayAllPlayer(){
        showAllPlayerDice();
        String message= " ";
        for(int i =0; i < playerList.size();i++) {
            var player = playerList.get(i);
            if(player.getJob()== null){
                message = getPlayerInfoFormat(player.getName(), player.getRank(), null, player.getDollars(), player.getCredit(), player.getTokens());
            }else {
                message = getPlayerInfoFormat(player.getName(), player.getRank(), player.getJob().toString(), player.getDollars(), player.getCredit(),player.getTokens());
            }
            if (i == 0) {
                this.view.Player1Display(message);
            } else if (i == 1) {
                this.view.Player2Display(message);
            } else if (i == 2) {
                this.view.Player3Display(message);
            } else if (i == 3) {
                this.view.Player4Display(message);
            } else if (i == 4) {
                this.view.Player5Display(message);
            } else if (i == 5) {
                this.view.Player6Display(message);
            } else if (i == 6) {
                this.view.Player7Display(message);
            } else if (i == 7) {
                this.view.Player8Display(message);
            }

        }
    }
    //show all player dice
    public void showAllPlayerDice(){
        // clear the last dice
        this.view.removeDice();

        for(int i = 0 ; i < this.playerList.size();i++) {
            int x = 0;
            int y = 0;
            int w = 0 ;
            int h = 0;
            var player = playerList.get(i);
            Room room = player.getLocation();
            if (player.getOffCard()) {
                Part part = player.getJob();
                x = part.getX()+3;//adjustment
                y = part.getY()+3;
                w = part.getW();
                h = part.getH();

            } else if (player.getOnCard()) {
                x = room.getX();
                y = room.getY();
                w = room.getW();
                h = room.getH();

                if (player.getLocation().getNumberOfOnCardJob() >= 3){
                    if (player.getLocation().getPlayerOnCardJobNumber(player.getJob())==1){
                        x = x + 20;
                        y = y + 47;
                    } else if(player.getLocation().getPlayerOnCardJobNumber(player.getJob())==2){
                        x = x + 83;
                        y = y + 47;
                    }
                    else if(player.getLocation().getPlayerOnCardJobNumber(player.getJob())==3){
                        x = x + 145;
                        y = y + 47;
                    }

                }else if(player.getLocation().getNumberOfOnCardJob() == 2){
                    if (player.getLocation().getPlayerOnCardJobNumber(player.getJob())==1){
                        x = x + 53;
                        y = y + 47;
                    }else if (player.getLocation().getPlayerOnCardJobNumber(player.getJob())==2){
                        x = x + 115;
                        y = y + 47;
                    }
                }
                else if(player.getLocation().getNumberOfOnCardJob() == 1){
                    x = x + 83;
                    y = y + 47;
                }

            } else {


                x = room.getX();
                y = room.getY();
                w = room.getW();
                h = room.getH();


                for (int k = 0; k < i; k++) {


                    if (playerList.get(i).getLocation() == playerList.get(k).getLocation() && playerList.get(i).getWork() == false && playerList.get(k).getWork() == false) {
                        x = x + 40;
                    }
                }

// check read xml file if area is linked to adjroom.


            }
            this.view.playerDice(x, y, h, w, playerList.get(i).getRank() - 1, i);
        }
    }
    //making player information board as a string
    public String getPlayerInfoFormat(String name , int level, String work, int money, int credit,int tokens ){
        String outPutString = "Name: "+ name
                 + "\nwork:"+ work + "\nMoney: "+ money + "\nCredit: "+ credit + "\nTokens: " +tokens; ;
        return outPutString;
    }
    //making final result as a string
    public String setFinalResultString(String name , int level, int money, int credit ){
        String outPutString = "Name: "+ name
                 + "\nMoney: "+ money + "\nCredit: "+ credit + "\nLevel"+level +"\n";
        return outPutString;
    }
    //show the success message
    public void afterLevelUp(int currentPlayer, Player player){
        disableAllButton();
        this.view.informationBoard.setText(" you have succeed up grade to Level"+ player.getRank());
        this.view.button.setText("continue");
        showAllPlayerDice();
        DisplayAllPlayer();
        this.view.button.setVisible(true);
        this.view.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                takeTurn(currentPlayer+1);

            }
        });

    }
    //using credit to level up and set the cost
    public void creditLevelCalculation(Player player, HashMap<Integer,Integer> map, int currentPlayer, String information){
        this.view.informationBoard.setText(information);
        for(int i = player.getRank() ; i <= map.size();i++) {
            int nextLevel = i+1;
            if (i == player.getRank()  && map.size() >= player.getRank()  && map.get(nextLevel) < player.getCredit()) {


                this.view.button.setText("Level" + (player.getRank() + 1));
                this.view.button.setVisible(true);
                int finalI = i;
                this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() +1));
                        player.upGrade_Credit(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });

            } else if (i == player.getRank() + 1 && map.size() >= player.getRank() + 1 && map.get(nextLevel) < player.getCredit()) {
                this.view.button1.setText("Level" + (player.getRank() + 1));
                this.view.button1.setVisible(true);
                int finalI = i;
                this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 2));
                        player.upGrade_Credit(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);

                    }
                });

            }else if (i == player.getRank() + 2 && map.size() >= player.getRank() + 2 && map.get(nextLevel) < player.getCredit()) {
                this.view.button2.setText("Level" + (player.getRank() + 3));
                this.view.button2.setVisible(true);
                int finalI = i;
                this.view.button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 3));
                        player.upGrade_Credit(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });
            }else if (i == player.getRank() + 3 && map.size() >= player.getRank() + 3 && map.get(nextLevel) < player.getCredit()) {
                this.view.button3.setText("Level" + (player.getRank() + 4));
                this.view.button3.setVisible(true);
                int finalI = i;
                this.view.button3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 4));
                        player.upGrade_Credit(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);

                    }
                });

            }else if (i == player.getRank() + 4 && map.size() >= player.getRank() + 4 && map.get(nextLevel) < player.getCredit()) {
                this.view.button4.setText("Level" + (player.getRank() + 5));
                this.view.button4.setVisible(true);
                int finalI = i;
                this.view.button4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 5));
                        player.upGrade_Credit(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });
            }
        }

    }
    //using dollar to level up and set the cost
    public void dollarLevelCalculation(Player player, HashMap<Integer,Integer> map, int currentPlayer, String information){
        this.view.informationBoard.setText(information);

        for(int i = player.getRank() ; i <= map.size();i++) {
            int nextLevel = i+1;
            if (i == player.getRank()  && map.size() >= player.getRank()  && map.get(nextLevel) < player.getDollars()) {


                this.view.button.setText("Level" + (player.getRank() + 1));
                this.view.button.setVisible(true);
                int finalI = i;
                this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() +1));
                        player.upGrade_dollar(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });

            } else if (i == player.getRank() + 1 && map.size() >= player.getRank() + 1 && map.get(nextLevel) < player.getDollars()) {
                this.view.button1.setText("Level" + (player.getRank() + 2));
                this.view.button1.setVisible(true);
                int finalI = i;
                this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 2));
                        player.upGrade_dollar(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);

                    }
                });
            }else if (i == player.getRank() + 2 && map.size() >= player.getRank() + 2 && map.get(nextLevel) < player.getDollars()) {
                this.view.button2.setText("Level" + (player.getRank() + 3));
                this.view.button2.setVisible(true);
                int finalI = i;
                this.view.button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 3));
                        player.upGrade_dollar(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });
            }else if (i == player.getRank() + 3 && map.size() >= player.getRank() + 3 && map.get(nextLevel) < player.getDollars()) {
                this.view.button3.setText("Level" + (player.getRank() + 4));
                this.view.button3.setVisible(true);
                int finalI = i;
                this.view.button3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        player.setRank((player.getRank() + 4));
                        player.upGrade_dollar(map.get((player.getRank())));
                        DisplayAllPlayer();
                        afterLevelUp(currentPlayer, player);
                    }
                });
            }else if (i == player.getRank() + 4 && map.size() >= player.getRank() + 4 && map.get(nextLevel) < player.getDollars()) {
                    this.view.button4.setText("Level" + (player.getRank() + 5));
                    this.view.button4.setVisible(true);
                    int finalI = i;
                    this.view.button4.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            player.setRank((player.getRank() + 5));
                            player.upGrade_dollar(map.get((player.getRank())));
                            DisplayAllPlayer();
                            afterLevelUp(currentPlayer, player);
                        }
                    });
                }
            }
        }
    //chose credit or dollar to level up
    public void LevelUp (Player player, int currentPlayer  ){
        int rank = 0 ;
        int cost = 0 ;
        HashMap<Integer,Integer> dollar = new HashMap<Integer, Integer>();
        HashMap<Integer,Integer> credit = new HashMap<Integer, Integer>();
        var levelUpList = player.getLocation().getLevelUpsList();
        for(LevelUp list : levelUpList){
            rank = Integer.parseInt(list.toString().substring(5,6));
            cost = Integer.parseInt(list.toString().substring(12,14).replace(" ",""));
            if(list.toString().contains("Dollar")){
                dollar.put(rank,cost);
            }else {
                credit.put(rank,cost);
            }
        }

        this.view.informationBoard.setText("Upgrade with dollar or credit?");

        String dollarInformation = "cost table \n dollar:\n Level: 2  Cost:" + dollar.get(2)+"\n Level: 3  Cost:" + dollar.get(3)+
                "\n Level: 4  Cost:" + dollar.get(4)+"\n Level: 5  Cost:" + dollar.get(5)+"\n Level: 6  Cost:" + dollar.get(6);

        String creditInformation = "cost table \n credit:\n Level: 2  Cost:" + credit.get(2)+"\n Level: 3  Cost:" + credit.get(3)+
                "\n Level: 4  Cost:" +credit.get(4)+"\n Level: 5  Cost:" + credit.get(5)+"\n Level: 6  Cost:" + credit.get(6);
        System.out.println(dollarInformation);

        if(dollar.get(player.getRank()+1)< player.getDollars()){
            this.view.button.setText("Dollar");
            this.view.button.setVisible(true);
            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    dollarLevelCalculation(player,dollar,currentPlayer,dollarInformation);
                }
            });
        }else {
            failUpgrade(currentPlayer );
        }
        if(credit.get(player.getRank()+1)< player.getCredit()){
            this.view.button1.setText("Credit");
            this.view.button1.setVisible(true);
            this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                  creditLevelCalculation(player,credit,currentPlayer,creditInformation);
                }
            });
        } else {
            failUpgrade(currentPlayer );};
    }
    // if player have no money or credit, then here is the fail code
    public void failUpgrade(int currentPlayer ){
        System.out.println("function: failUpgrade");
        this.view.informationBoard.setText("sorry you dont have enough money or credit to upgrade");
        this.view.button.setVisible(true);
        this.view.button.setText("continue");
        this.view.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                takeTurn(currentPlayer+1);
            }
        });

    }
    //check the room budget
    public void checkBudget(Player player, int total, int currentPlayer) {
        int nextPlayer = currentPlayer +1  ;
        this.view.button1.setVisible(false);
        if (nextPlayer > playerList.size()) {
            nextPlayer = 0;
        }

        if (total < player.getLocation().getBudget()) {
            String loseString = "Sorry you have to stay at work, good luck";

            if (player.getOffCard() == true) {
                this.view.informationBoard.setText(loseString + "\n you have got 1 dollar for working off the card");
                this.view.button.setText("continue");
                player.addDollar(1);
                int finalNextPlayer = nextPlayer;
                this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        takeTurn(finalNextPlayer);
                    }
                });
            } else {

                this.view.informationBoard.setText(loseString + "\n you have get nothing for working on the card");
                this.view.button.setText("continue");
                int finalNextPlayer = nextPlayer;
                this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        takeTurn(finalNextPlayer);
                    }
                });

            }
        } else {
            if (player.getOffCard() == true) {
                this.view.informationBoard.setText("Nice job, you have earn 1 dollar and \n1 credit for working off card \n now you are free to go ");
                player.addDollar(1);
                player.addCredit(1);
                this.view.button.setText("continue");
                int finalNextPlayer = nextPlayer;
                this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        takeTurn(finalNextPlayer);
                    }
                });
            } else if(player.getOnCard()==true){
                int taker = 0;
                for (Room room : this.list) {
                    if (room.toString() == player.getLocation().toString()) {
                        taker = room.getNumberOfTaker();
                        taker = taker -1 ;
                        room.getTakers().get(taker).getView().setVisible(false);
                        room.removeOneTaker();
                        if (room.getNumberOfTaker() == 0) {

                            for( Player otherPlayer : this.playerList){
                                if(otherPlayer.getLocation() ==player.getLocation()){
                                    otherPlayer.setJob(null);
                                    otherPlayer.setWork(false);
                                    otherPlayer.setOffCard(false);
                                    otherPlayer.setOnCard(false);
                                    otherPlayer.setTokens(0);
                                    otherPlayer.addCredit(2);
                                }
                            }

                            room.setCardFace(false);
                            this.view.informationBoard.setText("LALAALALALA,\n you finished the entire scene \n now you will get your bonuses!\n " +
                                    "all other player got paid 2 credits!!!! ");
                            this.view.button.setText("continue");
                            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    getOnCardCredit(player.getRank(), player.getLocation().getBudget(),player,currentPlayer);
                                }
                            });

                        }else {
                            this.view.informationBoard.setText("Nice job, you have earn two credits\n now you are free to go ");
                            player.addDollar(2);
                            this.view.button.setText("continue");
                            int finalNextPlayer = nextPlayer;
                            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    takeTurn(finalNextPlayer);
                                }
                            });
                        }
                    }
                }
            }
            player.setJob(null);
            player.setWork(false);
            player.setOffCard(false);
            player.setOnCard(false);
            player.setTokens(0);
        }
    }
    //roll dice and print budget
    public int rollDiceAndCheck(Player player, int token, int currentPlayer){
    System.out.println("function: rollDiceAndCheck");
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
        int total = randomNum + token;
        this.view.button1.setVisible(false);
        this.view.informationBoard.setText("Player : "+player.getName()+"\n your dice: " + randomNum + "  your tokens : " + player.getTokens()+"\n"+"this is the Budget: " + player.getLocation().getBudget());
        this.view.button.setText("continue");
        this.view.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkBudget(player,total,currentPlayer);
            }
        });
        return token ;
    }
    // for player rehearsal
    public void rehearsal(Player player,int currentPlayer){
        int nextPlayer = currentPlayer+1;
        if(nextPlayer >= playerList.size()){
            nextPlayer = 0;
        }
        player.setTokens(player.getTokens()+1);
        this.view.informationBoard.setText("Player : "+player.getName()+"\nou had chosen rehearsal.");
        DisplayAllPlayer();
        takeTurn(nextPlayer);

    }
    //this will do the act rehearsal or roll, also print related information and pay.
    public void work( int currentPlayer){
        disableAllButton();
        System.out.println("function: work");
        var player = playerList.get(currentPlayer);
        this.view.informationBoard.setText("Player : "+player.getName()+"\nDo you want to roll or rehearsal ");

        this.view.button.setText("Roll");
        this.view.button.setVisible(true);
        this.view.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rollDiceAndCheck(player,player.getTokens(), currentPlayer);
            }
        });
        this.view.button1.setText("Rehearsal");
        this.view.button1.setVisible(true);
        this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rehearsal(player,currentPlayer);
            }
        });


//        if (roll.equals("roll")) {
//            int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
//            int total = randomNum + player.getTokens();
//            System.out.println(" your dice: " + randomNum + "  your tokens : " + player.getTokens());
//            System.out.println("this is the Budget: " + player.getLocation().getBudget());
//            if (total < player.getLocation().getBudget()) {
//                System.out.println("sorry you have to stay at work, good luck");
//                if(player.getOffCard()==true){
//                    player.addCredit(1);
//                }
//
//            } else {
//                System.out.println("Nice work ! now you finish your job, and you are free ");
//                if(player.getOffCard()==true){
//                    player.addCredit(1);
//                    player.addDollar(1);
//                }else if (player.getLocation().getMarkers()==0)
//                {
//                    // player.addCredit(2);
//                    player.addCredit(getOnCardCredit(player.getRank(), player.getLocation().getBudget()));
//                    player.getLocation().decrementMarkers();
//                    for( var x: playerList){
//                        if(x.getLocation()==player.getLocation() && x.getWork()==true){
//                            //bonus
//                            x.addCredit(1);
//                            x.setJob(null);
//                            x.setWork(false);
//                            x.setOffCard(false);
//                            x.setOnCard(false);
//                            System.out.println(x.getName()+ " has been finished the job since all the counter at the location opened ");
//
//                        }
//                    }
//
//                }else {
//                    player.addCredit(2);
//                    System.out.println(player.getName()+" you got paid 2 credit" );
//                }
//                player.setJob(null);
//                player.setWork(false);
//                player.setOffCard(false);
//                player.setOnCard(false);
//            }
//        }else if(roll.equals("rehearsal")){
//            player.setTokens(+1);
//        }
    }
    //setting the next player
    public int nextPlayer(int currentPlayer){
        int nextPlayer = currentPlayer+1;
        if( nextPlayer >= playerList.size()){
            nextPlayer=0;
        }

        return nextPlayer;
    }
    //player select off card job options
    public void selectJobOffCard(Player player, int currentPlayer ){
        this.view.informationBoard.setText("Player : "+player.getName()+"\n Which job do you want to have ?");
        var list = player.getLocation().getPartRooms(player.getRank());
            player.setOffCard(true);
        for( int i = 0 ; i < list.size();i++){
            GameButtonController button ;
            System.out.println("size:"+list.size());
            System.out.println(list.get(i));
            
            if(i == 0){
              button = this.view.button;
              this.view.button1.setVisible(false);
              this.view.button2.setVisible(false);
              this.view.button3.setVisible(false);
            }
            else if(i ==1){
               button= this.view.button1;
                this.view.button2.setVisible(false);
                this.view.button3.setVisible(false);
                this.view.button4.setVisible(false);
            }
            else if(i == 2){
                button= this.view.button2;
                this.view.button3.setVisible(false);
            }
            else {
                button= this.view.button3;
                this.view.button2.setVisible(false);
                this.view.button3.setVisible(false);
                }
            button.setText(list.get(i));
            button.setVisible(true);
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int nextPlayer =nextPlayer(currentPlayer);
                    player.setJob(player.getLocation().getPartRoom().get(finalI));
                    DisplayAllPlayer();

                    player.setWork(true);


                    takeTurn(nextPlayer);
                }
            });
        }
    }
    //player select on card job options
    public void selectJobOnCard(Player player,int currentPlayer){
        int nextPlayer = currentPlayer+1;
        if( nextPlayer >= playerList.size()){
            nextPlayer=0;
        }
        disableAllButton();

        var list =  player.getLocation().getCardPartRooms(player.getRank());

       for (int i = 0 ; i<list.size(); i++){
           if(i ==0){

               this.view.button.setText(list.get(0).toString());
               this.view.button.setVisible(true);
               this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       int nextPlayer =nextPlayer(currentPlayer);
                       player.setJob(list.get(0));
                       player.setWork(true);
                       player.setOnCard(true);

                       DisplayAllPlayer();
                       takeTurn(nextPlayer);
                   }
               });
           }
           else if(i==1){
               this.view.button1.setText(list.get(1).toString());
               this.view.button1.setVisible(true);
               this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       player.setJob(list.get(1));
                       int nextPlayer =nextPlayer(currentPlayer);
                       player.setWork(true);
                       player.setOnCard(true);
                       DisplayAllPlayer();
                       takeTurn(nextPlayer);
                   }
               });

           }
           else if(i==2){
               this.view.button2.setText(list.get(2).toString());
               this.view.button2.setVisible(true);
               this.view.button2.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       player.setJob(list.get(2));
                       int nextPlayer =nextPlayer(currentPlayer);
                       player.setWork(true);
                       player.setOnCard(true);
                       DisplayAllPlayer();
                       takeTurn(nextPlayer);
                   }
               });
               this.view.button3.setVisible(false);
               this.view.button4.setVisible(false);


           }


       }

    }
    //player select off card or on job
    public void selectJobOnOffCard(Player player, int currentPlayer) {
        var OnCardRoom = player.getLocation().getCardPartRooms(player.getRank()).size();
        var OffCardRoom = player.getLocation().getPartRooms(player.getRank()).size();
        this.view.informationBoard.setText("Player : "+player.getName()+"\nDo you want to work of card or off card ?  ");
        System.out.println(player.getName()+" turn, in function selectJobOnOffCard");
        System.out.println();
        if (OnCardRoom != 0) {
            for(int i = 0 ; i <OnCardRoom;i++){
                System.out.println("on card: "+player.getLocation().getCardPartRooms(player.getRank()).get(i));
            }
            this.view.button.setText("On card ");
            this.view.button.setVisible(true);
            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   selectJobOnCard(player,currentPlayer);
                   player.setOnCard(true);
                }
            });
        }else this.view.button.setVisible(false);

        if(OffCardRoom!=0) {
            for(int i = 0 ; i < OffCardRoom;i++){
                System.out.println("off card: "+player.getLocation().getPartRooms(player.getRank()).get(i));
            }
            this.view.button1.setText("Off card ");
            this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectJobOffCard(player, currentPlayer);
                    player.setOffCard(true);
                }
            });
        }else {
            this.view.button1.setVisible(false);
        }
        if(OffCardRoom == 0 && OnCardRoom==0){
            this.view.informationBoard.setText("Player : "+player.getName()+"\nSorry,there is no job for you at this moment."+
                    "\n please go other place to level up ");
            this.view.button1.setVisible(false);
            this.view.button.setVisible(true);
            this.view.button.setText("End Turn");
            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int nextPlayer = currentPlayer+1;
                    if(nextPlayer>playerList.size()){
                        nextPlayer=0;
                    }
                    takeTurn(nextPlayer);
                }
            });


        }
    }
    //allow players to get job or not
    public void workOrNotWork(Player player, int currentPlayer){
        if ( !player.getLocation().equals(board.getOffice())) {
            this.view.informationBoard.setText("Player: " + player.getName()+"\nDo you want to get a job ?");
            this.view.button.setText("YES");
            this.view.button.setVisible(true);
            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectJobOnOffCard(player, currentPlayer);
                }
            });

            this.view.button1.setText("NO");
            this.view.button1.setVisible(true);
            this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    takeTurn(currentPlayer + 1);
                }
            });
        }else {
            LevelUp(player,currentPlayer);
        }
//        System.out.println(" y /n ");
//        String actInput = input.next();
//        if (actInput.equals("y")) {
//            System.out.println("which job you want to work ? ");
//            player.getLocation().printCard();
//            //player.getLocation().
//            if (player.getLocation().getPartRooms(player.getRank()) == null) {
//                System.out.println("there is no job off card for your rank");
//            } else {
//                System.out.println("Off card job :");
//                player.getLocation().printPartRooms(player.getRank());
//
//            }
//            if (player.getLocation().getCardPartRooms(player.getRank()) == null) {
//                System.out.println("there is no job on card for your rank");
//            } else {
//                System.out.println("On card job :");
//                player.getLocation().printCardPartRooms(player.getRank());
//            }
//
//        }
//        System.out.println("  y = On card / n = off card / q = quit ");
//        String answer = input.next();
//        if (answer.equals("n")) {
//            player.getLocation().printPartRooms(player.getRank());
//            System.out.println("Type the number of the job you would like to chose. ex: like 0, 1 ,2 ");
//            String actIndex = input.next();
//
//            int actIndexToInt = Integer.parseInt(actIndex);
//            Part act = player.getLocation().getPartRoom().get(actIndexToInt);
//            player.setJob(act);
//            System.out.println("now you are working at " + player.getJob());
//            player.setWork(true);
//            player.setOffCard(true);
//        }
//        else if(answer.equals("y")){
//            player.getLocation().printCardPartRooms(player.getRank());
//            System.out.println("Type in one word from the name , if it is a sentance , type in one word as well , and should be spicify, not is , a ..etc");
//            String chosen = input.next();
//            player.setOnCardJob(player.getLocation().getChosenCardPartRooms(chosen));
//            System.out.println("now you are working at " + player.getOnCardJob());
//            player.setWork(true);
//            player.setOnCard(true);
 //       }
    }
    // a players turn
    public void takeTurn(int currentPlayer) {
        //endGameSummery();
        if(checkForGameEnd()){
            endGameSummery();
        }
            if (currentPlayer+1>playerList.size()){
                currentPlayer=0;
            }
//        while (!gameEnd) {

             //   problem here, never wait till the button is pushed
                var player = playerList.get(currentPlayer);
        if (player.getLocation().equals(board.getOffice())) {

            this.view.informationBoard.setText("Do you want to level up");
            this.view.button.setText("YES");
            this.view.button.setVisible(true);
            int finalCurrentPlayer6 = currentPlayer;
            this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    LevelUp(player, finalCurrentPlayer6);
                }
            });

            this.view.button1.setText("NO");
            this.view.button1.setVisible(true);
            int finalCurrentPlayer = currentPlayer;
            this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    choseLocation(player, finalCurrentPlayer);
                }
            });
            this.view.button2.setVisible(false);
            this.view.button3.setVisible(false);
            this.view.button4.setVisible(false);
                        }
//System.out.println("running in take turn");
               else if (player.getWork() == true) {
                    //System.out.println("running in take turn,getWork");
                    work(currentPlayer); // passing the current player
                }
              else if (player.getWork() == false) {
            choseLocation(player,currentPlayer);

        }

////            gameEnd = checkForGameEnd();

        }
    //for player to chose location
    public void choseLocation(Player player, int currentPlayer){
    System.out.println("function: choseLocation");

            this.view.informationBoard.setText("Player : "+player.getName()+"\n Where  do you want to move?");
            ArrayList<Room> list = player.getLocation().getAdjacentRooms();
            disableAllButton();


            for (int k = 0; k < list.size(); k++) {
                System.out.println(list.get(k));

                if (k == 0) {
                    this.view.button.setText(list.get(0).toString());
                    this.view.button.setVisible(true);
                    int finalCurrentPlayer = currentPlayer;
                    this.view.button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setPlayerRoom(player, 0, finalCurrentPlayer);
                        }
                    });
                } else if (k == 1) {
                    this.view.button1.setText(list.get(1).toString());
                    this.view.button1.setVisible(true);
                    int finalCurrentPlayer1 = currentPlayer;
                    this.view.button1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setPlayerRoom(player, 1, finalCurrentPlayer1);

                        }
                    });
                } else if (k == 2) {
                    this.view.button2.setText(list.get(2).toString());
                    this.view.button2.setVisible(true);
                    int finalCurrentPlayer2 = currentPlayer;
                    this.view.button2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setPlayerRoom(player, 2, finalCurrentPlayer2);
                        }
                    });

                } else if (k == 3) {
                    this.view.button3.setText(list.get(3).toString());
                    this.view.button3.setVisible(true);
                    int finalCurrentPlayer3 = currentPlayer;
                    this.view.button3.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setPlayerRoom(player, 3, finalCurrentPlayer3);
                        }
                    });
                } else if (k == 4) {
                    this.view.button4.setText(list.get(4).toString());
                    this.view.button4.setVisible(true);
                    int finalCurrentPlayer4 = currentPlayer;
                    this.view.button4.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setPlayerRoom(player, 4, finalCurrentPlayer4);
                        }
                    });

                }
            }
            return;
        }
    // set player to the chosen room
    private void setPlayerRoom(Player player,int roomNumber, int currentPlayer){
//        System.out.println("function: setPlayerRoom");
//        var listofroom = player.getLocation().getAdjacentRoom().size();
//        for(int i = 0 ;i<listofroom;i++){
//            System.out.println(player.getLocation().getAdjacentRoom().get(i).toString());
//        }
        //because trailer is not an option for player to re-enter, but it is on the list of option,
        //so whenever trailer appear on the list, which will be at the first item on the list.
        //just +1 will skip the
        if(player.getLocation().getAdjacentRoom().get(0).toString().equals("trailer")) {
        roomNumber = roomNumber+1;
//            System.out.println("inside if function");

        }
            Room nextRoom = player.getLocation().getAdjacentRoom().get(roomNumber);
        System.out.println(nextRoom);
            player.move(nextRoom);
            player.setLocation(nextRoom);

        for (Room room: this.list)
        {
            if(room.toString()==nextRoom.toString()){
                room.setCardFace(true);
//                room.getTakers().get(0).getView().setVisible(false);
            }
        }
            setRoomCardDisplay();
            DisplayAllPlayer();
            disableAllButton();
        workOrNotWork(player,currentPlayer);

        }
    // the bonus point function
    private void getOnCardCredit(int playerRank, int budget,Player player,int currentPlayer) {
        var diceRolls = new ArrayList<Integer>();
        int payment = 0 ;
        // roll dice per budget
        for (int i = 0; i < budget; i++)
        {
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 6);
            this.view.informationBoard.setText( "Dice " + i +" : "+ diceRoll);
            diceRolls.add(diceRoll);
        }

        Collections.sort(diceRolls);
        Collections.reverse(diceRolls);

        if (playerRank > 0 && playerRank < 3)
        {
            this.view.informationBoard.setText( "You got " +diceRolls.get(2)+" Dollar as your payment" );
            payment= diceRolls.get(2);
        }
        else if (playerRank > 2 && playerRank < 5)
        {if (diceRolls.size()>4){
            this.view.informationBoard.setText( "You got " +diceRolls.get(1)+  "+ "+ diceRolls.get(4)+" Dollar as your payment" );
            payment = diceRolls.get(1) + diceRolls.get(4);}
            else { this.view.informationBoard.setText( "You got " + diceRolls.get(1)+" Dollar as your payment" );
            payment = diceRolls.get(1);
        }
        }
        else if (playerRank > 4 && playerRank <= 6) {
            if(diceRolls.size()>3){
                this.view.informationBoard.setText( "You got " +diceRolls.get(0)+  "+ "+ diceRolls.get(3)+" Dollar as your payment" );
                payment = diceRolls.get(0) + diceRolls.get(3);}
        else {
                this.view.informationBoard.setText( "You got " +diceRolls.get(0)+ " Dollar as your payment" );
                payment= diceRolls.get(0) + diceRolls.get(3);}
        }
        player.addDollar(payment);
        this.view.button.setVisible(true);
        this.view.button.setText("continue");
        this.view.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                takeTurn(currentPlayer);
            }
        });

    }
    //disable all the button
    private void disableAllButton(){
        this.view.button.setVisible(false);
        this.view.button1.setVisible(false);
        this.view.button2.setVisible(false);
        this.view.button3.setVisible(false);
        this.view.button4.setVisible(false);
    }
    //set room card display
    public void setRoomCardDisplay(){

         int x = 0 ;
         int y = 0 ;
         String dfurl = "default";
        for (Room room: this.list)
        {
            if(room.getCardFace() && !room.getName().equals("office") && !room.getName().equals("trailer") ){
                x = room.getX();
                y= room.getY();
                //this should be moves to getCardFace = true
               String url = Integer.toString(room.getCardNumber());
                this.view.card(x,y,205,115,url);
            }else if (room.getCardFace()== false && !room.getName().equals("office") && !room.getName().equals("trailer")){
                x = room.getX();
                y= room.getY();
                this.view.card(x,y,205,115,dfurl);


            }

        }
    }
    // check open rooms
    private boolean checkForGameEnd()
    {
        int openRooms = 0;
        for (var room : this.board.getRoomList())
        {
            if (!room.getIsClosed())
                openRooms++;
        }
        return openRooms <= 1;
    }
    //print end gme summery
    private void endGameSummery() {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i< playerList.size();i++){
            Player player = playerList.get(i);
            list.add(setFinalResultString(player.getName(),player.getRank(),player.getDollars(),player.getCredit()));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The summary");
        alert.setHeaderText("Who is the winner ? ");
        alert.setContentText(Arrays.toString(list.toArray()));

        alert.showAndWait();
    }
}