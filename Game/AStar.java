import java.io.*;
import java.util.*;

public class AStar{

    private char[][] map;
    private Frontier f;
    private Node e, start;
    private int maxX, maxY;
    private char plpos = 'P';
    private char floor = ' ';
    private char wall = 'w';
    private char monpos = 'M';
    private Node rec;
    private Monster m;

    public AStar(String file, Monster mon){
	maxX = 16;
	maxY = 16;
	map = new char[maxX][maxY];

	m = mon;

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

    public void move(Player pl){
	int pX, pY, mX, mY;

	pX = pl.getX() / 32;
	pY = pl.getY() / 32;
	mX = m.getX() / 32;
	mY = m.getY() / 32;

	e = new Node(pX,pY);
	map[pX][pY] = plpos;
	map[mX][mY] = monpos;

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
	    delay(50);
	    //System.out.println(this);
	}

	// path recovery
	rec = new Node(current.getX(),current.getY()); 
	for (Node path = current.getPrev(); path != null ; path = path.getPrev()){	    
	    Node temp = new Node(path.getX(),path.getY());
	    temp.setNext(rec);
	    rec = temp;
	}
    }

    public void nextStep(){
	if (rec != null){
	    if ((rec.getX() - m.getX()) > 0)
		m.setDirection('R');
	    else if ((rec.getX() - m.getX()) < 0)
		m.setDirection('L');
	    else if ((rec.getY() - m.getY()) > 0)
		m.setDirection('D');
	    else
		m.setDirection('U');
	    m.setDX(rec.getX()*32-m.getX());
	    //System.out.println(rec.getX());
	    m.setDY(rec.getY()*32-m.getY());
	    rec = rec.getNext();
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
  
}
