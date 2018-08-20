import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class C1_DataFilterOutputStream {

	public static void main(String[] args) {
		try(DataOutputStream out = new DataOutputStream (new FileOutputStream("data.dat")))
		{
			out.writeInt(370); // int형 데이터 저장
			out.writeDouble(3.14); // double형 데이터 저장
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
