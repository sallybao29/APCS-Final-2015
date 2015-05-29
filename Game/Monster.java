import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Monster extends Character{
    private int damage;
    private int cycle;

    public Monster(TileMap t){
	super("Frog_", t);
	setHP(100);
	setX(300);
	setY(300);
	damage = 12;

	setDirection('U');
    }

    public Monster(String s, int level, TileMap t){
	super(s, t);
	int tmp = (10-level)*10 + 100;
	setHP(tmp);
    }

    public void loadImage(){
	setPath("../Sprites/Monster/");
	super.loadImage();
    }

    public int getDamage(){
	return damage;
    }

    public void move(){
    }

    public void move(String file, Player p){
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
   
    public void attack(){
	System.out.println("You've been caught!");
    }

    public void die(){
	//to be cont.
    }

}
