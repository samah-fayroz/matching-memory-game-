package org.example.start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.time.LocalTime;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class settings extends JPanel implements ActionListener, MouseListener {
	
    Image background;
    Timer timer;
    boolean flip =false, check= false;
    int [] selected=new int[2];
    int count=0, index;

    cards[] cards;
    int cardSize=28;
    
    
    
    
    
    //the images of the card
    String [] playing={"2","3","4","5","6","7","8","9","10","44","55","A","m","Q"};

    public settings(){
        try {
            background= ImageIO.read(new File("C:\\Users\\Dell\\Desktop\\tamam 2\\assignment\\images\\background.jpg"));
        }
        catch (IOException ex){
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, (String) null,ex);
        }

cards= new cards[cardSize];
        List<String> Shuffled_list= Arrays.asList(playing);
        Collections.shuffle(Shuffled_list);

        for (int i=0;i<cardSize;i++){
            cards[i]=new cards(playing[i%14],i);

        }

        addMouseListener(this);
        timer= new Timer(50,this);
       timer.start();
    }

//the part of the image of the background
    protected void paintComponent(Graphics G){
        super.paintComponent(G);
        G.setColor(Color.black);
        G.drawImage(background,0,0,game.width,game.height,null);
        for (int i=0;i<cardSize;i++){
           G.drawImage(cards[i].getPicture(), cards[i].getX(), cards[i].getY(), cards[i].getWidth(), cards[i].getHeight(), null);
        }
        
        
       
    }
    //the part of rotate a card to see its image
    int direction=1;
    @Override
    public void actionPerformed(ActionEvent e) {
 if(flip){

flip= cards[index].flip();
if (cards[index].getWidth()<=0)
    cards[index].setPicture(cards[index].getName());
 }
if (check) {
    if (cards[selected[0]].getName() != cards[selected[1]].getName()){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, (String) null,ex);
        }
        cards[selected[0]].setPicture("Cap");
        cards[selected[1]].setPicture("Cap");
    }
check=false;
}

    repaint();

if (count==2 && flip== false){
    check=true;
    count=0;
}
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
//the part of when you click on the mouse on the card you will see its image and compare it with another image
    @Override
    public void mousePressed(MouseEvent e) {
        flip = true;
        direction=1;
        for (cards cards1 : cards){
            if (cards1.collision(e.getX(), e.getY())){
                selected[count]= cards1.getIndex();
                if (count==0 || (count==1 && selected[0] != selected[1])) {
                    index = cards1.getIndex();
                    count++;
                }
            }
        }

    }
    
   

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
