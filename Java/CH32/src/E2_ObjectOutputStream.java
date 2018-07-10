import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class E2_ObjectOutputStream {

	public static void main(String[] args) {
		E1_SBox box1 = new E1_SBox("Robot");
		E1_SBox box2 = new E1_SBox("Strawberry");
		
		try(ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("Object.bin")))
		{
			oo.writeObject(box1); // box1이 참조하는 인스턴스 저장
			oo.writeObject(box2); // box2가 참조하는 인스턴스 저장
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
