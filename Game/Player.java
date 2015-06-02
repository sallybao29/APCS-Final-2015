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
    private int maxPower;
    private int stepcount;

    public Player(TileMap t){
	super("PlayerF_" , t);
	setHP(200);
	setX(160);
	setY(160);
	setSpeed(2);

	attacking = false;
	maxPower = 200;
	power = maxPower;
        
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

    public int getPower(){
	return power;
    }

    public void setPower(int p){
	power = p;
    }

    public int getMaxP(){
	return maxPower;
    }

    public void move(){
	setX(getX() + getDX() * getSpeed());
	setY(getY() + getDY() * getSpeed());
    }

    public void die(){
	//dying animation
    }

    public void attack(){
	if (attacking){
	    char direction = getDirection();
	    Projectile p = new Projectile("English_", direction);

	    if (power > p.getCost()){
		power -= p.getCost();

		p.setX(getX());
		p.setY(getY());

		projectiles.add(p);
	    }
	}
    }

    public void update(){
	int tmpx = getX();
	int tmpy = getY();

	super.update();

	int x = getX();
	int y = getY();

	//if player has moved a certain number of steps, 
	//increase power
	if (x != tmpx || y != tmpy){
	    stepcount++;

      	    if (stepcount % 5 == 0 && power < maxPower)
		power++;
	}
    }


}
