import java.util.ArrayList;

public class Part {
    private String name ;
    private int Level ;
    private int x ;
    private int y ;
    private int h ;
    private int w ;
    private String line ;
public Part(String name , int level){
    this.name = name;
    this.Level = level;
    this.x = 0;
    this.y = 0 ;
    this.h = 0 ;
    this.w = 0;
}
    // getting the part of the room
public Part(String name, int level, int x, int y, int w, int h, String Line) {
    this.name = name;
    this.Level = level;
    this.x = x;
    this.y = y ;
    this.h = h ;
    this.w = w;
    this.line = Line ;
}
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }
    public int getH(){
        return this.h;
    }

public String getName(){
    return this.name;
}

public String toString(){
    return  name + "\n" +"Level:"+Level ;
}


    }



