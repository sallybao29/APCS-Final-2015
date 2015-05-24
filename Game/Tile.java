import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;


public class Tile{

    //x and y coordinates of top-left corner
    private int x;
    private int y;

    private BufferedImage image;
    private boolean blocked;
    private Rectangle bounds;

    private final int height = 32;
    private final int width = 32;

    public Tile(BufferedImage b, boolean p){
	image = b;
        blocked = p;
	bounds = new Rectangle(x, y, width, height);
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public boolean isBlocked(){
	return blocked;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return width;
    }

    public void setXY(int x, int y){
	this.x = x;
	this.y = y;
    }

    public void draw(Graphics2D g){
	g.drawImage(image, x, y, null);
    }


}
