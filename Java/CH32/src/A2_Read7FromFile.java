import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A2_Read7FromFile {

	public static void main(String[] args) throws IOException {
		try {
			InputStream in = new FileInputStream("data.dat"); // 입력 스트림 생성
			int dat = in.read(); // 데이터 읽음
			in.close(); // 입력 스트림 종료sysodat
			System.out.println(dat);
			System.out.printf("%c", dat);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
