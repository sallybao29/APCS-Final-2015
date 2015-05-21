import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class TileMap{
    private int x;
    private int y;

    private int tileSize;
    private int[][] map;

    private int width;
    private int height;

    public TileMap(String bg, String file, int size){
        tileSize = size;
	Scanner sc = null;

	try{
	    //background image
	    background = imageIO.read(new File(bg));
	    sc = new Scanner(new File(file));

      
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


    public BufferedImage getBG(){
	return background;
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

}
