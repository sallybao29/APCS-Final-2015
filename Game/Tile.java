import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;


public class Tile extends MapObject{

    private boolean blocked;

    public Tile(String s, boolean b){
	super(s, ' ');
	blocked = b;
    }

    public void loadImage(){
	setPath("../Tileset/Tiles/");
	super.loadImage();
    }

  
    public boolean isBlocked(){
	return blocked;
    }

    public void setBlocked(boolean b){
	blocked = b;
    }

}
