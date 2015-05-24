import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.image.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;



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

	tilemap = new TileMap("hall_1", "../Tileset/Wall_Tiles/WT_", "../Tileset/Floor_Tiles/Tile_5.png");

        p = new Player(tilemap);

	m = new Monster(tilemap);

	timer = new Timer(DELAY, this);
	timer.start();
    }
  
 
    public void paint(Graphics g){
	super.paint(g);

	Graphics2D im = (Graphics2D)g;
	tilemap.draw(im);
	p.draw(im);
		    
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }
  
  

    public void actionPerformed(ActionEvent e){
	p.update();
	repaint();

    }


}
