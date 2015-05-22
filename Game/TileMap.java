import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class TileMap{
    private int x;
    private int y;

    private int tileSize;
    private char[][] map;

    private int width = 40;
    private int height = 40;

    public TileMap(String type, String wall, String floor){

	switch (type){
	case "hall_1":
	    break;
	case "hall_2":
	    break;
	case "hall_3":
	    break;
	case "classroom":
	    break;
	    
	}

	if (type
        tileSize = size;
	Scanner sc = null;

	try{
	    sc = new Scanner(new File(file));

      
	    //read map into array
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

    /*
      public BufferedImage getBG(){
      return background;
      }
    */
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public String toString(){
	String s = "";
	while (int i = 0; i < map.length; i++){
	    while (int j = 0; j < map[i].length; j++){
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
