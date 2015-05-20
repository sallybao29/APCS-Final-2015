import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener{

    private static final int width = 400;
    private static final int height = 400;
    private final int DELAY = 5;

    private Player p;
    private Timer timer;

    public GamePanel(){
	super();
	addKeyListener(new TAdapter());
	setFocusable(true);
	setPreferredSize(new Dimension(width, height));

        p = new Player();

	timer = new Timer(DELAY, this);
	timer.start();
    }

    public void paint(Graphics g){
	super.paint(g);

	Graphics2D im = (Graphics2D)g;
	im.drawImage(p.getImage(), p.getX(), p.getY(), this);

	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }


    public void actionPerformed(ActionEvent e){
	p.move();
	repaint();
    }


    private class TAdapter extends KeyAdapter{
	public void keyPressed(KeyEvent e){
	    p.keyPressed(e);
	}

	public void keyReleased(KeyEvent e){
	    p.keyReleased(e);
	}
    }

}
