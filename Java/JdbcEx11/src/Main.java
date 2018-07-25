import java.sql.Connection;
public class Main {

	public static void main(String[] args) throws InterruptedException {
		for(int i=0; i<100; i++)
		{
			TestThread test = new TestThread(i);
			test.start();
		}

	}

}
