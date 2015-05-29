import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.io.File;


public abstract class Character extends MapObject{

    private int hp;
    private int dx;
    private int dy;
    private int power;

    private BufferedImage[] walkingLeft;
    private BufferedImage[] walkingRight;
    private BufferedImage[] walkingUp;
    private BufferedImage[] walkingDown;
    
    private Animation animation;
    private TileMap map;

    public Character(String s, TileMap t){
	super(s, 'U');

	map = t;       
    }
    

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
 
    public int getHP(){
	return hp;
    }

    public int getDX(){
	return dx;
    }

    public int getDY(){
	return dy;
    }

    public int getPower(){
	return power;
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

    public void setPower(int p){
	power = p;
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
	 int x = getX();
	 int y = getY();

	 int tx = x / 32;
	 int ty = y / 32;
	 Tile t;

	 if (dy == -1 || dx == -1){
	     t = map.getTile(tx, ty);
	     if (t.isBlocked()){
		 if (dy == -1)
		     setY(ty * 32 + 32);     
		 else 
		     setX(tx * 32 + 32); 
	     }
	 }
	 if (dy == 1){
	     ty = y / 32 + 1;
	     t = map.getTile(tx, ty);
	     if (t.isBlocked())
		 setY(ty * 32 - 32);
	 }
	 if (dx == 1){
	     tx = x / 32 + 1;
	     t = map.getTile(tx, ty);
	     if (t.isBlocked())
		 setX(tx * 32 - 32);
	 }
     }

 
    public void update(){
	int tmpx = getX();
	int tmpy = getY();

	move(); 
	checkBounds();

	int x = getX();
	int y = getY();

	if (x != tmpx || y != tmpy){
	    if (power < 100){
		power++;
	    }
	}

	attack(); 

	//player animation
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

    public void draw(Graphics2D g){
	g.drawImage(animation.getImage(), getX(), getY(), null);
    }

    abstract void move();
 
    abstract void die();

    abstract void attack();

 

}
