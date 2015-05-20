import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 400;
    private static final int height = 400;
    private final int DELAY = 5;

    private Player p;
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

	timer = new Timer(DELAY, this);
	timer.start();
    }

    public void paint(Graphics g){
	super.paint(g);

	Graphics2D im = (Graphics2D)g;
	im.drawImage(p.getImage(), p.getX(), p.getY(), null);

	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }


    public void actionPerformed(ActionEvent e){
	p.move();
	repaint();
    }


}
