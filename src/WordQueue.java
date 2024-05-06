import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class WordQueue {
	private HashMap<String, Node> graph;
	private PriorityQueue<Node> buffer;
	private Set<String> visited;
	private HashSet<String> dict;
	private String end;

	public WordQueue(HashSet<String> dict, Comparator<Node> alg, String start, String end){
		this.graph = new HashMap<String, Node>();
		this.buffer = new PriorityQueue<Node>(alg);
		this.visited = new HashSet<String>();
		this.dict = dict;
		this.end = end;
		
		int g,h;

		Iterator<String> wordItr = this.dict.iterator();
		
		while (wordItr.hasNext()){
			String nextWord = wordItr.next();
			g = 0;
			h = diffLetters(nextWord, end);

			Node node = new Node(nextWord, g, h, null);
			this.graph.put(node.word, node);
			
			if (node.word.equals(start)){
				this.buffer.add(node);
			}
		}

	}

	public void processNext(){
		Node curr = this.buffer.poll();
		curr.next = findNext(curr.word);
		for (String w : curr.next){
			if (!visited.contains(w)){
				Node n = getNodeInGraph(w);
				n.g = curr.g + 1;
				n.copyThread(curr.thread);
				n.pushThread(curr.word);
				buffer.add(n);
				visited.add(w);
			}
		}
	}

	public boolean isDone(){
		if (bufferIsEmpty()) return true;
		else{
			//Kalau tidak kosong, cek dulu kalau ketemu solusi
			if (buffer.peek().word.equals(end)){
				return true;
			}
			//Kalau belum ketemu, lanjut dengan return false
			return false;
		}
	}

	//Prekondisi: wq.isDone()
	public void printSolution(){
		Node solution = this.buffer.poll();
		int steps = solution.thread.size();
		
		while (!solution.thread.isEmpty()){
			System.out.print(solution.popThread() + " -> ");
		}
		System.out.println(solution.word);
		System.out.println("Visited count: " + visited.size());
		System.out.println("Steps count: " + steps);
	}

	public void printVisitedOnly(){
		System.out.println("Visited count: " + visited.size());
	}

	public boolean bufferIsEmpty(){
		return buffer.isEmpty();
	}

	//Prekondisi: panjang string a dan b sama
	private int diffLetters(String a, String b){
		int count = 0;
		for (int i=0; i<a.length(); i++){
			if (a.charAt(i) != b.charAt(i)){
				count++;
			}
		}
		return count;
	}

	private ArrayList<String> findNext(String word){
		ArrayList<String> next = new ArrayList<String>();
		String temp;
		for(int i=0; i<word.length(); i++){
			for (char aToz : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
				temp = word.substring(0, i) + aToz + word.substring(i+1);
				if (this.dict.contains(temp) && !temp.equals(word)){
					next.add(temp);
				}
			}
		}
		return next;
	}

	// Prekondisi: word pasti ada di dalam graph
	private Node getNodeInGraph(String word){
		return graph.get(word);
	}

}
