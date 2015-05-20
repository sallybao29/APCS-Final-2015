import javax.swing.JFrame;

public class Game{
    public static void main(String[] args){
	JFrame window = new JFrame("Game");
	window.setSize(500, 500);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.add(new GamePanel());
	window.setVisible(true);
	window.setResizable(false);
	window.setLocationRelativeTo(null);
    }
}
