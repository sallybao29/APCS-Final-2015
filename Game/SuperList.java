import java.util.LinkedList;

public class SuperList{
    private LinkedList<MapObject> things;
    String id;

    public SuperList(String s){
	id = s;
	things = new LinkedList<MapObject>();
	makeObjects();
    }

    public MapObject get(int i){
	return things.get(i);
    }

    public void add(MapObject o){
	things.add(o);
    }

    public int size(){
	return things.size();
    }


    public void makeObjects(){
	switch(id){
	case "Hall_1":
	    add(new MapObject("Stairs_D", 386, 368));
	    add(new MapObject("Locker_1", 32, 72));
	    add(new MapObject("Locker_1", 64, 72));
	    break;
	case "Hall_2":
	    add(new MapObject("Escalator", 384, 256));
	    break;
	case "Hall_5":
	    add(new MapObject("Locker_1", 96, 72));
	    add(new MapObject("Locker_1", 128, 72));
	    add(new MapObject("Locker_1", 160, 72));
	    add(new MapObject("Locker_1", 192, 72));
	    add(new MapObject("Locker_1", 224, 72));

	    add(new MapObject("Stairs_U", 96, 366));
	    break;
	case "Classroom_CS":
	    add(new MapObject("White_board", 96, 32));
	    add(new MapObject("White_board", 192, 32));
	    add(new MapObject("White_board", 288, 32));

	    add(new MapObject("Rug", 192, 116));
	    add(new MapObject("Table_1", 384, 128));

	    add(new MapObject("Computer_R", 32, 176));
	    add(new MapObject("Computer_R", 32, 240));
	    add(new MapObject("Computer_R", 32, 304));
	    add(new MapObject("Computer_R", 32, 368));

	    add(new MapObject("Computer_R", 160, 176));
	    add(new MapObject("Computer_R", 160, 240));
	    add(new MapObject("Computer_R", 160, 304));
	    add(new MapObject("Computer_R", 160, 368));

	    add(new MapObject("Computer_R", 352, 176));
	    add(new MapObject("Computer_R", 352, 240));
	    add(new MapObject("Computer_R", 352, 304));
	    add(new MapObject("Computer_R", 352, 368));

	    add(new MapObject("Computer_L", 128, 176));
	    add(new MapObject("Computer_L", 128, 240));
	    add(new MapObject("Computer_L", 128, 304));
	    add(new MapObject("Computer_L", 128, 368));

	    add(new MapObject("Computer_L", 320, 176));
	    add(new MapObject("Computer_L", 320, 240));
	    add(new MapObject("Computer_L", 320, 304));
	    add(new MapObject("Computer_L", 320, 368));

	    add(new MapObject("Computer_L", 448, 176));
	    add(new MapObject("Computer_L", 448, 240));
	    add(new MapObject("Computer_L", 448, 304));
	    add(new MapObject("Computer_L", 448, 368));

	    break;
	}
    }
}
