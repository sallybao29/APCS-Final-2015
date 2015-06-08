import java.io.*;
import java.util.Scanner;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.*;


public class Floor{

    private int x;
    private int y;

    //ascent
    private int ax;
    private int ay;

    //descent
    private int dx;
    private int dy;

    private TileMap[][] areas;

    private boolean visited;
    private boolean reset;
    private String id;

    private BufferedImage floor;

    public Floor(int level){

	visited = false;
	reset = false;

	switch(level){
	case 10:
	    TileMap hall1 = new TileMap("Hall_1", level);
	    TileMap hall2 = new TileMap("Hall_2", level);
	    TileMap hall3 = new TileMap("Hall_3", level);
	    TileMap hall4 = new TileMap("Hall_4", level);
	    TileMap hall5 = new TileMap("Hall_5", level);

	    id = "Art";
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    break;
	case 9:
	    id = "Chemistry";
	    break;
	case 8:
	    id = "Physics";
	    break;
	case 7:
	    id = "Biology";
	    break;
	case 6:
	    id = "English";

	    break;
	case 5:
	    id = "Language";
	    break;
	case 4:
	    id = "Math";
	    break;
	case 3:
	    id = "History";
	    break;
	case 2:
	    break;
	}

	String path = "../Tileset/Tiles/" + id + ".png";

	floor = null;
	try {
	    floor = ImageIO.read(new File(path));
	}
	catch (Exception e){}
    }



    public TileMap getCurrent(){
	return areas[y][x];
    }

    public BufferedImage getFloor(){
	return floor;
    }

    public void setVisited(boolean b){
	visited = b;
    }

    public boolean wasVisited(){
	return visited;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void ascend(){
	x = ax;
	y = ay;
    }

    public void descend(){
	x = dx;
	y = dy;
    }

    public String getID(){
	return id;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

}

