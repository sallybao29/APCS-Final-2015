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

    private boolean locked;
    private boolean keyMade;

    private String id;

    private BufferedImage floor;

    TileMap hall1, hall2, hall3, hall4, hall5, hall6,
	hall7, hall8, library1, library2, chem, lunchroom, 
	cs, gym, bio, office;


    /*----------------------------- Constructor ------------------------------*/

    public Floor(int l){
	locked = true;
	keyMade = false;

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
	ax = 0;
	ay = 3;
	dx = 2;
	dy = 1;

	hall1 = new TileMap("Hall_1", level);
	hall2 = new TileMap("Hall_2", level);
	hall3 = new TileMap("Hall_3", level);
	hall4 = new TileMap("Hall_4", level);
	hall5 = new TileMap("Hall_5", level);
	hall6 = new TileMap("Hall_6", level);
	hall7 = new TileMap("Hall_7", level);
	hall8 = new TileMap("Hall_8", level);
	lunchroom = new TileMap("Cafeteria", level);
	library1 = new TileMap("Library_1", level);
	library2 = new TileMap("Library_2", level);
	chem = new TileMap("Chem_Class", level);
	cs = new TileMap("CS_Class", level);
	gym = new TileMap("Gym", level);
	office = new TileMap("Office", level);
	bio = new TileMap("Bio_Class", level);

	//blocks off hall2 on all floors
	//except 6, because library is there
	if (level != 6){
	    hall2.add(new MapObject("Plant_4", 0, 136));
	    hall2.add(new MapObject("Plant_4", 0, 168));
	    hall2.add(new MapObject("Plant_4", 0, 200));
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
	ay = 2;
	MapObject ob = hall5.getProps().find("Escalator");
	hall5.getProps().remove(ob);

	hall3.add(new MapObject("Door_close", 56, 34));
	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeChem(){
	id = "Chemistry";
	hall3.add(new MapObject("Door_open", 56, 34));

	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{chem, null, null},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makePhys(){
	id = "Physics";
	ay = 2;
	dy = 0;
	hall3.add(new MapObject("Door_close", 56, 34));
	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeBio(){
	id = "Biology";

	hall3.add(new MapObject("Door_close", 56, 34));
	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_open", 312, 34));

	areas = new TileMap[][]{{null, null, bio},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeEng(){
	id = "English";
	ax = 1;
	dx = 3;
	ay = 2;
	dy = 0;

	hall3.add(new MapObject("Door_close", 56, 34));

	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{null, hall3, hall4, hall5},
				{library1, hall2, null, null},
				{library2, hall1, null, null}};
    }

    public void makeLang(){
	id = "Language";

	hall5.add(new MapObject("Door_close", 312, 34));

	hall4.add(new MapObject("Door_close", 216, 34));

	areas = new TileMap[][]{{lunchroom, null, null},
				{hall6, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeMath(){
	id = "Math";
	dy = 0;
	ay = 2;

	hall3.add(new MapObject("Door_close", 56, 34));
	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, null, null}};
    }

    public void makeHist(){
	id = "History";

	hall3.add(new MapObject("Door_open", 56, 34));
	hall1.add(new MapObject("Exit_V", 480, 120));

	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));

	areas = new TileMap[][]{{gym, null, null},
				{hall3, hall4, hall5},
				{hall2, null, null},
				{hall1, cs, null}};
    }

    public void makeFinal(){
	id = "";
	dy = 2;
	hall4.add(new MapObject("Door_close", 216, 34));
	hall5.add(new MapObject("Door_close", 312, 34));
	areas = new TileMap[][]{{office, null, null},
				{hall8, null, null},
				{hall7, hall4, hall5}};
		        
    }


    /*--------------------------------- Getters and Setters -------------------------------*/

    public TileMap getCurrent(){
	return areas[y][x];
    }

    public boolean cleared(){
	for (int row = 0; row < areas.length; row ++){
	    for (int col = 0; col < areas[row].length; col++){
		TileMap t = areas[row][col];
		if (t != null && !t.empty()){
		    return false;
		}		
	    }
	}
	return true;
    }

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

    public boolean keyMade(){
	return keyMade;
    }

    public String getID(){
	return id;
    }

    public int getX(){
	return x;
    }

    public void setKeyMade(boolean b){
	keyMade = b;
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

}

