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


    /*----------------------------- Constructor ------------------------------*/
    public Floor(int level){

	visited = false;
	reset = false;

	makeFloor(level);

	String path = "../Tileset/Tiles/" + id + ".png";

	floor = null;
	try {
	    floor = ImageIO.read(new File(path));
	}
	catch (Exception e){}
    }

    /*------------------------------- Initialization -----------------------*/

    public void makeFloor(int level){
	TileMap hall1, hall2, hall3, hall4, hall5, hall6,
	    library1, library2, chem;

	hall1 = new TileMap("Hall_1");
	hall2 = new TileMap("Hall_2");
	hall3 = new TileMap("Hall_3");
	hall4 = new TileMap("Hall_4");
	hall5 = new TileMap("Hall_5");
	library1 = new TileMap("Library_1");
	library2 = new TileMap("Library_2");
	chem = new TileMap("Chem_Class");

	switch(level){
	case 10:
	    id = "Art";
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    ax = 0;
	    ay = 2;
	    break;
	case 9:
	    id = "Chemistry";
	    areas = new TileMap[][]{{chem, null, null},
				    {hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    break;
	case 8:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    id = "Physics";
	    break;
	case 7:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    id = "Biology";
	    break;
	case 6:
	    id = "English";
	    areas = new TileMap[][]{{null, hall3, hall4, hall5},
				    {library1, hall2, null, null},
				    {library2, hall1, null, null}};
	    setLevel(areas, level);
	    ax = 1;
	    ay = 2;
	    break;
	case 5:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    id = "Language";
	    break;
	case 4:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    id = "Math";
	    break;
	case 3:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    id = "History";
	    break;
	case 2:
	    areas = new TileMap[][]{{hall3, hall4, hall5},
				    {hall2, null, null},
				    {hall1, null, null}};
	    setLevel(areas, level);
	    break;
	}
    }

    public void setLevel(TileMap[][] t, int level){
	for (TileMap[] a: t){
	    for (TileMap b: a){
		b.setLevel(level);
	    }
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

}

