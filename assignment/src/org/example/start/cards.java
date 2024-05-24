package org.example.start;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cards {
    private int x;
    private int y;
    private int width;
    private int height;

    private String name;

    private int index;

    private Image pic;



public cards(String name, int i){
    this.width=150;
   this.height=200;
   this.name=name;
   this.index=i;
    setPicture("Cap");


    setX(50+i*175);

    if(i>=8 && i<16){
        setX(50+(i-8)*175);
        setY(250);

    }
    if(i>=16 && i<24){
        setX(50+(i-16)*175);
        setY(2*250);

    }
    if(i>=24){
        setX(50+(i-24)*175);
        setY(3*250);

    }


}
int direction=1;
public boolean flip(){
    width-=30*direction;
    x+=15*direction;

    if(width<=0)
        direction*=-1;
    if (direction==-1 && width==150){
        direction=1;
        return false;
    }
    return true;
}

public boolean collision(int x_cursor, int y_cursor){
Rectangle cardReact = new Rectangle(x, y, width, height);
return cardReact.contains(x_cursor, y_cursor);
}

public void setPicture(String name){
    try {

        pic=ImageIO.read(new File("C:\\Users\\Dell\\Desktop\\tamam 2\\assignment\\images\\" + name + ".jpg"));

    }catch (IOException ex){
        Logger.getLogger(cards.class.getName()).log(Level.SEVERE, (String) null,ex);
    }

}



public Image getPicture(){
    return pic;
}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getIndex() {
        return index;
    }

}


