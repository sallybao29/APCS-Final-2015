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
<<<<<<< HEAD
import javax.imageio.ImageIO;
=======
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.File;
>>>>>>> 3a7a04493429a1b85f267c88ca2a74885fa1ba32


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 400;
    private static final int height = 400;
    private final int DELAY = 5;

    private Player p;
    private Monster m;
    private Timer timer;


    private class TAdapter extends KeyAdapter{
	public void keyPressed(KeyEvent e){
	    p.keyPressed(e);
	}

	public void keyReleased(KeyEvent e){
	    p.keyReleased(e);
	}
    }

    public GamePanel(){
	super();
	init();
    }

    public void init(){
	addKeyListener(new TAdapter());
	setFocusable(true);
	setBackground(Color.BLACK);
	setDoubleBuffered(true);
	setVisible(true);

        p = new Player();

	m = new Monster("Jiji", 0, 10, 10);

	timer = new Timer(DELAY, this);
	timer.start();
    }

    public void paint(Graphics g){
	super.paint(g);

	//Testing out background
	BufferedImage bg = null;
	try{
	    bg = ImageIO.read(new File("../Sprites/Floors_1.jpg"));
	} catch (Exception e){}

	g.drawImage(bg, 0, 0, null);
    
	Graphics2D im = (Graphics2D)g;
	im.drawImage(p.getAnimation().getImage(), p.getX(), p.getY(), null);

	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }
    /*
    public void update(){
	p.update();
    }
    */
  
 
    public void actionPerformed(ActionEvent e){
        p.move();
	repaint();
    }


}
