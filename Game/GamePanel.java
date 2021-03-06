import java.io.File;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 512;
    private static final int height = 512;
    private final int DELAY = 15;

    private boolean inGame;
    private boolean inStart;
    private boolean gameWon;

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
    private LinkedList<MapObject> itemDrop; 
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
	    case KeyEvent.VK_C:
		p.use(5);
		break;
	    case KeyEvent.VK_B:
		p.use(4);
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
	    case KeyEvent.VK_S:
		if (inStart)
		    inStart = false;
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
	inStart = true;
	gameWon = false;

	itemDrop = new LinkedList<MapObject>();
	//setup floors 
	floors = new Floor[11];

	for (int i = 2; i < 11; i++){
	    floors[i] = new Floor(i);
	}
	level = 10;

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

	if (inStart){
	    paintStartScreen(g);
	}
	else if (gameWon){
	    paintWinScreen(g);
	}
	else if (!inGame && !gameWon){
	    paintGameOver(g);
	}
	else {
	    paintComponents(g);
	}
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void paintStartScreen(Graphics g){
	BufferedImage snake = null;
	try{ 
	    snake = ImageIO.read(new File("Snakes.png"));
	}
	catch (Exception e){}
	g.drawImage(snake, 0, 0, this);
    }

    public void paintWinScreen(Graphics g){
	BufferedImage phone = null;
	try{ 
	    phone = ImageIO.read(new File("Phone.png"));
	}
	catch (Exception e){}
	g.drawImage(phone, 0, 0, this);
    }

    public void paintGameOver(Graphics g){
	setBackground(Color.BLACK);
	Font font = new Font("Jokerman", Font.PLAIN, 30);
	String message = "Congratulations!\n You have died!";

        setFont(font);
	g.drawString(message, 10, 256);

    }

    public void paintComponents(Graphics g){
	g.setColor(Color.CYAN);

	BufferedImage f = currentFloor.getFloor();
	if (tilemap.getID().contains("Hall"))
	    g.drawImage(f, 0, 0, this);
    

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
    }


    //draw hp and pp bars
    //change rectangle based on each and draw
    public void drawStats(Graphics g){
	int hwidth = (int)(((double)p.getHP())/p.getMaxHP() * 96);
	int pwidth = (int)(((double)p.getPower())/p.getMaxP() * 96);

	if (hwidth > 96)
	    hwidth = 96;

	hp.setSize(hwidth, (int)hp.getHeight());
	pp.setSize(pwidth,(int)pp.getHeight());

	g.fillRect((int)hp.getX(), (int)hp.getY(), (int)hp.getWidth(), (int)hp.getHeight());
	g.fillRect((int)pp.getX(), (int)pp.getY(), (int)pp.getWidth(), (int)pp.getHeight());

	g.drawImage(hpbar, 0, 20, this);
	g.drawImage(ppbar, 0, 40, this);
    }


    /*------------------------------------------ Updating ----------------------------------------------*/

    public void actionPerformed(ActionEvent e){
	if (p.getHP() <= 0)
	    inGame = false;
        if (!inGame){
	    timer.stop();
	}

	if (currentFloor.cleared() && currentFloor.locked() && !currentFloor.keyMade() && level != 2){
	    currentFloor.genKey();
	    currentFloor.setKeyMade(true);
	}

	if (tilemap.getID().equals("Hall_8") && tilemap.empty()){
	    tilemap.getProps().remove(0);
	    tilemap.add(new MapObject("Door_open", 216, 34));	    
	}

	updateBoard();
	updatePlayer();
	updateMonsters();
	updateProjectiles();
	checkCollisions();

	repaint();
    }


    /*------------------------------------------ Update Board -----------------------------------------------*/

    //change tilemap as player moves to next area
    //check for moving out of bounds
    //or stepping on transfer point
    public void updateBoard(){
	int fx = currentFloor.getX();
	int fy = currentFloor.getY();
	TileMap tmp = tilemap;

	//if player moves off current map
	if (p.getX() < 0 ||  p.getX() + p.getWidth() >= width ||
	    p.getY() < 0 || p.getY() + p.getHeight() >= height){

	    if (p.getX() < 0){
		p.setX(480);
		currentFloor.setX(fx - 1);
	    }
	    else if (p.getX() + p.getWidth() >= width){
	        p.setX(0);
        	currentFloor.setX(fx + 1);
	    }
	    else if (p.getY() < 0){
	        p.setY(480);
		currentFloor.setY(fy - 1);
	    }
	    else if (p.getY() + p.getHeight() >= height){
		p.setY(0);
        	currentFloor.setY(fy + 1);
	    }
	    
	    tilemap = currentFloor.getCurrent();
	}
	//if player takes stairs/escalator/enters door/exits room
	else {
	    transfer();
	}
	if (!tmp.equals(tilemap)){
	    itemDrop.clear();
	    if (tilemap.getID().equals("Office")){
		itemDrop.add(new MapObject("Cellphone", 320, 128));
	    }
	    p.setMap(tilemap);
	    p.getProjectiles().clear();
	}	 
    }

    //if player steps on transfer point
    //move to next tilemap/floor
    public void transfer(){
	int fx = currentFloor.getX();
	int fy = currentFloor.getY();
	int pw = p.getWidth();
	int ph = p.getHeight();

	Tile topRight = tilemap.getTile((p.getX() + pw)/32 , p.getY()/32);
	Tile topLeft = tilemap.getTile(p.getX()/32, p.getY()/32);
	Tile bottomRight = tilemap.getTile((p.getX() + pw)/32, (p.getY() + ph)/32);
	Tile bottomLeft = tilemap.getTile(p.getX()/32, (p.getY() + ph)/32);


	if (p.getDY() == -1){
	    if (topLeft.has("Escalator") && topRight.has("Escalator")){
		checkLock();
		if (!currentFloor.locked()){
		    if (level == 3)
			level -= 1;
		    else 
			level -= 2;
		    currentFloor = floors[level];
		    currentFloor.descend();
		    p.setX(160);
		    p.setY(416);
		}
	    }
	    try {topLeft = tilemap.getTile(p.getX()/32, p.getY()/32 - 1);}
	    catch (IndexOutOfBoundsException e){}

	    if (topLeft.has("Door")){
		currentFloor.setY(fy - 1);
		p.setY(448);
	    }
	}
	if (p.getDX() == 1){
	    if (topRight.has("Stairs_D") || bottomRight.has("Stairs_D")){
		level--;
		currentFloor = floors[level];
		currentFloor.descend();
		p.setX(160);
		p.setY(416);
		p.setHP(p.getHP() - 5);
	    }
	    if (topRight.has("Exit_V")){
		currentFloor.setX(fx + 1);
		p.setX(33);
	    }
	}
	if (p.getDX() == -1){
	    if (topLeft.has("Stairs_U") || bottomLeft.has("Stairs_U")){
		level++;
		currentFloor = floors[level];
		currentFloor.ascend();
		p.setX(384);
		p.setY(384);
		p.setHP(p.getHP() - 5);
	    }
	    if (topLeft.has("Exit_V")){
		currentFloor.setX(fx - 1);
		p.setX(479);
	    }
	}
	if (p.getDY() == 1 && bottomLeft.has("Exit_H")){
	    currentFloor.setY(fy + 1);
	    p.setY(97);
	}
	tilemap = currentFloor.getCurrent();
    }


    public void checkLock(){
	if (currentFloor.locked() && p.hasKey()){
	    p.use(6);
	    currentFloor.unlock();
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
	        if (ob.getID().equals("Cellphone")){
		    gameWon = true;
		    inGame = false;
		    return;
		}
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

	    int tx = b.getX() / 32;
	    int ty = b.getY() / 32;
	    Tile t;

	    //if projectiles go out of bounds
	    //they disappear
	    if  (b.getX() < 0 || b.getX() + b.getWidth() > width ||
		 b.getY() < 0 || b.getY() + b.getHeight() > height){
		books.remove(i);
		removed = true;
	    }

	    //crash into walls
	    else {
		if (b.getDirection() == 'D')
		    ty = b.getY() / 32 + 1;   
		else if (b.getDirection() == 'R')
		    tx = b.getX() / 32 + 1;
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
	monsters = tilemap.getMonsters();
	int i = 0;
	while (i < monsters.size()){
	    Monster m = monsters.get(i);
	    m.resetP(p);

	    //if monster has no more hp
	    //drop item and remove from list
	    if (m.getHP() <= 0){
		if (!m.getItem().equals("None"))
		    itemDrop.add(new MapObject(m.getItem(), m.getX(), m.getY()));

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
	int i = 0;
	while (i < monsters.size()){
	    Monster m = monsters.get(i);
	    Rectangle mon = m.getBounds();

	    m.resetP(p);

	    //collision between player and monster
	    //player loses hp
	    if ( (Math.abs(p.getX()/32 - m.getX()/32)) < 0.5 &&
		 (Math.abs(p.getY()/32 - m.getY()/32)) < 0.5){
		m.repel();
		p.setHP(p.getHP() - m.getDamage());
	    }
	    //collision between projectile and monster
	    //if projectile collides, it disappears
	    //and monster loses hp

	    int j = 0;
	    while (j < books.size()){
		Rectangle proj = books.get(j).getBounds();
		if (mon.intersects(proj)){
		    m.setHP(m.getHP() - books.get(j).getDamage());
		    books.remove(j);
		    if (m.getID().equals("Ghost_")){
			Monster mini = new Monster("MiniMuk_", level, tilemap);
			mini.setX(m.getX());
			mini.setY(m.getY());
			tilemap.getMonsters().add(mini);
		    }
		    m.setIdle(false);
		}
		else 
		    j++;
	    }
	    i++;	  
	}
    }

    public static void main(String[] args){
	GamePanel g = new GamePanel();
    }

}
