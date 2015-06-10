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

    private int quantity;

    private BufferedImage image;
    private Rectangle bounds;
    //part of stairs/doors/escalators that allows
    //transfer to next point
    private Rectangle validArea;


    /*-------------------------------------- Constructor -----------------------------------------*/

    public MapObject(String s, char d){
	id = s;
	direction = d;
	quantity = 1;
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

    /*----------------------------------- Initialization --------------------------------------*/

    public void loadImage(){
	if (!id.contains("None")){
	    String s = path + id;
	    if (direction != ' ')
		s += direction;
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
	int bx = x;
	int by = y;
	int bh = height;
	int bw = width;

	int vx = bx;
	int vy = by;
	int vh = 31;
	int vw = 31;

	switch (id){
	case "Stairs_D":
	    bx += 32;
	    bw = 62;
	    vx = bx;
	    vy += 16;
	    break;
	case "Stairs_U":
	    vx += 32;
	    vy += 51;
	    bw = 62;
	    break;
	case "Seat_1": case "Seat_2": case "Seat_3":
	case "Chair_R": case "Chair_L":
	    bw = 0;
	    bh = 0;
	    break;
	case "Door_open": 
	    vx += 8;
	    vy += 30;
	    break;
	case "Door_2":
	    vx += 8;
	    vy += 30;
	    vw = 63;
	    break;
	case "Escalator":
	    vy += 64;
	    vw = 63;
	    vh = 63;
	    break;
	case "Exit_H":
	    bw = 0;
	    bh = 0;
	    vy += 32;
	    vw = 47;
	    vh = 15;
	    break;
	}
 
	bounds = new Rectangle(bx, by, bw, bh);
	validArea = new Rectangle(vx, vy, vw, vh);
    }


 /*-------------------------------------- Getters and Setters -----------------------------------------*/
	    
    public BufferedImage getImage(){
	return image;
    }

    public int getQuantity(){
	return quantity;
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

    public void changeQuantity(int i){
	quantity += i;
	if (quantity < 0)
	    quantity = 0;
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

    public Rectangle getValid(){
	return validArea;
    }

    public void adjustRect(){
	bounds.setLocation(x ,y);
    }

    public void draw(Graphics2D g){
	if (!id.contains("None"))
	    g.drawImage(image, x, y, null);
    }
	    
 

}
