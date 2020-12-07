import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class GameSubscene extends SubScene {
    private final static String Font = "sample/pngs/files/font.ttf";
    private final static String backGround = "sample/pngs/files/a2ccd6aa419df758cf08f683ac4a7392.png";
      private boolean isHidden;

    public GameSubscene() {

        super(new AnchorPane(),600, 400);
        prefHeight(400);
        prefWidth(600);
        BackgroundImage image = new BackgroundImage(new Image(backGround,600,400,false,true), BackgroundRepeat.REPEAT.NO_REPEAT,BackgroundRepeat.REPEAT.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground((new Background(image)));
        isHidden = true;
        setLayoutX(1100);
        setLayoutY(250);


    }
    public void moveSubScene( ){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden){
            transition.setToX(-650);
            isHidden=false;
        }else {
            transition.setToX(0);

            isHidden=true;
        }
        transition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

}
