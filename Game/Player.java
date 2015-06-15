import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;


public class Player extends Character{
    private LinkedList<Projectile> projectiles;
    private Inventory inventory;
    private boolean attacking;
    private int power;
    private int maxPower;
    private int stepcount;


  /*----------------------------- Constructor + Initialization ---------------------------------*/ 

    public Player(TileMap t){
	super("PlayerF_" , t);
	setHP(200);
	setMaxHP(200);
	setX(256);
	setY(256);
	setSpeed(2);

	attacking = false;
	maxPower = 200;
	power = maxPower;
        
	setDirection('U');
	projectiles = new LinkedList<Projectile>();
	inventory = new Inventory();
    }

    public void loadImage(){
	setPath("../Sprites/Player/");
	super.loadImage();
    }

    /*----------------------------- Getters and Setters ---------------------------------*/ 

    //keep track of all projectiles and their positions
    public LinkedList<Projectile> getProjectiles(){
	return projectiles;
    }

    public void setProjectiles(LinkedList<Projectile> pro){
	projectiles = pro;
    }

    public Inventory getInventory(){
	return inventory;
    }

    public void attacking(boolean b){
	attacking = b;
    }

    public int getPower(){
	return power;
    }

    public boolean hasKey(){
	return inventory.get(6).getQuantity() > 0;
    }

    public void setPower(int p){
	power = p;
	if (power > maxPower)
	    power = maxPower;
	if (power < 0)
	    power = 0;
    }


    public int getMaxP(){
	return maxPower;
    }

    public void use(int i){
	int start, end;

	MapObject ob = inventory.get(i);
	start = ob.getQuantity();
	ob.changeQuantity(-1);
	end = ob.getQuantity();
	if (start != end){
	    if (i == 4)
		setHP(getHP() + 10);
	    if (i == 5){
		setHP(getHP() + 5);
		setPower(power + 10);
	    }
	}
	    
    }


    /*---------------------------------- Updating ------------------------------------*/ 
    public void move(){
	setX(getX() + getDX() * getSpeed());
	setY(getY() + getDY() * getSpeed());
    }

    public void die(){
	//dying animation
    }
    //create a new projectile and add to list
    public void attack(){
	if (attacking){

	    char direction = getDirection();
	    String s = inventory.getCurrent();
	    Projectile p = new Projectile(s, direction);

	    //if player has enough power
	    //set projectile's x and y
	    //and add to list
	    if (power > p.getCost()){
		power -= p.getCost();

		p.setX(getX());
		p.setY(getY());

		projectiles.add(p);
	    }
	}
    }
  
    public void update(){
	int tmpx = getX();
	int tmpy = getY();

	super.update();

	int x = getX();
	int y = getY();

	//if player has moved a certain number of steps, 
	//increase power
	if (x != tmpx || y != tmpy){
	    stepcount++;

      	    if (stepcount % 3 == 0 && power < maxPower)
		power++;
	}
    }


}
