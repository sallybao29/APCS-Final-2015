import java.io.File;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 512;
    private static final int height = 512;
    private final int DELAY = 15;

    private boolean inGame;

    private Player p;
    private TileMap tilemap;
    private Monster m;
    // private Floor[] floors;
    private int level = 10;

    private LinkedList<Monster> monsters;
    private LinkedList<Projectile> books;
    private Timer timer;

    /*------------------------------------------ Key Class ----------------------------------------------*/
  
    private class Key implements KeyListener {

	public void keyPressed(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_SPACE:
		p.attacking(true);
		break;
	    case KeyEvent.VK_RIGHT:
		p.setDirection('R');
		p.setDX(1);
		p.setDY(0);
		break;
	    case KeyEvent.VK_LEFT:
		p.setDirection('L');
		p.setDX(-1);
		p.setDY(0);
		break;
	    case KeyEvent.VK_UP:
		p.setDirection('U');
		p.setDY(-1);
		p.setDX(0);
		break;
	    case KeyEvent.VK_DOWN:
		p.setDirection('D');
		p.setDY(1);
		p.setDX(0);
		break;
	    }
	}
	
	public void keyReleased(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_SPACE:
		p.attacking(false);
		break;
	    case KeyEvent.VK_RIGHT: 
	    case KeyEvent.VK_LEFT:
		p.setDX(0);
		break;
	    case KeyEvent.VK_UP:
	    case KeyEvent.VK_DOWN:
		p.setDY(0);
		break;
	    }
	}
	
	public void keyTyped(KeyEvent e) {}
    }


    /*------------------------------------------ Constructor ----------------------------------------------*/    

    public GamePanel(){
	super();
	addKeyListener(new Key());
	setPreferredSize(new Dimension(width, height));
	setFocusable(true);
	setDoubleBuffered(true);
	setVisible(true);

	inGame = true;

	/*
	floors = new Floor[11];

	for (int i = 2; i < 11; i++){
	    floors[i] = new Floor(i);
	}
	*/

	tilemap = new TileMap("Hall_1");
	tilemap.makeMonsters(level);

        p = new Player(tilemap);

	monsters = tilemap.getMonsters();

	timer = new Timer(DELAY, this);
	timer.start();
    }


    /*--------------------------------------- Displaying Content ------------------------------------------*/

    //draw all components on screen 
    public void paint(Graphics g){
	super.paint(g);

	//BufferedImage f = tilemap.getFloor();

	/*
	if (f != null){
	    g.drawImage(f, 0, 0 null);
	}
	*/

	Graphics2D im = (Graphics2D)g;
	tilemap.draw(im);
	p.draw(im);

        books = p.getProjectiles();

	for (Projectile p: books)
	    p.draw(im);
      
	for (Monster monster: monsters){	   
	    monster.draw(im);
	}


	g.drawString("HP: " + p.getHP(), 128, 100);
	g.drawString("PP: " + p.getPower(), 128, 128);
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    /*------------------------------------------ Updating ----------------------------------------------*/

    public void actionPerformed(ActionEvent e){
	inGame();

	p.update();
	updateMonsters();

        //(new Thread(new MRunnable(tilemap.getFile(),m,p))).start();
	updateProjectiles();

	checkCollisions();

	repaint();

    }

    public void inGame(){
	if (!inGame)
	    timer.stop();
    }

    public void updateProjectiles(){
        books = p.getProjectiles();

	int i = 0; 
	while (i < books.size()){
	    Projectile b = books.get(i); 
	    boolean removed = false;

	    int x = b.getX();
	    int y = b.getY();
	    int tx = x / 32;
	    int ty = y / 32;
	    Tile t;

	    //go out of bounds
	    if  (x < 0 || x + b.getWidth() > width ||
		 y < 0 || y + b.getHeight() > height){
		books.remove(i);
		removed = true;
	    }

	    //crash into walls
	    else {
		if (b.getDirection() == 'D')
		    ty = y / 32 + 1;   
		else if (b.getDirection() == 'R')
		    tx = x / 32 + 1;
		if (ty != 16 && tx != 16){
		    t = tilemap.getTile(tx, ty);
		    if (t.isBlocked()){
			books.remove(i); 
			removed = true;
		    }
		}	
	    }
	    if (removed == false){
		b.move();
		i++;
	    }
	}
    }

    public void updateMonsters(){
	int i = 0;
	while (i < monsters.size()){
	    Monster m = monsters.get(i);

	    if (m.getHP() <= 0)
		monsters.remove(i);
	    else {
		//if player in range of monster, enter chasing state
		//otherwise remain idle
		if (Math.sqrt(Math.pow(p.getX() - m.getX(), 2) + 
			      Math.pow(p.getY() - m.getY(), 2)) <= m.getRadius())
		    m.setIdle(false);
		else 
		    m.setIdle(true);
		i++;
		m.update();
	    }
	}
    }
  

    public void checkCollisions(){

	Rectangle pl = p.getBounds();

	for (Monster monster: monsters){
	    Rectangle mon = monster.getBounds();

	    //collision between player and monster
	    //player loses hp
	 
	    if (pl.intersects(mon)){
		p.setHP(p.getHP() - monster.getDamage());
	    }

	    //collision between projectile and monster
	    //if projectile collides, it disappears
	    //and monster loses hp

	    int i = 0;
	    while (i < books.size()){
		Rectangle proj = books.get(i).getBounds();
		if (mon.intersects(proj)){
		    monster.setHP(monster.getHP() - books.get(i).getDamage());
		    books.remove(i);
		}
		else {
		    i++;
		}
	    }	  
	}
    }


}
