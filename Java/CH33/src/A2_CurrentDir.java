import java.nio.file.Path;
import java.nio.file.Paths;

public class A2_CurrentDir {

	public static void main(String[] args) {
		Path cur = Paths.get("");
		String cdir;
		
		if(cur.isAbsolute())
		{
			System.out.println("1111");
			cdir = cur.toString();
		}
		else {
			System.out.println("222");
			cdir = cur.toAbsolutePath().toString();
		}
		
		System.out.println("Current dir: " + cdir);

	}

}
