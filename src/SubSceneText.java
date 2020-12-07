import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SubSceneText extends Label{
    private final String FONT_PATH = "src/sample/pngs/files/Zombie_Holocaust.ttf";

    public SubSceneText(String text,int x, int y, int frontSize ) throws IOException {

       setLayoutX(x);
       setLayoutY(y);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont(frontSize);

    }
    private  void setLabelFont(int frontSize) throws IOException {
        try {
            setFont(javafx.scene.text.Font.loadFont(new FileInputStream(new File(FONT_PATH)), frontSize));
        }catch (FileNotFoundException e){
            setFont(javafx.scene.text.Font.font("Verdana",frontSize));
        }

    }
}
