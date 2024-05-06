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
		EnglishDict dict = new EnglishDict("src/words-alpha.txt");
		Scanner in = new Scanner(System.in);
		String start, end;
		int algorithm;
		
		System.out.println("Welcome to word Ladder! ");

		System.out.print("Start word: ");
		start = in.nextLine();

		if (start.equals("debug")){
			start = "head";
			end = "teal";
			algorithm = 1;
		}
		else{
			System.out.print("End word: ");
			end = in.nextLine();
			System.out.println("Algorithm List");
			System.out.println("1: UCS (Uniform Cost Search)");
			System.out.println("2: G-BFS");
			System.out.println("3: A* Search");
			System.out.print("Algorithm to use: ");
			algorithm = in.nextInt();
		}

		if (start.length() != end.length()){
			System.err.println("Start word letter count is not equal to end word");
		}
		else if (!dict.getDict().contains(start)){
			System.err.println("Start word is not found inside words-alpha.txt");
		}
		else if (!dict.getDict().contains(end)){
			System.err.println("End word is not found inside words-alpha.txt");
		}
		else{
			long filterStartTime = System.currentTimeMillis();
			HashSet<String> filteredDict = filterByLength(dict.getDict(), start.length());
			long filterEndTime = System.currentTimeMillis();
			WordQueue wq;
			
			long constructorStartTime = System.currentTimeMillis();
			if (algorithm == 1){
				wq = new WordQueue(filteredDict, new UCS(), start, end);
			}
			else if (algorithm == 2){
				wq = new WordQueue(filteredDict, new GBFS(), start, end);
			}
			else{
				wq = new WordQueue(filteredDict, new Astar(), start, end);
			}
			long constructorEndTime = System.currentTimeMillis();

			long startTime = System.currentTimeMillis();

			while (!wq.isDone()){
				wq.processNext();
			}

			long endTime = System.currentTimeMillis();
	
			if (wq.bufferIsEmpty()){
				System.out.println("Buffer kosong sebelum ketemu solusi");
			}
			else{
				wq.printSolution();
				System.out.println("Time taken:  " + (endTime - startTime) + "ms");
				System.out.println("Filter time taken:  " + (filterEndTime - filterStartTime) + "ms");
				System.out.println("Constructor time taken:  " + (constructorEndTime - constructorStartTime) + "ms");
			}
	
		}

		in.close();
	}
}

class UCS implements Comparator<Node>{
	public int compare(Node n1, Node n2){
		if (n1.g > n2.g) return 1;
		else if (n1.g < n2.g) return -1;
		else return 0;
	}
}

class GBFS implements Comparator<Node>{
	public int compare(Node n1, Node n2){
		if (n1.h > n2.h) return 1;
		else if (n1.h < n2.h) return -1;
		else return 0;
	}
}

class Astar implements Comparator<Node>{
	public int compare(Node n1, Node n2){
		if (n1.g + n1.h > n2.g + n2.h) return 1;
		else if (n1.g + n1.h < n2.g + n2.h) return -1;
		else return 0;
	}
}
