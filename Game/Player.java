import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;


public class Player implements Character{

    private int hp;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width, height;

    private BufferedImage[] walkingLeft;
    private BufferedImage[] walkingRight;
    private BufferedImage[] walkingUp;
    private BufferedImage[] walkingDown;
    
    private Animation animation;

    private String direction;

    public Player(){
	hp = 100;
	x = 40;
	y = 60;
        
        direction = "up";

	animation = new Animation();

	walkingLeft = new BufferedImage[4];  
	walkingRight = new BufferedImage[4];  
	walkingUp = new BufferedImage[4];  
	walkingDown = new BufferedImage[4]; 

	loadFrames(walkingLeft, "../Sprites/Player/Red_L"); 
	loadFrames(walkingRight, "../Sprites/Player/Red_R"); 
	loadFrames(walkingUp, "../Sprites/Player/Red_U"); 
	loadFrames(walkingDown, "../Sprites/Player/Red_D"); 

    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
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
	if (x < 0) x = 0;
	if (x > 400) x = 400;
	if (y < 0) y = 0;
	if (y > 400) y = 400;
    }
    

    public void move(){
	x += dx;
	y += dy;

	checkBounds();

	//player animation
	switch(direction){
	case "right":
	    animation.setFrames(walkingRight);
	    break;
	case "left":
	    animation.setFrames(walkingLeft);
	    break;
	case "down":
	    animation.setFrames(walkingDown);
	    break;
	case "up":
	    animation.setFrames(walkingUp);
	    break;
	}

	if (dx == 0 && dy == 0) 
	    animation.setDelay(-1);
	else 
	    animation.setDelay(100);

	animation.update();

    }
 

    public void die(){
	//dying animation
    }

    public void attack(){
	//two+ forms of attack
    }

    public void draw(Graphics2D g){
	move();
	g.drawImage(animation.getImage(), x, y, null);
    }
 


}
