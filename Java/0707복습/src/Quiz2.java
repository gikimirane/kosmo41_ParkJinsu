import java.util.*;

public class Quiz2 {

	public static void main(String[] args) {
		List<String> lst = Arrays.asList("G","o","o","d"," ","T","i","m","e");
		
		for(Iterator<String> itr = lst.iterator(); itr.hasNext();)
		{
			System.out.print(itr.next());
		}

	}

}
