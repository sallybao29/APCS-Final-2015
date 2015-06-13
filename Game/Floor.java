import java.io.*;
import java.util.Scanner;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Floor{
    private int x;
    private int y;

    //coordinates of next TileMap if
    //coming from floor below
    private int ax;
    private int ay;

    //coordinates of next TileMap if
    //coming from floor above
    private int dx;
    private int dy;

    private int level;

    private TileMap[][] areas;

    private boolean visited;
    private boolean reset;
    private boolean locked;

    private String id;

    private BufferedImage floor;

    TileMap hall1, hall2, hall3, hall4, hall5, hall6,
	library1, library2, chem, lunchroom, cs, art;


    /*----------------------------- Constructor ------------------------------*/

    public Floor(int l){
	//visited = false;
	//reset = false;
	locked = true;

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

    //initialize all TileMaps
    //make the layout of each floor
    public void makeFloor(){

	hall1 = new TileMap("Hall_1", level);
	hall2 = new TileMap("Hall_2", level);
	hall3 = new TileMap("Hall_3", level);
	hall4 = new TileMap("Hall_4", level);
	hall5 = new TileMap("Hall_5", level);
	hall6 = new TileMap("Hall_6", level);
	lunchroom = new TileMap("Cafeteria", level);
	library1 = new TileMap("Library_1", level);
	library2 = new TileMap("Library_2", level);
	chem = new TileMap("Chem_Class", level);
	cs = new TileMap("CS_Class", level);
	art = new TileMap("Art_Class", level);

	//blocks off hall2 on all floors
	//except 6, because library is there
	if (level != 6){
	    MapObject plant1 = new MapObject("Plant_4", 0, 136);
	    MapObject plant2 = new MapObject("Plant_4", 0, 168);
	    MapObject plant3 =  new MapObject("Plant_4", 0, 200);

	    hall2.addToBounds(plant1);
	    hall2.addToBounds(plant2);
	    hall2.addToBounds(plant3);

	    hall2.getProps().add(plant1);
	    hall2.getProps().add(plant2);
	    hall2.getProps().add(plant3);
	}

	switch(level){
	case 10:
	    makeArt();
	    break;
	case 9:
	    makeChem();
	    break;
	case 8:
	    makePhys();
	    break;
	case 7:
	    makeBio();
	    break;
	case 6:
	    makeEng();
	    break;
	case 5:
	    makeLang();
	    break;
	case 4:
	    makeMath();
	    break;
	case 3:
	    makeHist();
	    break;
	case 2:
	    makeFinal();
	    break;
	}
    }

 
    public void makeArt(){
	id = "Art";
	ax = 0;
	ay = 3;
	hall4.add(new MapObject("Door_open", 216, 34));
	areas = new TileMap[][]{{null, art, null},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeChem(){
	id = "Chemistry";
	dx = 2;
	dy = 1;
	areas = new TileMap[][]{{chem, null, null},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makePhys(){
	id = "Physics";
	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeBio(){
	id = "Biology";
	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeEng(){
	id = "English";
	ax = 1;
	ay = 2;
	dx = 3;
	dy = 0;
	areas = new TileMap[][]{{null, hall3, hall4, hall5},
				{library1, hall2, null, null},
				{library2, hall1, null, null}};
    }

    public void makeLang(){
	id = "Language";
	ax = 0;
	ay = 3;
	dx = 2;
	dy = 1;
	areas = new TileMap[][]{{lunchroom, null, null},
				{hall6, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeMath(){
	id = "Math";
	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeHist(){
	id = "History";
	areas = new TileMap[][]{{cs, null, null},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }


    public void makeFinal(){
	id = "";
	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }


    /*--------------------------------- Getters and Setters -------------------------------*/

    public TileMap getCurrent(){
	return areas[y][x];
    }

    //
    public void genKey(){
	int randx = (int)(Math.random() * areas[0].length);
	int randy = (int)(Math.random() * areas.length);

	TileMap t = areas[randy][randx];

	while (t == null){
	    randx = (int)(Math.random() * areas[0].length);
	    randy = (int)(Math.random() * areas.length);

	    t = areas[randy][randx];
	}
	t.addKey();
    }

    public BufferedImage getFloor(){
	return floor;
    }

    public boolean locked(){
	return locked;
    }


    public String getID(){
	return id;
    }

    /*
    public void setVisited(boolean b){
	visited = b;
    }

    public boolean wasVisited(){
	return visited;
    }
    */

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

    public void unlock(){
	locked = false;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    /*
    public int getLevel(){
	return level;
    }
    */

}

