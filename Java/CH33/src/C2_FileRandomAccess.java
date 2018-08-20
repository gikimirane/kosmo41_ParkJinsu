import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class C2_FileRandomAccess {

	public static void main(String[] args) {
		Path fp = Paths.get("data.dat");
		ByteBuffer wb = ByteBuffer.allocate(1024); // 버퍼 생성
		wb.putInt(120); // int형 데이터 버퍼에 저장
		wb.putInt(240);
		wb.putDouble(0.94); // double형 데이터 버퍼에 저장
		wb.putDouble(0.75);
		
		// 하나의 채널 생성
		try(FileChannel fc = FileChannel.open(fp, StandardOpenOption.CREATE,
				StandardOpenOption.READ, StandardOpenOption.WRITE))
		{
			//파일에 쓰기
			wb.flip(); //버퍼의 맨 처음으로 이동
			fc.write(wb);
			
			//파일로부터 읽기
			ByteBuffer rb = ByteBuffer.allocate(1024); //버퍼 생성
			fc.position(0); // 채널의 펴지션을 맨 앞으로 이동
			fc.read(rb); // 모든 데이터 다 들어옴
			
			// 이하 버퍼로부터 데이터 읽기
			rb.flip();
			System.out.println(rb.getInt());
			rb.position(Integer.BYTES * 2); // 버퍼의 포지션 이동
			System.out.println(rb.getDouble());
			System.out.println(rb.getDouble());
			
			rb.position(Integer.BYTES); // 버퍼의 포지션 이동
			System.out.println(rb.getInt());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		

	}

}
// Integer.BYTE : int형 정수의 크기에 대한 상수(=4)