import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class DictReader {
	private HashSet<String> dict;

	public DictReader(String filename){
		try{
			Scanner s = new Scanner(new File(filename));
			this.dict = new HashSet<String>();
			while (s.hasNext()){
				this.dict.add(s.next());
			}
			s.close();
		}
		catch(Exception e){
			System.out.println("File dictionary tidak ditemukan.");
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	public HashSet<String> getDict(){
		return this.dict;
	}

}
