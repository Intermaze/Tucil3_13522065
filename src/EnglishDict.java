import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class EnglishDict {
	private HashSet<String> dict;

	public EnglishDict(String filename){
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
		}
	}

	public HashSet<String> getDict(){
		return this.dict;
	}

}
