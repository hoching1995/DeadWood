import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class CardDisplayManger extends Button {
    public CardDisplayManger (){
        setupUml();
    }
    private HashMap<String,String> url = new HashMap<String, String>() ;



    public void setupUml(){
        this.url.put("default","sample/pngs/files/card/default.jpg");
        this.url.put("1","sample/pngs/files/card/01.png");
        this.url.put("2","sample/pngs/files/card/02.png");
        this.url.put("3","sample/pngs/files/card/03.png");
        this.url.put("4","sample/pngs/files/card/04.png");
        this.url.put("5","sample/pngs/files/card/05.png");
        this.url.put("6","sample/pngs/files/card/06.png");
        this.url.put("7","sample/pngs/files/card/07.png");
        this.url.put("8","sample/pngs/files/card/08.png");
        this.url.put("9","sample/pngs/files/card/09.png");
        this.url.put("10","sample/pngs/files/card/10.png");
        this.url.put("11","sample/pngs/files/card/11.png");
        this.url.put("12","sample/pngs/files/card/12.png");
        this.url.put("13","sample/pngs/files/card/13.png");
        this.url.put("14","sample/pngs/files/card/14.png");
        this.url.put("15","sample/pngs/files/card/15.png");
        this.url.put("16","sample/pngs/files/card/16.png");
        this.url.put("17","sample/pngs/files/card/17.png");
        this.url.put("18","sample/pngs/files/card/18.png");
        this.url.put("19","sample/pngs/files/card/19.png");
        this.url.put("20","sample/pngs/files/card/20.png");
        this.url.put("21","sample/pngs/files/card/21.png");
        this.url.put("22","sample/pngs/files/card/22.png");
        this.url.put("23","sample/pngs/files/card/23.png");
        this.url.put("24","sample/pngs/files/card/24.png");
        this.url.put("25","sample/pngs/files/card/25.png");
        this.url.put("26","sample/pngs/files/card/26.png");
        this.url.put("27","sample/pngs/files/card/27.png");
        this.url.put("28","sample/pngs/files/card/28.png");
        this.url.put("29","sample/pngs/files/card/29.png");
        this.url.put("30","sample/pngs/files/card/30.png");
        this.url.put("31","sample/pngs/files/card/31.png");
        this.url.put("32","sample/pngs/files/card/32.png");
        this.url.put("33","sample/pngs/files/card/33.png");
        this.url.put("34","sample/pngs/files/card/34.png");
        this.url.put("35","sample/pngs/files/card/35.png");
        this.url.put("36","sample/pngs/files/card/36.png");
        this.url.put("37","sample/pngs/files/card/37.png");
        this.url.put("38","sample/pngs/files/card/38.png");
        this.url.put("39","sample/pngs/files/card/39.png");




    }

    public String getString(String input){
        return url.get(input);

    }


}
