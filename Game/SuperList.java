import java.util.LinkedList;
import java.awt.Graphics2D;

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
	    add(new MapObject("Escalator", 416, 256));
	    break;
	case "Hall_5":
	    add(new MapObject("Locker_1", 96, 72));
	    add(new MapObject("Locker_1", 128, 72));
	    add(new MapObject("Locker_1", 160, 72));
	    add(new MapObject("Locker_1", 192, 72));
	    add(new MapObject("Locker_1", 224, 72));

	    add(new MapObject("Stairs_U", 96, 366));
	    break;
	case "CS_Class":
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
	case "Chem_Class":
	    add(new MapObject("Window_2", 32, 32));
	    add(new MapObject("Window_2", 384, 32));

	    add(new MapObject("Black_board", 160, 32));
	    add(new MapObject("Black_board", 256, 32));

	    add(new MapObject("Desk_5", 128, 114));
	    add(new MapObject("Desk_4", 256, 108));

	    add(new MapObject("Desk_4", 32, 186));
	    add(new MapObject("Desk_3", 32, 288));
	    add(new MapObject("Desk_3", 32, 384));
	    add(new MapObject("Desk_5", 352, 192));
	    add(new MapObject("Desk_4", 352, 282));
	    add(new MapObject("Desk_3", 352, 384));

	    add(new MapObject("Seat_1", 32, 224));
	    add(new MapObject("Seat_1", 64, 224));
	    add(new MapObject("Seat_1", 128, 224));
	    
	    add(new MapObject("Seat_1", 64, 320));

	    add(new MapObject("Seat_1", 32, 416));
	    add(new MapObject("Seat_1", 96, 416));

	    add(new MapObject("Seat_1", 352, 224));
	    add(new MapObject("Seat_1", 384, 224));
	    add(new MapObject("Seat_1", 448, 224));

	    add(new MapObject("Seat_1", 384, 320));
	    add(new MapObject("Seat_1", 448, 320));

	    add(new MapObject("Seat_1", 384, 416));
	    add(new MapObject("Seat_1", 416, 416));
	    break;
	case "Library_1":
	    add(new MapObject("Bookshelf", 32, 64));
	    add(new MapObject("Bookshelf", 96, 64));
	    add(new MapObject("Bookshelf", 160, 64));
	    add(new MapObject("Bookshelf", 224, 64));
	    add(new MapObject("Bookshelf", 288, 64));
	    add(new MapObject("Bookshelf", 352, 64));

	    add(new MapObject("Picture", 420, 102));

	    add(new MapObject("Couch_R", 32, 144));
	    add(new MapObject("Couch_R", 32, 240));
	    add(new MapObject("Couch_R", 32, 336));

	    add(new MapObject("Table_3", 96, 152));
	    add(new MapObject("Table_3", 96, 248));
	    add(new MapObject("Table_3", 96, 344));

	    add(new MapObject("Counter_2", 320, 338));
	    add(new MapObject("Phone", 448, 352));

	    add(new MapObject("Notice", 420, 332));

	    add(new MapObject("Books", 352, 320));
	    add(new MapObject("Books", 384, 320));
	    add(new MapObject("Books", 100, 160));

	    add(new MapObject("Book", 128, 160));
	    add(new MapObject("Book", 128, 256));
	    add(new MapObject("Book", 320, 384));
	    break;
	case "Library_2":
	    add(new MapObject("Bookshelf", 288, 160));
	    add(new MapObject("Bookshelf", 352, 160));
	    add(new MapObject("Bookshelf", 416, 160));

	    add(new MapObject("Bookshelf", 288, 256));
	    add(new MapObject("Bookshelf", 352, 256));
	    add(new MapObject("Bookshelf", 416, 256));

	    add(new MapObject("Bookshelf", 288, 352));
	    add(new MapObject("Bookshelf", 352, 352));
	    add(new MapObject("Bookshelf", 416, 352));

	    add(new MapObject("Computer_R", 32, 96));
	    add(new MapObject("Computer_R", 32, 160));
	    add(new MapObject("Computer_R", 32, 224));
	    add(new MapObject("Computer_R", 32, 288));
	    add(new MapObject("Computer_R", 32, 352));

	    add(new MapObject("Seat_3", 104, 128));
	    add(new MapObject("Seat_3", 104, 192));
	    add(new MapObject("Seat_3", 104, 256));
	    add(new MapObject("Seat_3", 104, 320));
	    add(new MapObject("Seat_3", 104, 384));

	    add(new MapObject("Plant_4", 32, 416));
	    add(new MapObject("Plant_4", 448, 416));

	    add(new MapObject("Books", 128, 160));
	    add(new MapObject("Books", 288, 96));
	    break;
	}
    }

    public void draw(Graphics2D g){
        for (MapObject ob: things){
	    ob.draw(g);
	}
    }
}
