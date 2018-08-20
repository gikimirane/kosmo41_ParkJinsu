import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class A6_CopyFileFromFiles {

	public static void main(String[] args) throws IOException{
		Path src = Paths.get("D:\\pwlstn6144\\JavaStudy\\2.java");
		Path dst = Paths.get("D:\\pwlstn6144\\JavaStudy\\1.java");
		
		//src가 지시하는 파일을 dst가 지시하는 위치와 이름으로 복사
		Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
		

	}

}
