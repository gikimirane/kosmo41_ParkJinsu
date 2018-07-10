import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D5_StringReader {

	public static void main(String[] args) {
		String ks = "공부에 있어서 돈이 꼭 필요한 것은 아니다";
		String es = "Life is long if you know how to use it.";
		
		try(BufferedReader br = new BufferedReader(new FileReader("src.txt")))
		{
			String str;
			while(true)
			{
				str = br.readLine(); // 한 문장 읽어 들이기
				if(str == null)
					break;
				System.out.println(str);
			}
		
 		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
