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
    private final int DELAY = 10;

    private boolean inGame;

    private Player p;
    private TileMap tilemap;
    private Monster m;
    private LinkedList<Monster> monsters;
    private LinkedList<Projectile> books;
    private Timer timer;
 
  
    private class Key implements KeyListener {

	public void keyPressed(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_A:
		p.attacking(true);
		break;
	    case KeyEvent.VK_RIGHT:
		p.setDirection("Right");
		p.setDX(1);
		break;
	    case KeyEvent.VK_LEFT:
		p.setDirection("Left");
		p.setDX(-1);
		break;
	    case KeyEvent.VK_UP:
		p.setDirection("Up");
		p.setDY(-1);
		break;
	    case KeyEvent.VK_DOWN:
		p.setDirection("Down");
		p.setDY(1);
		break;
	    }
	}
	
	public void keyReleased(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_A:
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
    

    public GamePanel(){
	super();
	addKeyListener(new Key());
	setPreferredSize(new Dimension(width, height));
	setFocusable(true);
	setDoubleBuffered(true);
	setVisible(true);

	inGame = true;

	tilemap = new TileMap("hall_1", "WT_", "../Tileset/Floor_Tiles/Tile_5.png");

        p = new Player(tilemap);

	m = new Monster(tilemap);

	makeMonsters();

	timer = new Timer(DELAY, this);
	timer.start();
    }

    public void makeMonsters(){
	monsters = new LinkedList<Monster>();
    }
  
    public void paint(Graphics g){
	super.paint(g);

	Graphics2D im = (Graphics2D)g;
	tilemap.draw(im);
	p.draw(im);
	m.draw(im);

        books = p.getProjectiles();

	for (Projectile p: books){
	    p.draw(im);
	}

	g.drawString("HP: " + p.getHP(), 128, 100);
	g.drawString("PP: " + p.getPower(), 128, 128);
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void updateProjectiles(){
        books = p.getProjectiles();

	for (int i = 0; i < books.size(); i++){
	    Projectile b = books.get(i);
	    if  (b.getX() < 0 || b.getX() > width ||
		 b.getY() < 0 || b.getY() > height){
		books.remove(i);
	    }
	    else {
		b.move();
	    }
	}
    }
  

    public void actionPerformed(ActionEvent e){
	inGame();
	p.update();

        (new Thread(new MRunnable(tilemap.getFile(),m,p))).start();
	updateProjectiles();

	checkCollisions();

	repaint();

    }

    public void inGame(){
	if (!inGame)
	    timer.stop();
    }

    public void checkCollisions(){

	//collision between player and monster
	Rectangle pl = p.getBounds();

	for (Monster monster: monsters){
	    Rectangle mon = monster.getBounds();

	    if (pl.intersects(mon)){
		p.setHP(p.getHP() - monster.getDamage());
	    }
	}

	//collision between projectile and monster

    }


}
