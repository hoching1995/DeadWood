import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SubSceneLabel extends Label {
    private final String FONT_PATH = "src/sample/pngs/files/font.ttf";

    public SubSceneLabel(String text,int x , int y ) throws IOException {
        setLayoutX(x);
        setLayoutY(y);
        setPadding(new Insets(0,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();

    }
    private  void setLabelFont() throws IOException {
        try {
            setFont(javafx.scene.text.Font.loadFont(new FileInputStream(new File(FONT_PATH)), 35));
        }catch (FileNotFoundException e){
            setFont(javafx.scene.text.Font.font("Verdana",23));
        }

    }
}
