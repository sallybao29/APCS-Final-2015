import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.Rectangle;

public class Projectile{
    private String type;
    private String direction;

    private int damage;
    private int cost;
    private int speed;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    private BufferedImage image;
    private Rectangle bounds;

    public Projectile(String t, String d){
	direction = d;
	String file = "../Sprites/Projectiles/";
	type = t;

	switch (t){
	case "English":
	    file += "English_";
	    speed = 3;
	    break;
	case "History":
	    speed = 1;
	    break;
	case "Physics":
	    break;
	case "Precalc":
	    break;
	}
	file += direction.substring(0, 1) + ".png";

	image = null;

	try { image = ImageIO.read(new File(file));}
	catch (Exception e){
	    System.out.println("Something went wrong.");
	}

	width = image.getWidth();
	height = image.getHeight();

	bounds = new Rectangle(x, y, width, height);

	switch (direction){
	case "Up":
	    dy = -1 * speed;
	    break;
	case "Down":
	    dy = 1 * speed;
	    break;
	case "Right":
	    dx = 1 * speed;
	    break;
	case "Left":
	    dx = -1 * speed;
	    break;
	}

    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getDamage(){
	return damage;
    }

    public int getCost(){
	return cost;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return height;
    }

    public Rectangle getBounds(){
	return bounds;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public void adjustRect(){
	bounds.setLocation(x, y);
    }

    public void move(){
	x += dx;
	y += dy;
	adjustRect();
    }

    public void draw(Graphics2D g){
	g.drawImage(image, x, y, null);
    }

  
}
