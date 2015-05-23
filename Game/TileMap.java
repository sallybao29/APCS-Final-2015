import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class TileMap{

    private final int tileSize = 32;
    private final int width = 14;
    private final int height = 14;

    private int x;
    private int y;

    private char[][] map;
    private Tile[][] tiles;


    public TileMap(String type, String wall, String floor){

	String file;

	//only hall_1 exists at the moment
	switch (type){
	case "hall_1":
	    file = "../Maps/Hall_1";
	    break;
	case "hall_2":
	    file = "../Maps/Hall_2";
	    break;
	case "hall_3":
	    file = "../Maps/Hall_3";
	    break;
	case "classroom":
	    break;	    
	}

	Scanner sc = null;

	try{
	    sc = new Scanner(new File(file));

      
	    //read map into array
	    //map contains information on tile shape and position 
	    while(sc.hasNext()){
		int h = 0;
		String s = sc.nextLine();
		for(int w = 0; w < width; w++){
		    map[h][w] = s.charAt(w);
		}
		h++;
	    }
	}
	catch (Exception e){}

    }

    //created 2d array of tile objects based on 
    public void setTiles(String path){

    }

  
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public String toString(){
	String s = "";
	for (int i = 0; i < map.length; i++){
	    for (int j = 0; j < map[i].length; j++){
		s += "" + map[i][j];
	    }
	    s += "\n";
	}
	return s;
    }

    public static void main(String[] args){
	TileMap t = new TileMap("map.txt", 10);
	System.out.println(t);
    }
}
