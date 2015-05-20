import javax.swing.JFrame;
import java.awt.*;

public class Game extends JFrame{

    private GamePanel panel;
    private Container window;

    public Game(){
	setTitle("Game");
	setSize(500, 500);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);

	window = getContentPane();
	panel = new GamePanel();

	window.add(panel);
    }
    public static void main(String[] args){
	Game g = new Game();
	g.setVisible(true);
    }
}
