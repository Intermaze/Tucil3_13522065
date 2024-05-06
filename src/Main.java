import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main{
	private static HashSet<String> filterByLength(HashSet<String> dict, int length){
		HashSet<String> filter = new HashSet<String>();
		Iterator<String> wordItr = dict.iterator();
		while(wordItr.hasNext()){
			String nextWord = wordItr.next();
			if (nextWord.length() == length) filter.add(nextWord);
		}
		return filter; 
	}	

	public static void main(String[] args) {
		System.out.println("Loading dictionary.txt...");

		DictReader dict = new DictReader("dictionary.txt");
		Scanner in = new Scanner(System.in);
		String start, end;
		int algorithm;

		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		System.out.println("Welcome to word Ladder! ");

		System.out.print("Start word: ");
		start = in.nextLine();

		System.out.print("End word: ");
		end = in.nextLine();

		System.out.println("====== Algorithm List ======");
		System.out.println("1: UCS (Uniform Cost Search)");
		System.out.println("2: GBFS (Greedy Best First Search)");
		System.out.println("3: A* Search");
		System.out.println("============================");
		System.out.print("Algorithm to use: ");
		algorithm = in.nextInt();
	

		if (start.length() != end.length()){
			System.err.println("Start word letter count is not equal to end word");
		}
		else if (!dict.getDict().contains(start)){
			System.err.println("Start word is not found inside the dictionary");
		}
		else if (!dict.getDict().contains(end)){
			System.err.println("End word is not found inside the dictionary");
		}
		else{
			long startTime = System.currentTimeMillis();

			HashSet<String> filteredDict = filterByLength(dict.getDict(), start.length());
			WordQueue wq;
			
			if (algorithm == 1){
				wq = new WordQueue(filteredDict, new UCS(), start, end);
				System.out.println("Using UCS...");
			}
			else if (algorithm == 2){
				wq = new WordQueue(filteredDict, new GBFS(), start, end);
				System.out.println("Using GBFS...");
			}
			else{
				//Kalau input selain 1 dan 2, automatis dipilih algoritma A*
				wq = new WordQueue(filteredDict, new Astar(), start, end);
				System.out.println("Using A* Search...");
			}

			while (!wq.isDone()){
				wq.processNext();
			}

			if (wq.bufferIsEmpty()){
				System.out.println("Solution cannot be reached from start word.");
				wq.printVisitedOnly();
			}
			else{
				wq.printSolution();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Time taken:  " + (endTime - startTime) + "ms");
		}
		in.close();
	}
}

abstract class Algorithm implements Comparator<Node>{
	abstract int fn(Node n);

	public int compare(Node n1, Node n2){
		if (fn(n1) > fn(n2)) return 1;
		else if (fn(n1) < fn(n2)) return -1;
		else return 0;
	}
}

class UCS extends Algorithm{
	int fn(Node n){
		return n.g;
	}
}

class GBFS extends Algorithm{
	int fn(Node n){
		return n.h;
	}
}

class Astar extends Algorithm{
	int fn(Node n){
		return n.g + n.h;
	}
}

