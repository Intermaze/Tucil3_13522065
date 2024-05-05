import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnglishDict {
	private List<String> dict;

	public EnglishDict(String filename){
		try{
			Scanner s = new Scanner(new File(filename));
			this.dict = new ArrayList<String>();
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

	public List<String> getDict(){
		return this.dict;
	}

}
