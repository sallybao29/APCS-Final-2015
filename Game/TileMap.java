import java.io.*;
import java.util.Scanner;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class TileMap{

    private final int width = 16;
    private final int height = 16;

    private int x;
    private int y;

    private char[][] map;
    private Tile[][] tiles;


    public TileMap(String type, String wall, String floor){

	map = new char[height][width];
        tiles = new Tile[height][width];

	String file = null;

	//only hall_1 exists at the moment
	switch (type){
	case "hall_1":
	    file = "../Maps/Hall_1.txt";
	    break;
	    /*
	case "hall_2":
	    file = "../Maps/Hall_2.txt";
	    break;
	case "hall_3":
	    file = "../Maps/Hall_3.txt";
	    break;
	case "classroom":
	    break;
	    */	    
	}

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

	loadTiles(wall, floor);

    }

    //created 2d array of tile objects based on map
    public void loadTiles(String wall, String floor){

	BufferedImage b = null;
	Tile t = null;

	for (int row = 0; row < height; row++){
	    for (int col = 0; col < width; col++){
		if (map[row][col] == 'z'){
		    try {
			b = ImageIO.read(new File(floor));
		    } 
		    catch (Exception e) {}
		    t = new Tile(b, false);
		}
		else {
		    String s = wall + map[row][col] + ".png";
		    try {
			b = ImageIO.read(new File(s));
		    }
		    catch (Exception e){}
		    t = new Tile(b, true);
		}
		t.setXY(col * t.getWidth(), row * t.getHeight());
		tiles[row][col] = t;
	    }
	}
    }

  
    public Tile getTile(int x, int y){
	return tiles[y][x];
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

  
    public static void main(String[] args){
	String type = "hall_1";
	String wall =  "../Tileset/Wall_Tiles/WT_";
	String floor = "../Tileset/Floor_Tiles/Tile_5.png";
	TileMap t = new TileMap(type, wall, floor);

	System.out.println(t);

	for (int row = 0; row < t.height; row++){
	    for (int col = 0; col < t.width; col++){
		System.out.println("" + t.tiles[row][col].getX() + " ," +  t.tiles[row][col].getY());
	    }
	}


    }

}
