
public class Tile extends MapObject{

    private boolean blocked;
    private String transferPoint;

    public Tile(String s, boolean b){
	super(s, ' ');
	blocked = b;
	transferPoint = "None";
    }

    public void loadImage(){
	setPath("../Tileset/Tiles/");
	super.loadImage();
    }

  
    public boolean isBlocked(){
	return blocked;
    }

    public String transferPoint(){
	return transferPoint;
    }

    public boolean has(String s){
	return transferPoint.contains(s);
    }

    public void setTransferPoint(String s){
	transferPoint = s;
    }

    public void setBlocked(boolean b){
	blocked = b;
    }

}
