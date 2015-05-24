public class MRunnable implements Runnable{
    Player pl;
    Monster mon;
    String file;

    public MRunnable(String f, Monster m, Player p){
	file = f;
	mon = m;
	pl = p;
    }
    public void run(){
	mon.move(file, pl);
    }
}
