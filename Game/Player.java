import java.io.*;
import java.awt.image.BufferedImage;
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

    private BufferedImage image;

    public Player(){
	hp = 100;
	x = 40;
	y = 60;
	image = null;
	try {
	    image = ImageIO.read(new File("../Sprites/Ghost_Sprites1.png"));
	}
	catch (Exception e){}
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public BufferedImage getImage(){
	return image;
    }

    public void move(){
	x += dx;
	y += dy;
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

    public static void main(String[] args){
	Player p = new Player();
	System.out.println(p.getImage().getHeight());
    }
}
