import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.nio.file.Paths;
import java.io.IOException;

public class DeadWood extends Application {
    private AnchorPane  root;
    private GameSubscene sceneToHide;
    private GameSubscene playSubScene;
    private GameSubscene scoresSubScene;
    private GameSubscene instructionSubScene;
    private GameSubscene developerSubScene;

    MediaPlayer BGM;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // all the button in the gui
    public void start(Stage primaryStage) throws IOException {
        int x = 50;
        root = new AnchorPane(); // must have

        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        //backGroundMusic();
        background();


        MainMenuButtonController play = new MainMenuButtonController("P L A Y");
        play.setTranslateX(x);
        play.setTranslateY(200);
        root.getChildren().add(play);
            createStartGameSubScene();
        play.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showSubScene(playSubScene);
                }
        });

        MainMenuButtonController scores = new MainMenuButtonController("S C O R E S");
        scores.setTranslateX(x);
        scores.setTranslateY(280);
        root.getChildren().add(scores);
        scoresSubScene = new GameSubscene();
        root.getChildren().add(scoresSubScene);
        createScoresSubScene();
        scores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoresSubScene);
            }
        });

        MainMenuButtonController help = new MainMenuButtonController("I N S T R U C T I O N");
        help.setTranslateX(x);
        help.setTranslateY(360);
        root.getChildren().add(help);
        createInstructionSubScene();
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(instructionSubScene);
            }
        });

        MainMenuButtonController developers = new MainMenuButtonController("D E V E L O P E R");
        developers.setTranslateX(x);
        developers.setTranslateY(440);
        root.getChildren().add(developers);
        createDeveloperSubScene();
        developers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                showSubScene(developerSubScene);
            }
        });

        MainMenuButtonController exit = new MainMenuButtonController("E X I T");
        exit.setTranslateX(x);
        exit.setTranslateY(520);
        root.getChildren().add(exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

    }
//create instruction sub scene
    private void createInstructionSubScene() throws IOException {
        instructionSubScene = new GameSubscene();
        root.getChildren().add(instructionSubScene);
        SubSceneLabel InstructionCharacterLabel = new SubSceneLabel("I N S T R U C T I O N",110,60); // title

        SubSceneText InstructionText = new SubSceneText("            Welcome to DeadWooD Studios \n" +
                "Simply check play to entry the dream of act\n"+
                "Each round you can chose to act or to make a living \n"+
                "of course working in the scenes will make more money \n" +
                "when every scene is finished, as the round \n"+
                "Player with highest rank and money will with the game\n"+
                "for full instruction please click the link below", 10,70,18); // for text in SubScene


        instructionSubScene.getPane().getChildren().add( InstructionCharacterLabel);
        instructionSubScene.getPane().getChildren().add(InstructionText);
        MainMenuButtonController instructionLink = new MainMenuButtonController("Instruction Link ");

        instructionLink.setTranslateX(180);
        instructionLink.setTranslateY(280);
        instructionSubScene.getPane().getChildren().add(instructionLink);
        instructionLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://cheapass.com//wp-content/uploads/2016/07/Deadwood-Free-Edition-Rules.pdf");
            }
        });
    }

    private void createScoresSubScene() throws IOException {
        scoresSubScene = new GameSubscene();
        root.getChildren().add(scoresSubScene);
        SubSceneLabel InstructionCharacterLabel = new SubSceneLabel("S C O R E S",150,60); // title

        SubSceneText InstructionText = new SubSceneText(
                "      |    Player  |   Score   |   Level   |   Date    |\n "+
                "      ------------------------------------------------\n", 10,70,18); // for text in SubScene


        scoresSubScene.getPane().getChildren().add( InstructionCharacterLabel);
        scoresSubScene.getPane().getChildren().add(InstructionText);
        MainMenuButtonController instructionLink = new MainMenuButtonController("Instruction Link ");

        instructionLink.setTranslateX(180);
        instructionLink.setTranslateY(280);
        scoresSubScene.getPane().getChildren().add(instructionLink);
        instructionLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://cheapass.com//wp-content/uploads/2016/07/Deadwood-Free-Edition-Rules.pdf");
            }
        });
    }
//create history sub scene
    private void createDeveloperSubScene() throws IOException {
        developerSubScene = new GameSubscene();
        root.getChildren().add(developerSubScene);
        SubSceneLabel historyCharacterLabel = new SubSceneLabel("HO CHING LAM (GABRIEL) ",50,60);
        developerSubScene.getPane().getChildren().add( historyCharacterLabel);

        SubSceneText developerText = new SubSceneText("This project was created the first online quarter \n" +
                "(WWU CSCI 345 OOD )due to covid-19, and my  \n"+
                "schedule was fully packed. So,I decided to develop \n"+
                "the entire project alone. Such as mvc pattern,  \n"+
                "GUI, and the object oriented design. Learning javafx \n" +
                " and finishing the entire project in one and half \n"+
                "month was challenging, and I love what i have \n"+
                "accomplish,if you are interested more of my work,  \n"+
                " click the button below to my E-commerce website", 10,70,18); // for text in SubScene
        developerSubScene.getPane().getChildren().add( developerText);

        MainMenuButtonController Ecommerce= new MainMenuButtonController("E-commerce");

        Ecommerce.setTranslateX(180);
        Ecommerce.setTranslateY(320);
        developerSubScene.getPane().getChildren().add(Ecommerce);
        Ecommerce.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://hoching1995.github.io/Maktub/index.html");
            }
        });

    }
//create startGame sub scene
    private void createStartGameSubScene() throws IOException {
        playSubScene = new GameSubscene();
        root.getChildren().add(playSubScene); // 8.22
        SubSceneLabel choseCharacterLabel = new SubSceneLabel("Are you ready ",140,120);
        playSubScene.getPane().getChildren().add(choseCharacterLabel);
        //playSubScene.getPane().getChildren().add(crateCharacterChose());

        MainMenuButtonController start = new MainMenuButtonController("S T A R T ");
        start.setTranslateX(180);
        start.setTranslateY(180);
        playSubScene.getPane().getChildren().add(start);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int days = 0;
              GameController game = new GameController();
              days =  game.showViewAndGetDays();
                  game.startGame();
                  game.DisplayAllPlayer();
                  game.takeTurn(0);

            }
        });
    }
    public void background(){
        Image backgroundImage = new Image("sample/pngs/files/backgroundImage.png",1100,700,false,true);
        BackgroundImage backgroundImageSet = new BackgroundImage(backgroundImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND, BackgroundPosition.CENTER,null);
        root.setBackground(new Background(backgroundImageSet));
    }
    private void showSubScene (GameSubscene subScene ){ // moving Scene in and out.
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;

    }
    public void backGroundMusic(){

        String music = "BGM.mp3"; // the file must locate at the root , so don't change the file location.
        Media h = new Media(Paths.get(music).toUri().toString());
        BGM = new MediaPlayer(h);
        BGM.play();
    }
}