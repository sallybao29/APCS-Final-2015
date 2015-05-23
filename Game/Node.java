public class Node {
    private int x,y,steps;
    private double priority;
    private Node prev, next;
		
    public Node(int x, int y){
	this.x = x;
	this.y = y;
    }

    public Node getPrev() {
	return prev;
    }

    public void setPrev(Node n){
	prev = n;
    }

    //
    public Node getNext(){
	return next;
    }
    public void setNext(Node n){
	next = n;
    }
    //

    public void setPriority(double p){
	priority = p;
    }

    public double getPriority(){
	return priority;
    }

    public void setSteps(int s){
	steps = s;
    }
    
    public int getSteps(){
	return steps;
    }
		
    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public String toString(){
	return "[" + x + "," + y + "]";
    }
		
}










