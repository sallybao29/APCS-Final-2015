import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Monster extends Character{
    private int damage;
    private int cycle;
    private int radius;
    private boolean idle;
    Player p = null;

    /*------------------------------------------ Constructor ----------------------------------------------*/
 

    public Monster(String s, int level, TileMap t){
	super(s, t);
	int tmp = (10-level) * 10 + 100;
	setHP(tmp);
	setMaxHP(tmp);
	radius = 96;
	damage = 5;
	idle = true;
    }

    public void loadImage(){
	setPath("../Sprites/Monster/");
	super.loadImage();
    }

    /*--------------------------------------- Getters and Setters ---------------------------------------*/

    public int getDamage(){
	return damage;
    }

    public int getRadius(){
	return radius;
    }

    public void setIdle(boolean b){
	idle = b;
    }

    /*------------------------------------------ Updating ----------------------------------------------*/

    public void resetP(Player pl){
	p = pl;
    }

    public void wander(){

	if (cycle % 20 == 0){

	    char[] dir = {'U', 'D', 'L', 'R'};

	    int delx = (int) (Math.random() * 3) - 1;
	    int dely = 0;

	    int r = (int) Math.random() * 20;

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

    public void pursue(int x, int y){
	if (getX() < x){
	    setDX(1);
	    setDY(0);
	    setDirection('R');
	}
	else if (getX() > x){
	    setDX(-1);
	    setDY(0);
	    setDirection('L');
	}
	else if (getY() < y){
	    setDY(1);
	    setDX(0);
	    setDirection('D');
	}
	else if (getY() > y){
	    setDY(-1);
	    setDX(0);
	    setDirection('U');
	}
    }
   

    public void move(){
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
    }
  
    /*
    public void move(String file){
	AStar a = new AStar(file, this);
	a.move(p);
	if (cycle == 10){
	    a = new AStar(file, this);
	    a.move(p);
	    cycle = 0;
	}
	a.nextStep();
	cycle++;
    }
    */
   
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

}
