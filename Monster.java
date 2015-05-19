public class Monster implements Character{
    private String name;
    private int hp; // health
    private int xcor, ycor; // location

    public Monster(){
	name = "Generic";
	hp = 100;
	xcor = 0;
	ycor = 0;
    }

    public Monster(String n, int level, int x, int y){
	name = n;
	hp = (10-level)*10 + 100;
	xcor = x;
	ycor = y;
    }

    public void move(int x, int y){
	//to be cont.
        xcor = x;
	ycor = y;
    }
   
    public void attack(){
	System.out.println("You've been caught!");
    }

    public void die(){
	//to be cont.
    }

    public String getName(){
	return name;
    }

    public void setHP(int h){
	hp = h;
    }

    public int getHP(){
	return hp;
    }

    public int getX(){
	return xcor;
    }
   
    public int getY(){
	return ycor;
    }

}
