import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Monster extends Character{
    private int damage;

    public Monster(TileMap t){
	super("Frog_", t);
	setHP(100);
	setX(300);
	setY(300);
	damage = 12;

	setDirection('U');
    }

    public Monster(String s, int level, int x, int y, TileMap t){
	super(s, t);
	int tmp = (10-level)*10 + 100;
	setHP(tmp);
        setX(x);
	setY(y);
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
	AStar a = new AStar(file);
	a.move(this, p);
    }
   
    public void attack(){
	System.out.println("You've been caught!");
    }

    public void die(){
	//to be cont.
    }

}
