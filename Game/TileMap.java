import java.io.*;
import java.util.*;
import java.awt.Rectangle;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class TileMap{

    private final int width = 16;
    private final int height = 16;

    private char[][] map;
    private Tile[][] tiles;
    private String file;
    private String id;

    private LinkedList<Monster> monsters;
    private SuperList props; 


    /*----------------------------------------- Constructor ----------------------------------------*/
 
    public TileMap(String type, int level){

	id = type;

	props = new SuperList(id);

	map = new char[height][width];
        tiles = new Tile[height][width];

	if (type.contains("Class"))
	    file = "../Maps/Class.txt";
	else 
	    file = "../Maps/" + type + ".txt";

	Scanner sc = null;

	try{
	    sc = new Scanner(new File(file));

	    //read map into array
	    //map contains information on tile shape and position 
	    int j = 0;
	    while (sc.hasNext()){
		String line = sc.nextLine();
		for (int i = 0;i < line.length();i++){
		    map[j][i] = line.charAt(i);
		}
		j++;
	    }
	}
	catch (IndexOutOfBoundsException e){
	    System.out.println("Index out of bounds");
	}
	catch (NullPointerException e){
	    System.out.println("Null");
	}
	catch(FileNotFoundException e){
	    System.out.println("File not found");
	}
	finally {
	    sc.close();
	}
	loadTiles();
	makeMonsters(level);
	//don't know what to call it
	foo();
    }

   
    /*--------------------------------------- Initialization --------------------------------------*/
 
    //created 2d array of tile objects based on map
    public void loadTiles(){

	Tile t = null;
	String id = "";
	boolean blocked = false;

	for (int row = 0; row < height; row++){
	    for (int col = 0; col < width; col++){

		char curr = map[row][col];

		if (curr >= 'a' && curr <= 'z'){
		    id = "Border_";
		    blocked = true;
		}
		if (curr >= 'A' && curr <= 'Z'){
		    id = "Wall_";
		    blocked = true;
		}
		if (curr >= '0' && curr <= '9'){
		    id = "Floor_";		   
		    blocked = false;
		}

		if (curr == ' '){
		    id = "None";
		    blocked = false;
		}
		id += curr;
		t = new Tile(id, blocked);

		t.setX(col * t.getWidth());
		t.setY(row * t.getHeight());

		tiles[row][col] = t;
	    }
	}
    }

    //generate monsters in random locations
    public void makeMonsters(int level){
	monsters = new LinkedList<Monster>();

	int num = (int)(Math.random() * (20 - level)) + 5;

	//different monsters based on level
	for (int i = 0; i < num; i++){
	    String[] names = null;
	    switch (level){
	    case 10:
	        names = new String[]{"Rabbit_"};
		break;
	    case 9:
		names = new String[]{"Rabbit_"};
		break;
	    case 8:
		names = new String[]{"Rabbit_"};
		break;
	    case 7:
	        names = new String[]{"Frog_", "Cat_"}; 
		break;
	    case 6:
		names = new String[]{"UnknownA_"};
		break;
	    case 5:
		names = new String[]{"Rabbit_"};
		break;
	    case 4:
		names = new String[]{"Rabbit_"};
		break;
	    case 3:
		names = new String[]{"Ghost_"};
		break;
	    case 2:
		names = new String[]{"Rabbit_"};
		break;
	    }
	 
	    String s = names[(int)(Math.random() * names.length)];
	    Monster mon = new Monster(s, level, this);

	    //limit spawning to 320 * 320 area centered within panel
	    int x = (int)(Math.random() * 321) + 96;
	    int y = (int)(Math.random() * 321) + 96;

	    mon.findCorners(x, y);

	    //if monster generated in invalid position
	    //recalculate x and y cor
	    while (mon.topLeft() || mon.topRight() || mon.bottomLeft() || mon.bottomRight()){

		x = (int)(Math.random() * 321) + 96;
		y = (int)(Math.random() * 321) + 96;

		mon.findCorners(x, y);
	    }
	  
	    mon.setX(x);
	    mon.setY(y);

	    monsters.add(mon);
	}
    }

    public void foo(){
	for (int i = 0; i < props.size(); i++){
	    MapObject ob = props.get(i);
	    Rectangle r = ob.getBounds();
	    int rx = (int)r.getX();
	    int ry = (int)r.getY();
	    int rw = (int)r.getWidth();
	    int rh = (int)r.getHeight();

	    for (int row = ry / 32; row < (ry + rh)/ 32; row++){
		for (int col = rx / 32; col < (rx + rw) / 32; col++){
		    if (ob.getID().contains("Stairs") || 
			ob.getID().equals("Door_open") ||
			ob.getID().equals("Escalator")){

			tiles[row][col].setBlocked(false);
			tiles[row][col].setTransferPoint(ob.getID());
		    }
		    else 
		        tiles[row][col].setBlocked(true);
		}
	    }
	}
    }


    /*----------------------------------------- Getters and Setters ----------------------------------------*/
 
    public LinkedList<Monster> getMonsters(){
	return monsters;
    }

    public String getID(){
	return id;
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

    public void draw(Graphics2D g){
	for (int row = 0; row < height; row++){
	    for (int col = 0; col < width; col++){
		tiles[row][col].draw(g);
	    }
	}
	props.draw(g);
    }
    
    public String toString(){
	String s = "";
	for (int row = 0; row < height; row++){
	    for (int col = 0; col < width; col++){
		s += map[row][col];
	    }
	s += "\n";
	}
	return s;
    }

   /*----------------------------------------- Testing ----------------------------------------*/
 
    public static void main(String[] args){
	String type = "Hall_1";
	TileMap t = new TileMap(type, 10);

	System.out.println(t);
	String s = "";

	for (Monster m: t.monsters){
	    System.out.println(m.getImage());
	}

    }

}
