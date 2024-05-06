import java.util.LinkedList;
import java.util.Queue;

public class Node {
	public String word;
	public int g, h;
	public Queue<String> thread;

	public Node(String word, int g, int h){
		this.word = word;
		this.g = g;
		this.h = h;
		this.thread = new LinkedList<String>();
	}

	public void pushThread(String w){
		thread.add(w);
	}

	public void copyThread(Queue<String> newThread){
		this.thread = new LinkedList<String>(newThread);
	}

	public String popThread(){
		return thread.remove();
	}

}
