import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;


public class Player extends Character{

    public Player(TileMap t){
	super("../Sprites/Player/Red_", t);
	setHP(100);
	setX(40);
	setY(60);
        
	setDirection("up");
    }

    public void move(){
	setX(getX() + getDX());
	setY(getY() + getDY());
    }

    public void die(){
	//dying animation
    }

    public void attack(){
	//two+ forms of attack
    }


 

}
