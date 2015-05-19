public class Monster implements Character{
    private String name;
    private int hp; // health

    public Monster(){
	hp = 100;
    }

    public Monster(String n, int level){
	name = n;
	hp = (10-level)*10 + 100;
    }

    public void move(){
	//to be cont.
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

}
