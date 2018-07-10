import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class D1_SimpleWriter {

	public static void main(String[] args) {
		try(Writer out = new FileWriter("data.txt"))
		{
			out.write('A'); // 문자 'A' 저장
			out.write('한'); // 문자 '한' 저장
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
