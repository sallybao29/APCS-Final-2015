import java.io.*;
import java.util.Scanner;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Floor{
    private int x;
    private int y;

    //coming from floor below
    private int ax;
    private int ay;

    //coming from floor above
    private int dx;
    private int dy;

    private int level;

    private TileMap[][] areas;

    private boolean visited;
    private boolean reset;
    private String id;

    private BufferedImage floor;


    /*----------------------------- Constructor ------------------------------*/

    public Floor(int l){
	visited = false;
	reset = false;

	level = l;

	makeFloor();

	String path = "../Tileset/Tiles/" + id + ".png";

	floor = null;
	try {
	    floor = ImageIO.read(new File(path));
	}
	catch (Exception e){}
    }

    /*------------------------------- Initialization -----------------------*/

    public void makeFloor(){
	TileMap hall1, hall2, hall3, hall4, hall5, hall6,
	    library1, library2, chem, lunchroom, cs;

	hall1 = new TileMap("Hall_1", level);
	hall2 = new TileMap("Hall_2", level);
	hall3 = new TileMap("Hall_3", level);
	hall4 = new TileMap("Hall_4", level);
	hall5 = new TileMap("Hall_5", level);
	hall6 = new TileMap("Hall_6", level);
	library1 = new TileMap("Library_1", level);
	library2 = new TileMap("Library_2", level);
	chem = new TileMap("Chem_Class", level);
	cs = new TileMap("CS_Class", level);
	lunchroom = new TileMap("Cafeteria", level);

	if (level != 5){
	    MapObject plant1 = new MapObject("Plant_4", 0, 128);
	    MapObject plant2 = new MapObject("Plant_4", 0, 160);
	    MapObject plant3 =  new MapObject("Plant_4", 0, 192);

	    hall2.foo(plant1);
	    hall2.foo(plant2);
	    hall2.foo(plant3);

	    hall2.getProps().add(plant1);
	    hall2.getProps().add(plant2);
	    hall2.getProps().add(plant3);
	}

	switch(level){
	case 10:
	    id = "Art";
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    ax = 0;
	    ay = 2;
	    break;
	case 9:
	    id = "Chemistry";
	    areas = new TileMap[][]{{chem, null, null},
				    {hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    dx = 2;
	    dy = 1;
	    break;
	case 8:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "Physics";
	    break;
	case 7:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "Biology";
	    break;
	case 6:
	    id = "English";
	    areas = new TileMap[][]{{null, hall3, hall4, hall5},
				    {library1, hall2, null, null},
				    {library2, hall1, null, null}};
	    ax = 1;
	    ay = 2;
	    dx = 3;
	    dy = 0;
	    break;
	case 5:
	    areas = new TileMap[][]{{lunchroom, null, null},
				    {hall6, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "Language";
	    ax = 0;
	    ay = 3;
	    dx = 2;
	    dy = 1;
	    break;
	case 4:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "Math";
	    break;
	case 3:
	    areas = new TileMap[][]{{cs, null, null},
				    {hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "History";
	    break;
	case 2:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    id = "Yolo";
	    break;
	}
    }

 
    /*-------------------------- Getters and Setters ---------------------*/

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

    public int getLevel(){
	return level;
    }

}

