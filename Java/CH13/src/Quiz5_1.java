import java.lang.reflect.Array;
import java.util.Scanner;
public class Quiz5_1 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("단어를 입력해주세요");
		StringBuilder word = new StringBuilder(s.nextLine());
		StringBuilder word2 = word.reverse();
		System.out.println(word2);
		/*if(word2.equals(word)) 
		{
			System.out.println("회문입니다");
		}
		else
		{
			System.out.println("회문이 아닙니다");
		}
	*/
		
	}		
}
