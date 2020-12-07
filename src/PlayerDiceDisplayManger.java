import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class PlayerDiceDisplayManger extends Button {
    public PlayerDiceDisplayManger(){
        setupUml();
    }
    private HashMap<String,String> url = new HashMap<String, String>() ;



public void setupUml(){
    this.url.put("Player00","sample/pngs/files/dice/b1.png");
    this.url.put("Player01","sample/pngs/files/dice/b2.png");
    this.url.put("Player02","sample/pngs/files/dice/b3.png");
    this.url.put("Player03","sample/pngs/files/dice/b4.png");
    this.url.put("Player04","sample/pngs/files/dice/b5.png");
    this.url.put("Player05","sample/pngs/files/dice/b6.png");
    this.url.put("Player10","sample/pngs/files/dice/c1.png");
    this.url.put("Player11","sample/pngs/files/dice/c2.png");
    this.url.put("Player12","sample/pngs/files/dice/c3.png");
    this.url.put("Player13","sample/pngs/files/dice/c4.png");
    this.url.put("Player14","sample/pngs/files/dice/c5.png");
    this.url.put("Player15","sample/pngs/files/dice/c6.png");
    this.url.put("Player20","sample/pngs/files/dice/o1.png");
    this.url.put("Player21","sample/pngs/files/dice/o2.png");
    this.url.put("Player22","sample/pngs/files/dice/o3.png");
    this.url.put("Player23","sample/pngs/files/dice/o4.png");
    this.url.put("Player24","sample/pngs/files/dice/o5.png");
    this.url.put("Player25","sample/pngs/files/dice/o6.png");
    this.url.put("Player30","sample/pngs/files/dice/p1.png");
    this.url.put("Player31","sample/pngs/files/dice/p2.png");
    this.url.put("Player32","sample/pngs/files/dice/p3.png");
    this.url.put("Player33","sample/pngs/files/dice/p4.png");
    this.url.put("Player34","sample/pngs/files/dice/p5.png");
    this.url.put("Player35","sample/pngs/files/dice/p6.png");
    this.url.put("Player40","sample/pngs/files/dice/r1.png");
    this.url.put("Player41","sample/pngs/files/dice/r2.png");
    this.url.put("Player42","sample/pngs/files/dice/r3.png");
    this.url.put("Player43","sample/pngs/files/dice/r4.png");
    this.url.put("Player44","sample/pngs/files/dice/r5.png");
    this.url.put("Player45","sample/pngs/files/dice/r6.png");
    this.url.put("Player50","sample/pngs/files/dice/v1.png");
    this.url.put("Player51","sample/pngs/files/dice/v2.png");
    this.url.put("Player52","sample/pngs/files/dice/v3.png");
    this.url.put("Player53","sample/pngs/files/dice/v4.png");
    this.url.put("Player54","sample/pngs/files/dice/v5.png");
    this.url.put("Player55","sample/pngs/files/dice/v6.png");
    this.url.put("Player60","sample/pngs/files/dice/w1.png");
    this.url.put("Player61","sample/pngs/files/dice/w2.png");
    this.url.put("Player62","sample/pngs/files/dice/w3.png");
    this.url.put("Player63","sample/pngs/files/dice/w4.png");
    this.url.put("Player64","sample/pngs/files/dice/w5.png");
    this.url.put("Player65","sample/pngs/files/dice/w6.png");
    this.url.put("Player70","sample/pngs/files/dice/y1.png");
    this.url.put("Player71","sample/pngs/files/dice/y2.png");
    this.url.put("Player72","sample/pngs/files/dice/y3.png");
    this.url.put("Player73","sample/pngs/files/dice/y4.png");
    this.url.put("Player74","sample/pngs/files/dice/y5.png");
    this.url.put("Player75","sample/pngs/files/dice/y6.png");


}

public String getString(String input){
    return url.get(input);

}


}
