import java.util.LinkedList;
import java.awt.Graphics2D;

public class Inventory extends SuperList{
    private int pointer;

    public Inventory(){
	super();
	pointer = 0;
	add(new Projectile("English_", 'U'));
	add(new Projectile("Physics_", 'U'));
	add(new Projectile("Precalc_", 'U'));
	add(new Projectile("History_", 'U'));
	    
	MapObject bagel = new MapObject("Bagel", 513, 0);
	MapObject coffee =  new MapObject("Coffee", 598, 0);
	bagel.changeQuantity(-1);
	coffee.changeQuantity(-1);

	add(bagel);
	add(coffee);
    }

    public void next(){
	pointer++;
	if (get(pointer).getQuantity() == 0)
	    pointer++;
    
	if (pointer > 3)
	    pointer = 0;
    }

    //takes String, MapObject with same id
    //and return entire object
    public MapObject find(String s){
	for (int i = 4; i < size(); i++){
	    if (get(i).getID().equals(s)){
		return get(i);
	    }
	}
	return null;
    }

    public String getCurrent(){
	return get(pointer).getID();
    }

    public void draw(Graphics2D g){
	//draw items in inventory
	for (int i = 4; i < size(); i++){
	    MapObject ob = get(i);
	    ob.draw(g);
	    String s = "" + ob.getQuantity();
	    g.drawString(s, ob.getX(), ob.getY() + 32);
	}
	//draw current projectile
        get(pointer).draw(g);
	String s = get(pointer).getID();

	g.drawString(s.substring(0, s.length() - 1), 20, 10);
    }

}
