import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class TileMap{

    private final int width = 16;
    private final int height = 16;

    private Tile[][] tiles;
    private String file;
    private String id;

    private int level;

    private LinkedList<Monster> monsters;
    private SuperList props;
    private Rectangle safeSpot;

    private String[][] names;

    private Monster muk;

    /*----------------------------------------- Constructor ----------------------------------------*/
 
    public TileMap(String type, int l){

	id = type;
	level = l;

	props = new SuperList(id);
	safeSpot = new Rectangle(96,416,384,64);

        tiles = new Tile[height][width];

	if (type.contains("Class"))
	    file = "../Maps/Class.txt";
	else 
	    file = "../Maps/" + type + ".txt";

	initNames();
	loadTiles();
	makeMonsters(l);
	addProps();
    }

    /*--------------------------------------- Initialization --------------------------------------*/


    public void addProps(){
	for (int i = 0; i < props.size(); i++){
	    addToBounds(props.get(i));
	}
    }

    public void initNames(){
	names = new String[][]{{"Klefki_"},
			       {"Ghost_", "MiniMuk_"},
			       {"Ekans_"},
			       {"Rabbit_"},
			       {"Vanillite_"},
			       {"A_"},
			       {"Frog_"},
			       {"Bird_"},
			       {"Rabbit_"},
			       {"Smeargle_"}};
    }


    //created 2d array of tile objects based on map
    public void loadTiles(){
	Scanner sc = null;

	try{
	    sc = new Scanner(new File(file));
	}
	catch (Exception e){}

	//read map into array
	//map contains information on tile shape and position 
	Tile t = null;
	String s = "";
	boolean blocked = false;

	int row = 0;
	while (sc.hasNext()){
	    String line = sc.nextLine();
	    for (int col = 0; col < line.length(); col++){
		char curr = line.charAt(col);
		if (curr >= 'a' && curr <= 'z'){
		    s = "Border_";
		    blocked = true;
		}
		if (curr >= 'A' && curr <= 'Z'){
		    s = "Wall_";
		    blocked = true;
		}
		if (curr >= '0' && curr <= '9'){
		    s = "Floor_";
		    blocked = false;
		}
		if (curr == ' '){
		    s = "None";
		    blocked = false;
		}
		
		s += curr;
		t = new Tile(s, blocked);

		t.setX(col * t.getWidth());
		t.setY(row * t.getHeight());

		tiles[row][col] = t;    
	    }
	    row++;
	}	
	sc.close();
    }


    //generate monsters in random locations
    public void makeMonsters(int lv){
	monsters = new LinkedList<Monster>();

	int num = 1;
	if (level > 3 || !id.equals("Hall_8"))
	    num = (int)(Math.random() * (15 - lv)) + 5;
	if (id.equals("Office"))
	    num = 0;

	//different monsters based on level
	for (int i = 0; i < num; i++){

	    int row = lv - 1;
	    int col = 0;
 
	    if (level == 2 && !id.equals("Hall_8"))
		col = 1;
	    else if (id.equals("Hall_8"))
		col = 0;
	    else 
		col = (int)(Math.random() * names[row].length);

	    String s = names[row][col];
	    Monster mon = new Monster(s, lv, this);

	    //limit spawning to 320 * 320 area centered within panel
	    int x = (int)(Math.random() * 321) + 96;
	    int y = (int)(Math.random() * 321) + 96;

	    mon.findCorners(x, y);

	    //if monster generated in invalid position
	    //recalculate x and y cor
	    while (mon.topLeft() || mon.topRight() || mon.bottomLeft() || mon.bottomRight()||!mon.outOfSafe()){
		x = (int)(Math.random() * 321) + 96;
		y = (int)(Math.random() * 321) + 96;

		mon.findCorners(x, y);
	    }	
	    mon.setX(x);
	    mon.setY(y);

	    /*
	    if (s.equals("Ghost_"))
		muk = mon;
	    //replicate Muk as miniMuks
	    if (s.equals("MiniMuk_")){
		mon.setX(muk.getX());
		mon.setY(muk.getY());	 
	    }
	    */
	    monsters.add(mon);
	}
    }

    public void addToBounds(MapObject ob){
	Rectangle r = ob.getBounds();

	if ((int)r.getHeight() != 0 && (int)r.getWidth() != 0){
	    for (int row = (int)r.getY()/32; row < ((int)r.getY() + (int)r.getHeight())/32; row++)
		for (int col = (int)r.getX()/32; col < ((int)r.getX()+ (int)r.getWidth())/32; col++)
		    tiles[row][col].setBlocked(true);
	}   
	if (ob.getID().contains("Stairs") || 
	    ob.getID().equals("Door_open") ||
	    ob.getID().equals("Door_2") ||
	    ob.getID().equals("Escalator") ||
	    ob.getID().contains("Exit")){

	    Rectangle v = ob.getValid();

	    for (int row = (int)v.getY()/32; row <= ((int)v.getY() + (int)v.getHeight())/32; row++){
		for (int col = (int)v.getX()/32; col <= ((int)v.getX() + (int)v.getWidth())/32; col++){
		    tiles[row][col].setBlocked(false);
		    tiles[row][col].setTransferPoint(ob.getID());
		}
	    }
	}
    }

    public void add(MapObject ob){
	addToBounds(ob);
	props.add(ob);
    }
   

    /*----------------------------------------- Getters and Setters ----------------------------------------*/
 
    public LinkedList<Monster> getMonsters(){
	return monsters;
    }

    public String getID(){
	return id;
    }

    public boolean empty(){
	return monsters.isEmpty();
    }
 
    public Tile getTile(int x, int y){
	return tiles[y][x];
    }

    public SuperList getProps(){
	return props;
    }


    public String getFile(){
	return file;
    }

    public void setMonsters(LinkedList<Monster> m){
	monsters = m;
    }

    public Rectangle getSafeSpot(){
	return safeSpot;
    }

    /*----------------------------------------- Misc ----------------------------------------*/

    public void draw(Graphics2D g){
	//draw tiles
	for (int row = 0; row < height; row++)
	    for (int col = 0; col < width; col++)
		tiles[row][col].draw(g);

	//draw props
	props.draw(g);
    }
    

    public boolean equals(TileMap other){
	return this.level == other.level && this.props.equals(other.props);
    }

    //give one of the monsters the key
    public void addKey(){
        makeMonsters(1);
	monsters.get(0).setSpeed(2);
	monsters.get(0).setItem("Key");
    }


}
