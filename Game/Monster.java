import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Monster extends Character{
    private String name;
    private int damage;

    public Monster(TileMap t){
	super("../Sprites/Monster/Frog_", t);
	setHP(100);
	setX(300);
	setY(300);
	damage = 12;

	setDirection("Up");
    }

    public Monster(String n, int level, int x, int y, TileMap t){
	super("../Sprites/Monster/"+n+"_", t);
	name = n;
	int tmp = (10-level)*10 + 100;
	setHP(tmp);
        setX(x);
	setY(y);
    }

    public int getDamage(){
	return damage;
    }

    public void move(){
    }

    public void move(String file, Player p){
	AStar a = new AStar(file);
	a.move(this, p);
    }
   
    public void attack(){
	System.out.println("You've been caught!");
    }

    public void die(){
	//to be cont.
    }

    public String getName(){
	return name;
    }
}
