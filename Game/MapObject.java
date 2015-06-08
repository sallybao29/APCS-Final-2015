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

    //purpose varies by item
    private int change;

    private BufferedImage image;
    private Rectangle bounds;


    /*-------------------------------------- Constructor -----------------------------------------*/

    public MapObject(String s, char d){
	id = s;
	direction = d;

	image = null;
	loadImage();

	if (image == null){
	    width = 32;
	    height = 32;
	}
	else {
	    width = image.getWidth();
	    height = image.getHeight();
	}

	bounds = new Rectangle(x, y, width, height);
        
    }

    public MapObject(String s, int x, int y){
	this(s, ' ');
	this.x = x;
	this.y = y;
        calculateBounds();

        
    }

    public void loadImage(){
	if (!id.contains("None")){
	    String s = path + id;
	    if (direction != ' '){
		s += direction;
	    }
	    s +=  ".png";

	    try{
		image = ImageIO.read(new File(s));
	    }

	    catch (IOException e){
		System.out.println("Error");
		System.out.println(s);
	    }
	}
    }

    public void calculateBounds(){
	int rx = x;
	int ry = y;
	int rh = height;
	int rw = width;

	switch (id){
	case "Stairs_D":
	    rx += 32;
	    rw = 62;
	    rh = 71;
	    break;
	case "Stairs_U":
	    rw = 63;
	    rh = 103;
	    break;
	case "Seat_1": case "Seat_2": case "Seat_3":
	    rw = 0;
	    rh = 0;
	    break;
	case "Door_open":
	    rx += 10;
	    ry += 12;
	    rw = 28;
	    rh = 49;
	    break;
	}

	bounds = new Rectangle(rx, ry, rw, rh);
    }


 /*-------------------------------------- Getters and Setters -----------------------------------------*/
	    
    public BufferedImage getImage(){
	return image;
    }

    public int getChange(){
	return change;
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

    public void setChange(int i){
	change = i;
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
	if (!id.contains("None"))
	    g.drawImage(image, x, y, null);
    }
	    
 

}
