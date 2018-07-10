import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class C1_FileCopierVerNio {

	public static void main(String[] args) {
		Path src = Paths.get("src.txt");
		Path des = Paths.get("des.txt");
		
		//하나의 버퍼 생성
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//try 에서 두 개의 채널 생성
		try(FileChannel ifc = FileChannel.open(src,  StandardOpenOption.READ);
				FileChannel ofc = FileChannel.open(des, StandardOpenOption.WRITE, StandardOpenOption.CREATE))
		{
			int num;
			while(true)
			{
				num = ifc.read(buf);// 채널 ifc에서 버퍼로 읽어 들임
				if(num == -1) // 읽어 들인 데이터가 없다면
					break;
				
				buf.flip(); // 모드 변환!
				ofc.write(buf); // 버퍼에서 채널 ofc로 데이터 전송
				buf.clear(); // 버퍼 비우기
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
