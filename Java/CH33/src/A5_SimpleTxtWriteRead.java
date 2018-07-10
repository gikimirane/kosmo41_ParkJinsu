import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class A5_SimpleTxtWriteRead {

	public static void main(String[] args) throws IOException{
		Path fp = Paths.get("D:\\pwlstn6144\\JavaStudy\\simple.txt");
		String st1 = "One Simple String";
		String st2 = "Two Simple String";
		List<String> lst1 = Arrays.asList(st1, st2);
		
		fp = Files.createFile(fp); //파일 생성, 파일이 존재하면 예외 발생
		
		Files.write(fp, lst1); // 파일에 문자열 저장하기
		List<String> lst2 = Files.readAllLines(fp); // 파일로부터 문자열 읽기
		System.out.println(lst2);

	}

}
