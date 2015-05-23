import java.util.*;

public class Frontier {
    public LinkedList<Node> l = new LinkedList<Node>();

    public void add(Node n){
	double p = n.getPriority();
	int i = 0;
        for(Node tmp : l){
	    if (p > tmp.getPriority()){
		i++;
	    }
	}
	l.add(i,n);
    }

    public Node remove() {
	return l.remove(0);
    }

    public boolean isEmpty(){
	return l.isEmpty();
    }

    public String toString(){
	String s = "";
	for (Node tmp : l){
	    s = s + tmp + " --> ";
	}
	return s + "null";
    }
}










