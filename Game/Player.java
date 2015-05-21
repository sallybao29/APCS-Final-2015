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
    
    private boolean idle;

    private Animation animation;

    public Player(){
	hp = 100;
	x = 40;
	y = 60;
        
        idle = true;

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

    public void setDY(int dy){
	this.dy = dy;
    }

    public void setDX(int dx){
	this.dx = dx;
    }

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

    public void move(){
	x += dx;
	y += dy;

	//player animation
	if (dx > 0){
	    animation.setFrames(walkingRight);
	    animation.setDelay(100);
	}
	else if (dx < 0){
	    animation.setFrames(walkingLeft);
	    animation.setDelay(100);
	}
	else if (dy > 0){
	    animation.setFrames(walkingDown);
	    animation.setDelay(100);
	}
        else if (dy < 0){
	    animation.setFrames(walkingUp);
	    animation.setDelay(100);
	}
	else {
	    animation.setFrames(idle);
	    animation.setDelay(-1);
	}
	animation.update();

    }
 

    public void die(){
	//dying animation
    }

    public void attack(){
	//two+ forms of attack
    }

    public void draw(Graphics2D g){
	g.drawImage(animation.getImage(), x, y, null);
    }
 

    public static void main(String[] args){
	Player p = new Player();
	System.out.println(Arrays.toString(p.walkingUp));
    }


}
