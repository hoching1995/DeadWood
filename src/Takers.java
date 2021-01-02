import javafx.scene.control.Button;

public class Takers {



    private int number;
    private int x;
    private int y;
    private int h;
    private int w;
    private Button view;
 public  Takers (int number , int x, int y, int h , int w ){
     this.number = number;
     this.x = x ;
     this.y = y ;
     this.h = h ;
     this.w = w ;
 }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public Button getView() {
        return view;
    }

    public void setView(Button view) {
        this.view = view;
    }
}
