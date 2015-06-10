import java.io.File;
import java.util.LinkedList;
import java.util.Random;
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
    private Floor[] floors;
    private Floor currentFloor;

    private Rectangle hp;
    private Rectangle pp;

    BufferedImage hpbar;
    BufferedImage ppbar;

    private int level;

    private LinkedList<Monster> monsters;
    private LinkedList<Projectile> books;
    private LinkedList<MapObject> itemDrop = new LinkedList<MapObject>();
    private Timer timer;

    /*------------------------------------------ Key Class ----------------------------------------------*/
  
    private class Key implements KeyListener {

	//move player or attack
	public void keyPressed(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
		//attack event
	    case KeyEvent.VK_SPACE:
		p.attacking(true);
		break;
		//change of projectiles
	    case KeyEvent.VK_SHIFT:
		p.getInventory().next();
		break;
		
	    case KeyEvent.VK_A:
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
	setPreferredSize(new Dimension(width+255, height));
	setFocusable(true);
	setDoubleBuffered(true);
	setVisible(true);

	inGame = true;

	//setup floors 
	floors = new Floor[11];

	for (int i = 2; i < 11; i++){
	    floors[i] = new Floor(i);
	}
	level = 10;
	System.out.println(level);
	currentFloor = floors[level];
	currentFloor.setX(2);
	currentFloor.setY(0);

	tilemap = currentFloor.getCurrent();

        p = new Player(tilemap);

	monsters = tilemap.getMonsters();
	for (Monster m: monsters){
	    m.resetP(p);
	}

	hp = new Rectangle(30, 24, 96, 4);
	pp = new Rectangle(30, 44, 96, 4);

	hpbar = null;
        ppbar = null;

	try {
	    hpbar = ImageIO.read(new File("../Sprites/Display/HPbar.png"));
	    ppbar = ImageIO.read(new File("../Sprites/Display/PPbar.png"));
	}
	catch (Exception e){}

	timer = new Timer(DELAY, this);
	timer.start();
    }


    /*--------------------------------------- Displaying Content ------------------------------------------*/

    //draw all components on screen 
    public void paint(Graphics g){
	super.paint(g);

	g.setColor(Color.CYAN);

	//draw floor
	BufferedImage f = currentFloor.getFloor();
	if (tilemap.getID().contains("Hall")){
	    g.drawImage(f, 0, 0, this);
	}

	Graphics2D im = (Graphics2D)g;
	tilemap.draw(im);
	p.draw(im);


        books = p.getProjectiles();
	for (Projectile p: books)
	    p.draw(im);
      
	for (Monster monster: monsters){	   
	    monster.draw(im);
	}
	
	for (MapObject item: itemDrop){
	    item.draw(im);
	}

	p.getInventory().draw(im);

	drawStats(g);
	drawDisplay(g);
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    //draw hp and pp bars
    //change rectangle based on each and draw
    public void drawStats(Graphics g){
	int hwidth = (int)(((double)p.getHP())/p.getMaxHP() * 96);
	int pwidth = (int)(((double)p.getPower())/p.getMaxP() * 96);

	hp.setSize(hwidth, (int)hp.getHeight());
	pp.setSize(pwidth,(int)pp.getHeight());

	g.fillRect((int)hp.getX(), (int)hp.getY(), (int)hp.getWidth(), (int)hp.getHeight());
	g.fillRect((int)pp.getX(), (int)pp.getY(), (int)pp.getWidth(), (int)pp.getHeight());

	g.drawImage(hpbar, 0, 20, this);
	g.drawImage(ppbar, 0, 40, this);
    }


    public void drawDisplay(Graphics g){
	g.drawRect(513, 0, 256, 640);
    }

    /*------------------------------------------ Updating ----------------------------------------------*/

    public void actionPerformed(ActionEvent e){
	inGame();
	updateBoard();
	updatePlayer();
	updateMonsters();
	updateProjectiles();
	checkCollisions();
	repaint();
    }

    public void inGame(){
	if (!inGame)
	    timer.stop();
    }

    /*------------------------------------------ Update Board -----------------------------------------------*/

    //change tilemap as player moves to next area
    //check for moving out of bounds
    //or stepping on transfer point
    public void updateBoard(){
	int px = p.getX();
	int py = p.getY();
	int fx = currentFloor.getX();
	int fy = currentFloor.getY();
	int temp = currentFloor.getLevel();

	//if player moves off current map
	if (px < 0 || px + p.getWidth() >= width ||
	    py < 0 || py + p.getHeight() >= height){

	    if (px < 0){
		px = (15 - px / 32) * 32;
		fx -= 1;
	    }
	    else if (px + p.getWidth() >= width){
		px = 0;
		fx += 1;
	    }
	    else if (py < 0){
		py = (15 - py / 32) * 32;
		fy -= 1;
	    }
	    else if (py + p.getHeight() >= height){
		py = 0;
		fy += 1;
	    }
	    p.setX(px);
	    p.setY(py);
	    
	    currentFloor.setX(fx);
	    currentFloor.setY(fy);

	    tilemap = currentFloor.getCurrent();
	    itemDrop = new LinkedList<MapObject>();
	    monsters = tilemap.getMonsters();
	    p.setMap(tilemap);
	    p.setProjectiles(new LinkedList<Projectile>());
	}
	//if player takes stairs/escalator/enters door/exits room
	else {
	    transfer(px, py);
	    transfer(px + p.getWidth(), py);
	    transfer(px, py + p.getHeight());
	    transfer(px + p.getWidth(), py + p.getHeight());
	}
    }

    //if player steps on transfer point
    //move to next tilemap/floor
   public void transfer(int x, int y){
	int fx = currentFloor.getX();
	int fy = currentFloor.getY();
	Tile t = tilemap.getTile(x/32, y/32);  

	if (!t.transferPoint().equals("None")){

	    switch(t.transferPoint()){
	    case "Door_open": case "Door_2":
		currentFloor.setY(fy - 1);
		p.setY(448);
		break;
	    case "Stairs_D":
		level--;
		currentFloor = floors[level];
		currentFloor.descend();
		p.setX(160);
		p.setY(416);
		break;
	    case "Stairs_U": 
		level++;
		currentFloor = floors[level];
		currentFloor.ascend();
		p.setX(384);
		p.setY(384);
		break;
	    case "Exit_H":
		currentFloor.setY(fy + 1);
		p.setY(97);
		break;
	    }
	    tilemap = currentFloor.getCurrent();
	    itemDrop = new LinkedList<MapObject>();
	    monsters = tilemap.getMonsters();
	    p.setMap(tilemap);
	    p.setProjectiles(new LinkedList<Projectile>());
	}
   }
  
    /*-------------------------------------- Update Player -----------------------------------------*/

    public void updatePlayer(){
	p.update();
	
	int i = 0; 
	while (i < itemDrop.size()){
	    MapObject ob = itemDrop.get(i);

	    //if player steps on dropped item
	    if (p.getBounds().intersects(ob.getBounds())){
		//increase quantity of item in inventory
		p.getInventory().find(ob.getID()).changeQuantity(1);
		itemDrop.remove(i);				 
	    }
	    else{
		i++;
	    }
	}
    }

    /*------------------------------------------ Update Projectiles ----------------------------------------------*/

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

	    //if projectiles go out of bounds
	    //they disappear
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

    /*------------------------------------------ Update Monsters ----------------------------------------------*/

    public void updateMonsters(){

	int i = 0;
	while (i < monsters.size()){
	    Monster m = monsters.get(i);
	    m.resetP(p);

	    //if monster has no more hp, remove
	    if (m.getHP() <= 0){
		Random rn = new Random();
		int randitem = rn.nextInt(2);
		int randchance = rn.nextInt(10);
		System.out.println(randitem);
		if (randchance%2 == 0){
		    if (randitem == 0){
			itemDrop.add(new MapObject("Bagel", m.getX(), m.getY()));
		    }
		    else if (randitem == 1){
			itemDrop.add(new MapObject("Coffee", m.getX(), m.getY()));
		    }
		}
		monsters.remove(i);
	    }
	    //if monster moves off tilemaps, remove
	    else if (m.getX() < 0 || m.getX() + m.getWidth() >= width ||
		     m.getY() < 0 || m.getY() + m.getHeight() >= height){
		monsters.remove(i);
	    }
	    else {
		//if in range of monster, attack
		if (Math.sqrt(Math.pow(p.getX() - m.getX(), 2) + 
			      Math.pow(p.getY() - m.getY(), 2)) <= m.getRadius())
		    m.setIdle(false);
		//if monster was attacked by player
		else if (m.getHP() < m.getMaxHP())
		    m.setIdle(false);
		else 
		    m.setIdle(true);
		i++;
		m.update();
	    }
	}
	tilemap.setMonsters(monsters);
    }
  
    /*------------------------------------------ Check Collisions ----------------------------------------------*/

    public void checkCollisions(){

	Rectangle pl = p.getBounds();

	for (Monster m: monsters){
	    Rectangle mon = m.getBounds();

	    m.resetP(p);

	    //collision between player and monster
	    //player loses hp
	    if ( (Math.abs(p.getX()/32 - m.getX()/32)) < 0.5 &&
		 (Math.abs(p.getY()/32 - m.getY()/32)) < 0.5){
		System.out.println("You've been caught!");
		m.repel();
		p.setHP(p.getHP() - m.getDamage());
	    }
	    //collision between projectile and monster
	    //if projectile collides, it disappears
	    //and monster loses hp

	    int i = 0;
	    while (i < books.size()){
		Rectangle proj = books.get(i).getBounds();
		if (mon.intersects(proj)){
		    m.setHP(m.getHP() - books.get(i).getDamage());
		    books.remove(i);
		}
		else 
		    i++;
	    }	  
	}
    }

    public static void main(String[] args){
	GamePanel g = new GamePanel();
    }

}
