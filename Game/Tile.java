import java.awt.image.*;

public class Tile{

    private BufferedImage image;
    private int height;
    private int width;
    private boolean passable;

    public Tile(BufferedImage b, int w, int h, boolean p){
	image = b;
	width = w;
	height = h;
	passable = p;
    }

    public BufferedImage getImage(){
	return image;
    }

}
