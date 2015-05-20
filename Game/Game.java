import javax.swing.JFrame;

public class Game extends JFrame{

    public Game(){
	super();
	initUI();
    }

    private void initUI(){
	add(new GamePanel());
	setSize(450, 450);

	setTitle("Game");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
    }

    public static void main(String[] args){
	/*
	EventQueue.invokeLater(new Runnable(){
	    public void run(){
		Game g = new Game();
	    }
	    });
	*/
	Game g = new Game();
	g.setVisible(true);
    }
}
