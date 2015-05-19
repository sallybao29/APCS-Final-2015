import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable{

    public static final int width = 400;
    public static final int height = 400;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private Player p;

    public GamePanel(){
	super();
	setFocusable(true);
	setPreferredSize(new Dimension(width, height));

	Player = new Player();
    }

}
