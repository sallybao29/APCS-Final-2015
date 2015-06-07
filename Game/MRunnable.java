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
	AStar a = new AStar(file, mon);
	a.move(pl);
	a.nextStep();
    }
}
