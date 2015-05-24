import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;


public class Player extends Character{
    private LinkedList<Projectile> projectiles;

    public Player(TileMap t){
	super("../Sprites/Player/Red_", t);
	setHP(100);
	setPower(100);
	setX(40);
	setY(60);
        
	setDirection("up");
	projectiles = new LinkedList<Projectile>();
    }


    public LinkedList<Projectile> getProjectiles(){
	return projectiles;
    }

    public void move(){
	setX(getX() + getDX());
	setY(getY() + getDY());
    }

    public void die(){
	//dying animation
    }

    public void attack(){
	String direction = getDirection();
	Projectile p = new Projectile("English", direction);

	int px = 0;
	int py = 0;

	switch(direction){

	case "Up":
	    px = (int)((getX() + getWidth()) / 2 - (p.getWidth() / 2));
	    py = getY() + p.getHeight();
	    break;
	case "Down":
	    px = (int)((getX() + getWidth()) / 2 - (p.getWidth() / 2));
	    py = getY() + getHeight();
	    break;
	case "Right":
	    px = getX() + getWidth();
	    py = (int)((getY() + getHeight()) / 2  - (p.getHeight() / 2));
	    break;
	case "Left":
	    px = getX() + p.getWidth();
	    py = (int)((getY() + getHeight()) / 2  - (p.getHeight() / 2));
	    break;
	}

	p.setX(px);
	p.setY(py);

	projectiles.add(p);

    }


 

}
