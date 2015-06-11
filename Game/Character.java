import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.io.File;


public abstract class Character extends MapObject{

    private int hp;
    public int maxHP;

    private int dx;
    private int dy;
    private int speed;

    private boolean bottomRight;
    private boolean bottomLeft;
    private boolean topLeft;
    private boolean topRight;

    private BufferedImage[] walkingLeft;
    private BufferedImage[] walkingRight;
    private BufferedImage[] walkingUp;
    private BufferedImage[] walkingDown;
    
    private Animation animation;
    private TileMap map;


    /*-------------------------------- Constructor ------------------------------------*/ 

    public Character(String s, TileMap t){
	super(s, 'U');

	map = t;       
    }  
    /*------------------------------ Initialization ----------------------------------*/ 

    public void loadImage(){
	walkingLeft = new BufferedImage[4];  
	walkingRight = new BufferedImage[4];  
	walkingUp = new BufferedImage[4];  
	walkingDown = new BufferedImage[4]; 

	String path = getPath();
	String id = getID();

	loadFrames(walkingLeft, path + id + "L"); 
	loadFrames(walkingRight, path + id + "R"); 
	loadFrames(walkingUp, path + id + "U"); 
	loadFrames(walkingDown, path + id + "D"); 

	animation = new Animation();
	animation.setFrames(walkingUp);
	animation.setDelay(-1);
	setImage(walkingUp[0]);

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
	    // e.printStackTrace();
	}
    }

    /*----------------------------- Getters and Setters ---------------------------------*/ 

    public int getHP(){
	return hp;
    }

    public int getMaxHP(){
	return maxHP;
    }

    public int getSpeed(){
	return speed;
    }

    public int getDX(){
	return dx;
    }

    public int getDY(){
	return dy;
    }

    public boolean topLeft(){
	return topLeft;
    }

    public boolean topRight(){
	return topRight;
    }

    public boolean bottomLeft(){
	return bottomLeft;
    }

    public boolean bottomRight(){
	return bottomRight;
    }

    public Animation getAnimation(){
	return animation;
    }

    public void setHP(int hp){
	this.hp = hp;

	if (hp > maxHP)
	    hp = maxHP;
	if (hp < 0)
	    hp = 0;
    }

    public void setDY(int dy){
	this.dy = dy;
    }

    public void setDX(int dx){
	this.dx = dx;
    }

    public void setMaxHP(int i){
	maxHP = i;
    }

    public void setSpeed(int s){
	speed = s;
    }

    public void setMap(TileMap m){
	map = m;
    }


    /*--------------------------- Abstract Methods --------------------------------*/ 

    abstract void move();
 
    abstract void die();

    abstract void attack();


    /*------------------------------ Updating --------------------------------*/ 

    public void update(){
	move(); 
	checkBounds();
	attack(); 

	//character animation
	char direction = getDirection();
	switch(direction){
	case 'R':
	    animation.setFrames(walkingRight);
	    break;
	case 'L':
	    animation.setFrames(walkingLeft);
	    break;
	case 'D':
	    animation.setFrames(walkingDown);
	    break;
	case 'U':
	    animation.setFrames(walkingUp);
	    break;
	}		 
	if (dx == 0 && dy == 0) 
	    animation.setDelay(-1);
	else 
	    animation.setDelay(100);
	animation.update();
	adjustRect();
    }


    //Keep character in bounds and walls
     public void checkBounds(){
	 int x = getX();
	 int y = getY();

	 findCorners(x, y);

	 if (dx == -1){
	     if (topLeft || bottomLeft)
		 setX((x / 32 + 1) * 32);
	 }
	 if (dx == 1){
	     if (topRight || bottomRight)
		 setX((x / 32) * 32);
	 }
	 if (dy == -1){
	     if (topLeft || topRight)
		 setY((y / 32 + 1) * 32);
	 }
	 if (dy == 1){
	     if (bottomRight || bottomLeft)
		 setY((y / 32) * 32);
	 }
     }


    //checks if four corners of character lie on blocked tiles
    public void findCorners(int x, int y){
	topRight = false;
	topLeft = false;
	bottomRight = false;
	bottomLeft = false;

	int tmpx = x / 32;
	int tmpy = y / 32;

	if (x % 32 != 0 && map.getTile(tmpx, tmpy).isBlocked())
	    topLeft = true;

	tmpx = (x + getWidth()) / 32;

	if (x % 32 != 0 && map.getTile(tmpx, tmpy).isBlocked())
	    topRight = true;

	tmpx = x / 32;
	tmpy = (y + getHeight()) / 32;

	if (y % 32 != 0 && map.getTile(tmpx, tmpy).isBlocked())
	    bottomLeft = true;

	tmpx = (x + getWidth()) / 32;

	if ( x % 32 != 0 && y % 32 != 0 && 
	     map.getTile(tmpx, tmpy).isBlocked())
	    bottomRight = true;
    }
  


    public void draw(Graphics2D g){
	g.drawImage(animation.getImage(), getX(), getY(), null);
    }

}
