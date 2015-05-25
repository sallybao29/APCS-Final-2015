import java.io.File;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.image.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 512;
    private static final int height = 512;
    private final int DELAY = 15;

    private Player p;
    private TileMap tilemap;
    private Monster m;
    private Timer timer;

  
    private class Key implements KeyListener {

	public void keyPressed(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_SPACE:
		p.attack();
		break;
	    case KeyEvent.VK_RIGHT:
		p.setDirection("right");
		p.setDX(1);
		break;
	    case KeyEvent.VK_LEFT:
		p.setDirection("left");
		p.setDX(-1);
		break;
	    case KeyEvent.VK_UP:
		p.setDirection("up");
		p.setDY(-1);
		break;
	    case KeyEvent.VK_DOWN:
		p.setDirection("down");
		p.setDY(1);
		break;
	    }
	}
	
	public void keyReleased(KeyEvent e) {
	    int c = e.getKeyCode();
	    switch (c){
	    case KeyEvent.VK_RIGHT:
		p.setDX(0);
		break;
	    case KeyEvent.VK_LEFT:
		p.setDX(0);
		break;
	    case KeyEvent.VK_UP:
		p.setDY(0);
		break;
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

	tilemap = new TileMap("hall_1", "WT_", "../Tileset/Floor_Tiles/Tile_5.png");

        p = new Player(tilemap);

	m = new Monster(tilemap);

	timer = new Timer(DELAY, this);
	timer.start();
    }
  
 
    public void paint(Graphics g){
	super.paint(g);

	//g.fillRect((int)p.getBounds().getX(), (int)p.getBounds().getY(), (int)p.getBounds().getWidth(), (int)p.getBounds().getHeight());

	Graphics2D im = (Graphics2D)g;
	tilemap.draw(im);
	p.draw(im);
	m.draw(im);

	LinkedList<Projectile> books = p.getProjectiles();

	for (Projectile p: books){
	    p.draw(im);
	}
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void updateProjectiles(){
	LinkedList<Projectile> books = p.getProjectiles();

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
	p.update();
	//m.update();
        (new Thread(new MRunnable(tilemap.getFile(),m,p))).start();
	updateProjectiles();
	repaint();

    }


}
