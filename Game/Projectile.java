import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.Rectangle;

public class Projectile extends MapObject{

    private int damage;
    private int cost;
    private int speed;
    private int dx;
    private int dy;
 
    public Projectile(String id, char d){
	super(id, d);

	switch (id){
	case "English_":
	    speed = 3;
	    cost = 2;
	    break;
	case "History_":
	    speed = 1;
	    break;
	case "Physics_":
	    break;
	case "Precalc_":
	    break;
	}

	switch (getDirection()){
	case 'U':
	    dy = -1 * speed;
	    break;
	case 'D':
	    dy = speed;
	    break;
	case 'R':
	    dx = speed;
	    break;
	case 'L':
	    dx = -1 * speed;
	    break;
	}

    }

    public void loadImage(){
	setPath("../Sprites/Projectiles/");
	super.loadImage();
    }

    public int getDamage(){
	return damage;
    }

    public int getCost(){
	return cost;
    }

    public void move(){
	setX(getX() + dx);
	setY(getY() + dy);
	adjustRect();
    }
  
}
