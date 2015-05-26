import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;


public class Player extends Character{
    private LinkedList<Projectile> projectiles;
    private boolean attacking;
    private int power;

    public Player(TileMap t){
	super("../Sprites/Player/PlayerF_" , t);
	setHP(100);
	setX(160);
	setY(160);
	attacking = false;
	power = 100;
        
	setDirection("Up");
	projectiles = new LinkedList<Projectile>();
    }

    //keep track of all projectiles and their positions
    public LinkedList<Projectile> getProjectiles(){
	return projectiles;
    }

    public void attacking(boolean b){
	attacking = b;
    }

    public int getPower(){
	return power;
    }

    public void setPower(int np){
	power = np;
    }

    public void move(){
	setX(getX() + getDX());
	setY(getY() + getDY());
    }

    public void die(){
	//dying animation
    }

    public void attack(){
	if  (attacking){
	    String direction = getDirection();
	    Projectile p = new Projectile("English", direction);

	    power = power - p.getCost();
	    if (power > 0){

		p.setX(getX());
		p.setY(getY());

		projectiles.add(p);
	    }
	}

    }

 

}
