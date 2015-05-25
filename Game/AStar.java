import java.io.*;
import java.util.*;

public class AStar{

    private char[][] map;
    private Frontier f;
    private Node e, start;
    private int maxX, maxY;
    private char plpos = 'P';
    private char floor = 'z';
    private char wall = 'w';
    private char monpos = 'M';

    public AStar(String file){
	maxX = 16;
	maxY = 16;
	map = new char[maxX][maxY];

	try{
	    Scanner sc = new Scanner(new File(file));
	    int h = 0;
	    while(sc.hasNext()){
		String s = sc.nextLine();
		for(int w = 0; w < maxX; w++){
		    map[h][w] = s.charAt(w);
		}
		h++;
	    }
	}
	catch (Exception e){}

    }

    public void addToFront(int tx,int ty, Node current){
	Node tmp = null;
	if (map[tx][ty] == floor || map[tx][ty] == plpos){
	    tmp = new Node(tx,ty);

	    tmp.setPriority(TaxiDist(tmp) + current.getSteps()); 
	    tmp.setSteps(current.getSteps()+1);

	    tmp.setPrev(current);
	    f.add(tmp);
	}						
    }
    public double TaxiDist(Node c){
	int xDist = Math.abs(c.getX()-e.getX());
	int yDist = Math.abs(c.getY()-e.getY());
	return xDist + yDist;
    }

    public void move(Monster m, Player pl){
	int pX, pY, mX, mY;

	pX = pl.getX() / 32;
	pY = pl.getY() / 32;
	mX = m.getX() / 32;
	mY = m.getY() / 32;

	e = new Node(pX,pY);
	map[pX][pY] = plpos;
	map[mX][mY] = monpos;
	System.out.println(this);

	f = new Frontier();
	start = new Node(mX,mY);
	f.add(start);

	Node current = null;
	while (!f.isEmpty()){
	    current = f.remove();
	    int cx = current.getX();
	    int cy = current.getY();

	    if (map[cx][cy]==plpos)
		break;
						
	    map[cx][cy]=monpos;

	    addToFront(cx+1,cy,current);
	    addToFront(cx-1,cy,current);
	    addToFront(cx,cy+1,current);
	    addToFront(cx,cy-1,current);

	    //delay(50);
	    //System.out.println(this);
	}

	// path recovery
	Node rec = new Node(current.getX(),current.getY()); 
	for (Node path = current.getPrev(); path != null ; path = path.getPrev()){	    
	    Node temp = new Node(path.getX(),path.getY());
	    temp.setNext(rec);
	    rec = temp;
	}
	for (Node r = rec; r != null; r = r.getNext()){
	    //map[r.getX()][r.getY()] = 'G';
	    if ((r.getX() - m.getX()) > 0)
		m.setDirection("right");
	    else if ((r.getX() - m.getX()) < 0)
		m.setDirection("left");
	    else if ((r.getY() - m.getY()) > 0)
		m.setDirection("down");
	    else
		m.setDirection("up");
	    m.setX(r.getX()*32);
	    m.setY(r.getY()*32);
	    delay(500);
	    //System.out.println(this);
	}
    }
    public void delay(int n){
	try {
	    Thread.sleep(n);
	} catch (Exception e) {}
    }
    public String toString(){
	String s = "";
	for (int i = 0; i < map.length; i++){
	    for (int j = 0; j < map[i].length; j++){
		s += map[i][j];
	    }
	    s += "\n";
	}
	return s;
    }
    /*
    public static void main(String[] args){
	AStar a = new AStar("../Maps/Hall_1.txt");
	AStar b = new AStar("map.txt");
	Monster m = new Monster();
	Player p = new Player();
	System.out.println(a);
	a.move(m,p);
    }
    */
}
