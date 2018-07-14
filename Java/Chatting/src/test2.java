import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class test2 {

	public static void main(String[] args) {
		String str = "/to aaa adfasdf asdf";
		StringTokenizer bbb = new StringTokenizer(str," ");
		List<String> lst = new ArrayList<String>();
		while(bbb.hasMoreTokens())
		{
			lst.add(bbb.nextToken());
		}
		System.out.println(lst.get(1));


	}

}
