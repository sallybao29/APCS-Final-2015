import java.io.*;
import java.util.*;
import java.awt.*;

public class TileMap{
    private int x;
    private int y;

    private int size;
    private int[][] map;
    private int width;
    private int height;

    public TileMap(String name, int size){
	this.size = size;
	Scanner sc = null;

	try{
	    //first two lines of file are height and width of map
	    sc = new Scanner(new File(name));
	    height = Integer.parseInt(sc.nextLine());
	    width = Integer.parseInt(sc.nextLine());
	    map = new int[height][width];

	    //read map into array
	    while(sc.hasNext()){
		int h = 0;
		String s = sc.nextLine();
		for(int w = 0; w < width; w++){
		    map[h][w] = Integer.parseInt("" + s.charAt(w));
		    h++;
		}
	    }
	}
	catch (Exception e){}

    }

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

    public static void main(String[] args){
	TileMap t = new TileMap("map.txt", 20);
	System.out.println(Arrays.deepToString(t.map));
    }
}
