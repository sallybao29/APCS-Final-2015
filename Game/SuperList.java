import java.util.LinkedList;
import java.awt.Graphics2D;

public class SuperList{
    private LinkedList<MapObject> things;
    private String id;

    public SuperList(){
	things = new LinkedList<MapObject>();
    }

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

    /*---------------------------- Initialization --------------------------*/

    public void makeObjects(){
	switch(id){
	    /*------------------------ Hall 1 -----------------------------*/
	case "Hall_1":
	    add(new MapObject("Stairs_D", 386, 368));
	    add(new MapObject("Locker_1", 32, 72));
	    add(new MapObject("Locker_1", 64, 72));

	    add(new MapObject("Display", 320, 64));
	    add(new MapObject("Display", 416, 64));

	    add(new MapObject("Bench_R", 32, 320));
	    add(new MapObject("Bench_R", 32, 384));
	    break;
	    /*------------------------ Hall 2 -----------------------------*/
	case "Hall_2":
	    add(new MapObject("Escalator", 416, 256));
	    break;
	    /*------------------------ Hall 3 -----------------------------*/
	case "Hall_3":
	    add(new MapObject("Vending_machine", 224, 64));
	    add(new MapObject("Vending_machine", 256, 64));

	    add(new MapObject("Locker_1", 480, 72));
	    add(new MapObject("Door_close", 342, 34));
	    break;
	    /*------------------------ Hall 4 -----------------------------*/
	case "Hall_4":
	    add(new MapObject("Locker_1", 0, 72));
	    add(new MapObject("Locker_1", 32, 72));
	    add(new MapObject("Locker_1", 64, 72));
	    add(new MapObject("Locker_1", 96, 72));

	    add(new MapObject("Locker_1", 288, 72));
	    add(new MapObject("Locker_1", 320, 72));
	    add(new MapObject("Locker_1", 352, 72));
	    add(new MapObject("Locker_1", 384, 72));
	    add(new MapObject("Locker_1", 416, 72));
	    break;
	    /*------------------------ Hall 5 -----------------------------*/
	case "Hall_5":
	    add(new MapObject("Locker_1", 96, 72));
	    add(new MapObject("Locker_1", 128, 72));
	    add(new MapObject("Locker_1", 160, 72));
	    add(new MapObject("Locker_1", 192, 72));
	    add(new MapObject("Locker_1", 224, 72));

	    add(new MapObject("Stairs_U", 96, 366));
	    break;
	    /*------------------------ Hall 6 -----------------------------*/
	case "Hall_6":
	    add(new MapObject("Door_2", 216, 34));
	    add(new MapObject("Display", 32, 64));
	    add(new MapObject("Display", 128, 64));
	    add(new MapObject("Display", 320, 64));
	    add(new MapObject("Display", 416, 64));

	    add(new MapObject("Fountain", 192, 224));
	    break;
	    /*------------------------ CS Class -----------------------------*/
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
	    /*------------------------ Chem Class ----------------------------*/
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
	    /*------------------------ Library 1 -----------------------------*/
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
	    /*------------------------ Library 2 -----------------------------*/
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

	    add(new MapObject("Seat_3", 72, 128));
	    add(new MapObject("Seat_3", 72, 192));
	    add(new MapObject("Seat_3", 72, 256));
	    add(new MapObject("Seat_3", 72, 320));
	    add(new MapObject("Seat_3", 72, 384));

	    add(new MapObject("Plant_4", 32, 416));
	    add(new MapObject("Plant_4", 448, 416));

	    add(new MapObject("Books", 128, 160));
	    add(new MapObject("Books", 288, 96));
	    break;
	    /*------------------------ Cafeteria -----------------------------*/
	case "Cafeteria":
	    add(new MapObject("Exit_H", 232, 464));

	    add(new MapObject("Window_1", 32, 32));
	    add(new MapObject("Window_1", 64, 32));
	    add(new MapObject("Window_1", 96, 32));
	    add(new MapObject("Window_1", 128, 32));
	    add(new MapObject("Window_1", 160, 32));
	    add(new MapObject("Window_1", 192, 32));
	    add(new MapObject("Window_1", 224, 32));
	    add(new MapObject("Window_1", 256, 32));
	    add(new MapObject("Window_1", 288, 32));
	    add(new MapObject("Window_1", 320, 32));
	    add(new MapObject("Window_1", 352, 32));
	    add(new MapObject("Window_1", 384, 32));

	    add(new MapObject("ATM", 32, 64));

	    add(new MapObject("Vending_machine", 96, 64));
	    add(new MapObject("Vending_machine", 128, 64));
	    add(new MapObject("Vending_machine", 160, 64));
	    add(new MapObject("Vending_machine", 192, 64));

	    add(new MapObject("Trashcan", 224, 96));
	    add(new MapObject("Trashcan", 240, 96));
	    add(new MapObject("Trashcan", 256, 96));

	    add(new MapObject("Counter_1", 386, 64));

	    add(new MapObject("Table_7", 64, 192));
	    add(new MapObject("Table_7", 224, 192));
	    add(new MapObject("Table_7", 64, 320));
	    add(new MapObject("Table_7", 224, 320));
	    add(new MapObject("Table_7", 384, 320));

	    add(new MapObject("Chair_R", 288, 96));
	    add(new MapObject("Chair_R", 320, 96));

	    add(new MapObject("Chair_R", 32, 194));
	    add(new MapObject("Chair_R", 32, 226));
	    add(new MapObject("Chair_L", 132, 194));
	    add(new MapObject("Chair_L", 132, 226));

	    add(new MapObject("Chair_R", 192, 226));
	    add(new MapObject("Chair_L", 292, 194));
	    add(new MapObject("Chair_L", 292, 226));

	    add(new MapObject("Chair_R", 32, 322));
	    add(new MapObject("Chair_R", 32, 354));
	    add(new MapObject("Chair_L", 132, 354));

	    add(new MapObject("Chair_R", 192, 322));
	    add(new MapObject("Chair_R", 192, 354));
	    add(new MapObject("Chair_L", 292, 322));
	    add(new MapObject("Chair_L", 292, 354));

	    add(new MapObject("Chair_R", 352, 322));
	    add(new MapObject("Chair_R", 352, 354));
	    add(new MapObject("Chair_L", 452, 322));
	    add(new MapObject("Chair_L", 452, 386));
	    add(new MapObject("Chair_L", 452, 418));

	    add(new MapObject("Plate", 256, 196));
	    add(new MapObject("Plate", 384, 96));
	    add(new MapObject("Plate", 384, 224));

	    add(new MapObject("Plate", 64, 320));
	    add(new MapObject("Plate", 224, 320));
	    add(new MapObject("Plate", 256, 320));
	    add(new MapObject("Plate", 338, 386));
	    break;
	}
    }

    public void draw(Graphics2D g){
        for (MapObject ob: things){
	    ob.draw(g);
	}
    }


}
