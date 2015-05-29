import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;


public class Player extends Character{
    private LinkedList<Projectile> projectiles;
    private boolean attacking;

    public Player(TileMap t){
	super("PlayerF_" , t);
	setHP(100);
	setX(160);
	setY(160);
	setPower(100);
	attacking = false;
        
	setDirection('U');
	projectiles = new LinkedList<Projectile>();
    }

    public void loadImage(){
	setPath("../Sprites/Player/");
	super.loadImage();
    }

    //keep track of all projectiles and their positions
    public LinkedList<Projectile> getProjectiles(){
	return projectiles;
    }

    public void attacking(boolean b){
	attacking = b;
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
	    char direction = getDirection();
	    Projectile p = new Projectile("English_", direction);

	    if (getPower() > p.getCost()){
		setPower(getPower() - p.getCost());

		p.setX(getX());
		p.setY(getY());

		projectiles.add(p);
	    }
	}

    }

 

}
