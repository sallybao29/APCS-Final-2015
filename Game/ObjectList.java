import java.util.LinkedList;

public ObjectList{
    private LinkedList<MapObject> things;
    String id;

    public ObjectList(String s){
	id = s;
	things = new LinkedList<MapObject>();
	makeObjects();
    }

    public void makeObjects(){
	switch(id){
	case "Hall_1":
	    things.add(new MapObject("Stairs_D", 386, 368));
	    things.add(new MapObject("Locker_1", 32, 72));
	    things.add(new MapObject("Locker_1", 64, 72));
	case "Classroom_CS":
	    things.add(new MapObject("White_board", 96, 32));
	    things.add(new MapObject("White_board", 192, 32));
	    things.add(new MapObject("White_board", 288, 32));

	    things.add(new MapObject("Rug", 192, 116));
	    things.add(new MapObject("Table_1", 384, 128));

	    things.add(new MapObject("Computer_R", 32, 176));
	    things.add(new MapObject("Computer_R", 32, 240));
	    things.add(new MapObject("Computer_R", 32, 304));
	    things.add(new MapObject("Computer_R", 32, 368));

	    things.add(new MapObject("Computer_R", 160, 176));
	    things.add(new MapObject("Computer_R", 160, 240));
	    things.add(new MapObject("Computer_R", 160, 304));
	    things.add(new MapObject("Computer_R", 160, 368));

	    things.add(new MapObject("Computer_R", 352, 176));
	    things.add(new MapObject("Computer_R", 352, 240));
	    things.add(new MapObject("Computer_R", 352, 304));
	    things.add(new MapObject("Computer_R", 352, 368));

	    things.add(new MapObject("Computer_L", 128, 176));
	    things.add(new MapObject("Computer_L", 128, 240));
	    things.add(new MapObject("Computer_L", 128, 304));
	    things.add(new MapObject("Computer_L", 128, 368));

	    things.add(new MapObject("Computer_L", 320, 176));
	    things.add(new MapObject("Computer_L", 320, 240));
	    things.add(new MapObject("Computer_L", 320, 304));
	    things.add(new MapObject("Computer_L", 320, 368));

	    things.add(new MapObject("Computer_L", 448, 176));
	    things.add(new MapObject("Computer_L", 448, 240));
	    things.add(new MapObject("Computer_L", 448, 304));
	    things.add(new MapObject("Computer_L", 448, 368));

	    break;
	}
    }
}
