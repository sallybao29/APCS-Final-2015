import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
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
    private BufferedImage floor;

    private LinkedList<Monster> monsters;


    /*----------------------------------------- Constructor ----------------------------------------*/
 
    public TileMap(String type){

	id = type;
	floor = null;

	map = new char[height][width];
        tiles = new Tile[height][width];

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
	    String s = "";
	    switch (level){
	    case 10:
		s = "Rabbit_";
		break;
	    case 9:
		break;
	    case 8:
		break;
	    case 7:
		break;
	    case 6:
		break;
	    case 5:
		break;
	    case 4:
		break;
	    case 3:
		break;
	    case 2:
		break;
	    }

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


    public String getFile(){
	return file;
    }

    public void setFloor(BufferedImage im){
	floor = im;
    }


    public void draw(Graphics2D g){
	for (int row = 0; row < height; row++){
	    for (int col = 0; col < width; col++){
		tiles[row][col].draw(g);
	    }
	}
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
	TileMap t = new TileMap(type);

	System.out.println(t);
	String s = "";

	/*
	for (int row = 0; row < t.height; row++){
	    for (int col = 0; col < t.width; col++){
	        s += t.tiles[row][col].isBlocked() + " ";
	    }
	    s += "\n";
	}
	System.out.println(s);
	*/


    }

}
