public class Player implements Character{

    private int hp;
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Player(){
	hp = 100;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void move(){
	x += dx;
	y += dy;
    }

    public void die(){
	//dying animation
    }

    public void attack(){
	//two+ forms of attack
    }

    //move or attack input
    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	switch(key){
	case KeyEvent.VK_LEFT:
	    dx = -1;
	    break;
	case KeyEvent.VK_RIGHT:
	    dx  = 1;
	    break;
	case KeyEvent.VK_UP:
	    dy = 1;
	    break;
	case KeyEvent.VK_DOWN:
	    dy = -1;
	    break;
	}
    }

    //stop moving or stop attacking
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();
	switch(key){
	case KeyEvent.VK_LEFT:
	    dx = 0;
	    break;
	case KeyEvent.VK_RIGHT:
	    dx  = 0;
	    break;
	case KeyEvent.VK_UP:
	    dy = 0;
	    break;
	case KeyEvent.VK_DOWN:
	    dy = 0;
	    break;
	}
    }
}
