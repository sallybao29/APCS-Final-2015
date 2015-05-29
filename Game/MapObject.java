import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.io.File;


public class MapObject{
    private String path = "../Tileset/Object/";
    private String id;
    private char direction;

    private int x;
    private int y;
    private int width; 
    private int height;

    private BufferedImage image;
    private Rectangle bounds;

    public MapObject(String s, char d){
	id = s;
	direction = d;

	loadImage();

	width = image.getWidth();
	height = image.getHeight();

	bounds = new Rectangle(x, y, width, height);
        
    }

    public void loadImage(){
	String s = path + id;
	if (direction != ' '){
	    s += direction;
	}
	s +=  ".png";

	image = null;

	try{
	    image = ImageIO.read(new File(s));
	}

	catch (Exception e){
	    System.out.println("Something went wrong.");
	}
    }
	    
    public BufferedImage getImage(){
	return image;
    }

    public String getID(){
	return id;
    }

    public char getDirection(){
	return direction;
    }

    public String getPath(){
	return path;
    }
 
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return width;
    }

    public void setImage(BufferedImage i){
	image = i;
    }

    public void setDirection(char d){
	direction = d;
    }

    public void setPath(String s){
	path = s;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public Rectangle getBounds(){
	return bounds;
    }

    public void adjustRect(){
	bounds.setLocation(x ,y);
    }

    public void draw(Graphics2D g){
	g.drawImage(image, x, y, null);
    }
 

}
