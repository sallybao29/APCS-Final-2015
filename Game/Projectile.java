import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Projectile{
    private String type;
    private String direction;

    private int damage;
    private int cost;
    private int x;
    private int y;
    private int dx;
    private int dy;

    private BufferedImage image;
    private Rectangle bounds;

    public Projectile(String t, String d){
	direction = d;
	String file = "../Sprites/Projectiles/";
	type = t;

	switch (t){
	case "English":
	    file += "English_";
	    break;
	case "History Textbook":
	    break;
	case "Regents Physics Textbook":
	    break;
	case "Trigonometry Textbook":
	    break;
	}
	file += direction.substring(0, 1).toUpperCase();

	image = null;

	try {
	    image = ImageIO.read(new File(file));
	}
	catch (Exception e){}

	bounds = new Rectangle(x, y, image.getWidth(), image.getHeight());

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

    public Rectangle getBounds(){
	return bounds;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public void update(){
	x += dx;
	y += dy;
    }

    public void draw(Graphics2D g){
	g.drawImage(image, x, y, null);
    }
}
