import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuButtonController extends Button {
    private final String FONT_PATH = "src/sample/pngs/files/font.ttf";

    private final String BUTTON_FREE_STYLE = "-fx-background-image: url('/sample/pngs/files/button.png');";

    public MainMenuButtonController(String text) throws FileNotFoundException {
        super(text);
        setFONT_PATH();

        setFont(font);
        setStyle( BUTTON_FREE_STYLE );

        setPrefSize(190,50);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { // setting for mouse entire the pic
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() { //setting for mouse exit the pic
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });

    }

    private Font font;
    private void setFONT_PATH() {
        try {
            font = Font.loadFont(new FileInputStream(FONT_PATH), 19);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

