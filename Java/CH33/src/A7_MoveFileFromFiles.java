import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class A7_MoveFileFromFiles {

	public static void main(String[] args) throws IOException {
		Path src = Paths.get("D:\\pwlstn6144\\JavaStudy\\1.java");
		Path dst = Paths.get("D:\\pwlstn6144\\JavaStudy\\2.java");
		
		//src가 지시하는 디렉토리를 dst가 지시하는  디렉토리로 이동
		Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
		

	}

}
