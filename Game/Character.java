import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.io.File;


public abstract class Character{

    private int hp;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width; 
    private int height;

    private BufferedImage[] walkingLeft;
    private BufferedImage[] walkingRight;
    private BufferedImage[] walkingUp;
    private BufferedImage[] walkingDown;
    
    private Animation animation;
    private BufferedImage currentImage;
    private TileMap map;
    private String direction;
    private Rectangle bounds;

    public Character(String path, TileMap t){
        
	bounds = new Rectangle();
	map = t;       
	animation = new Animation();

	walkingLeft = new BufferedImage[4];  
	walkingRight = new BufferedImage[4];  
	walkingUp = new BufferedImage[4];  
	walkingDown = new BufferedImage[4]; 

	loadFrames(walkingLeft, path + "L"); 
	loadFrames(walkingRight, path + "R"); 
	loadFrames(walkingUp, path + "U"); 
	loadFrames(walkingDown, path + "D"); 

	direction = "Up";
	animation.setFrames(walkingUp);
	animation.setDelay(-1);
	currentImage = walkingUp[0];
    }
 
    //move rectangle to sprite's new position
    //change size accordingly
    public void adjustRect(){
	bounds.setLocation(x, y);
	bounds.setSize(currentImage.getWidth(), currentImage.getHeight());
    }
 
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getHP(){
	return hp;
    }

    public int getDX(){
	return dx;
    }

    public int getDY(){
	return dy;
    }

    public int getHeight(){
	return currentImage.getHeight();
    }

    public int getWidth(){
	return currentImage.getWidth();
    }

    public String getDirection(){
	return direction;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public void setHP(int hp){
	this.hp = hp;
    }

    public void setDY(int dy){
	this.dy = dy;
    }

    public void setDX(int dx){
	this.dx = dx;
    }

    public void setDirection(String s){
	direction = s;
    }


    //read sprite images into array for animation
    private void loadFrames(BufferedImage[] frames, String path){
	String s = "";
	try{
	    for (int i = 0; i < frames.length; i++){
		s = path + i + ".png";
		frames[i] = ImageIO.read(new File(s));
	    }
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }

 
     public void checkBounds(){
	 int tx = x / 32;
	 int ty = y / 32;
	 Tile t;

	 if (dy == -1 || dx == -1){
	     t = map.getTile(tx, ty);
	     if (t.isBlocked()){
		 if (dy == -1)
		     y = ty * 32 + 32;     
		 else 
		     x = tx * 32 + 32; 
	     }
	 }
	 if (dy == 1){
	     ty = y / 32 + 1;
	     t = map.getTile(tx, ty);
	     if (t.isBlocked())
		 y = ty * 32 - 32;
	 }
	 if (dx == 1){
	     tx = x / 32 + 1;
	     t = map.getTile(tx, ty);
	     if (t.isBlocked())
		 x = tx * 32 - 32;
	 }
     }

 
    public void update(){
	move(); 
	checkBounds();
	attack(); 

	//player animation
	switch(direction){
	case "Right":
	    animation.setFrames(walkingRight);
	    break;
	case "Left":
	    animation.setFrames(walkingLeft);
	    break;
	case "Down":
	    animation.setFrames(walkingDown);
	    break;
	case "Up":
	    animation.setFrames(walkingUp);
	    break;
	}
		 
	if (dx == 0 && dy == 0) 
	    animation.setDelay(-1);
	else 
	    animation.setDelay(100);

	animation.update();
	currentImage = animation.getImage();
	adjustRect();
    }

    public Rectangle getBounds(){
	return bounds;
    }

    public void draw(Graphics2D g){
	g.drawImage(animation.getImage(), x, y, null);
    }

    abstract void move();
 
    abstract void die();

    abstract void attack();

 

}
