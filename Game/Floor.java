import java.io.*;
import java.util.Scanner;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.*;

/*
public class Floor{

    private int x;
    private int y;
    private TileMap[][] areas;

    private boolean visited;
    private boolean reset;
    private String id;

    private BufferedImage floor;

    public Floor(int level){

	visited = false;
	reset = false;

	TileMap hall1, hall2, hall3, hall4, hall5, 
	    classroom1, classroom2, classroom3;

	switch(level){
	case 10:
	    id = "Art";
	    areas = {{classroom1, classroom2, null},
		     {hall3, hall4, hall5},
		     {hall2, null, null},
		     {hall1, classroom3, null}};
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

	String path = "../Tileset/" + id + ".png";

	floor = null;
	try {
	    floor = ImageIO.read(new File(path));
	}
	catch (Exception e){}
    }



    public TileMap getCurrent(){
	return areas[y][x];
    }

    public void setVisited(boolean b){
	visited = b;
    }

    public boolean wasVisited(){
	return visited;
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
*/
