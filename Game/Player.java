import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;


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
    private BufferedImage[] idle;

    private Animation animation;

    public Player(){
	hp = 100;
	x = 40;
	y = 60;

	animation = new Animation();

	walkingLeft = new BufferedImage[4];  
	walkingRight = new BufferedImage[4];  
	walkingUp = new BufferedImage[4];  
	walkingDown = new BufferedImage[4]; 
	idle = new BufferedImage[1]; 

	loadFrames(walkingLeft, "../Sprites/Player/Red_L"); 
	loadFrames(walkingRight, "../Sprites/Player/Red_R"); 
	loadFrames(walkingUp, "../Sprites/Player/Red_U"); 
	loadFrames(walkingDown, "../Sprites/Player/Red_D"); 

	try {
	    idle[0] = ImageIO.read(new File("../Sprites/Player/Red_L0.png"));
	}
	catch (Exception e){}

    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public Animation getAnimation(){
	return animation;
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
    }
 
    public void update(){
	move();

	//player animation
	if (dx > 0){
	    animation.setFrames(walkingRight);
	    animation.setDelay(100);
	}
	if (dx < 0){
	    animation.setFrames(walkingLeft);
	    animation.setDelay(100);
	}
	if (dy > 0){
	    animation.setFrames(walkingDown);
	    animation.setDelay(100);
	}
        if (dy < 0){
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

    //move or attack input
    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	switch(key){
	case KeyEvent.VK_LEFT:
	    dx = -1;
	    break;
	case KeyEvent.VK_RIGHT:
	    dx = 1;
	    break;
	case KeyEvent.VK_UP:
	    dy = -1;
	    break;
	case KeyEvent.VK_DOWN:
	    dy = 1;
	    break;
	}
    }

    //stop moving or stop attacking
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();
	switch(key){
	case KeyEvent.VK_LEFT:
	    dx = 0;
	    break;
	case KeyEvent.VK_RIGHT:
	    dx  = 0;
	    break;
	case KeyEvent.VK_UP:
	    dy = 0;
	    break;
	case KeyEvent.VK_DOWN:
	    dy = 0;
	    break;
	}
    }
    /*
    public void draw(Graphics2D g){
	g.drawImage(animation.getImage(), x, y, null);
    }
    */

    public static void main(String[] args){
	Player p = new Player();
	System.out.println(Arrays.toString(p.walkingUp));
    }


}
