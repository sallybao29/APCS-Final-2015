import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import java.awt.Rectangle;

public class Monster extends Character{
    private int damage;
    private int cycle;
    private int radius;
    private boolean idle;
    private boolean superMon;
    private TileMap tile;

    private String item;

    Player p = null;

    /*------------------------------------------ Constructor ----------------------------------------------*/
 

    public Monster(String s, int level, TileMap t){
	super(s, t);
	int tmp;
	if (s.equals("MiniMuk_")) // if monsters are miniMuks
	    tmp = 50;
	else
	    tmp = (10-level) * 10 + 100;
	setHP(tmp);
	setMaxHP(tmp);
	radius = ((10 - level) / 2) * 32;
	damage = (14 - level) / 2;
	tile = t;
	idle = true;

	createItem();
    }
   
    public Monster(String s, int level, TileMap t, String special){
	this(s,level,t);
	if (special.equals("superMon")){
	    superMon = true;
	    setMaxHP(250);
	    setHP(250);
	    //setItem("Phone");
	}
    }

    /*---------------------------------------- Initialization --------------------------------------------*/

    public void loadImage(){
	setPath("../Sprites/Monster/");
	super.loadImage();
    }

    public void createItem(){	
	Random rn = new Random();
	int randitem = rn.nextInt(2);
	int randchance = rn.nextInt(10);

	if (randchance % 2 == 0){
	    if (randitem == 0)
		item = "Bagel";

	    else if (randitem == 1)
		item = "Coffee";
	}
	else 
	    item = "None";
    }

    /*--------------------------------------- Getters and Setters ---------------------------------------*/

    public int getDamage(){
	return damage;
    }

    public String getItem(){
	return item;
    }

    public int getRadius(){
	return radius;
    }

    public void setIdle(boolean b){
	idle = b;
    }

    public void setItem(String s){
	item = s;
    }

    public void resetP(Player pl){
	p = pl;
    }

    /*------------------------------------------ Updating ----------------------------------------------*/

    //if the monster is idle: it has full hp
    //and the player is not it its range
    //it will wander around
    public void wander(){

	//to slow down the movement and animation
	if (cycle % 20 == 0){

	    char[] dir = {'U', 'D', 'L', 'R'};

	    int delx = (int) (Math.random() * 3) - 1;
	    int dely = 0;

	    int r = (int) Math.random() * 20;

	    //if r is a multiple of five
	    //move the monster one step in random direction
	    if (r % 5 == 0){	
		if (delx == 0)
		    dely = (int) (Math.random() * 3) - 1;
	    }
	    //otherwise change direction
	    else {
		setDirection(dir[(int)(Math.random() * 3)]);
		delx = 0;
		dely = 0;
	    }
	    setDX(delx);
	    setDY(dely);
	}
	cycle++;
    }

    //if monster is not idle
    //it will chase after the player
    public void pursue(int x, int y){
	if (getX() < x){
	    setDX(1);
	    setDY(0);
	}
	else if (getX() > x){
	    setDX(-1);
	    setDY(0);
	}
	else if (getY() < y){
	    setDY(1);
	    setDX(0);
	}
	else if (getY() > y){
	    setDY(-1);
	    setDX(0);
	}
    }

    //checks if monster is outside of safespot
    public boolean outOfSafe(){
        return !getBounds().intersects(tile.getSafeSpot());
    }

    public void move(){
	if (!outOfSafe()){
	    setDY(-1);
	    setDirection('U');
	}
	if (idle)
	    wander();
	else 
	    pursue(p.getX(), p.getY());

	setX(getX() + getDX());
	setY(getY() + getDY());

	if (getDX() == -1)
	    setDirection('L');
	if (getDX() == 1)
	    setDirection('R');
	if (getDY() == -1)
	    setDirection('U');
	if (getDY() == 1)
	    setDirection('D');

	if (superMon){
	    replicate();
	}
    }

   
    public void attack(){
    }

    public void repel(){
	p.setDX(getDX());
	p.setDY(getDY());
	p.getAnimation().setDelay(-1);
	p.checkBounds();
    }

    public void update(){
	super.update();
	Animation ani = getAnimation();

	if (getDX() == 0 && getDY() == 0) 
	    ani.setDelay(-1);
	else if (idle)
	    ani.setDelay(500);
	else 
	    ani.setDelay(100);
	ani.update();
	adjustRect();
    }

    public void die(){
	//to be cont.
    }

    //~~~~~~~MUK~~~~~~~~~
    public void replicate(){
	if (getHP()>140 && getHP()<150){
	    tile.makeMonsters(2); // create miniMuks
	}
    }
  
}
