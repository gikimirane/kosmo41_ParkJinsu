import java.lang.reflect.Array;
import java.util.Scanner;
public class Quiz5_1 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("단어를 입력해주세요");
		String word = s.next();

		String[] arr= new String[word.length()]; // 스트링을 담을 배열

		for(int i=0;i<arr.length;i++){ //스트링을 한글자씩 끊어 배열에 저장
			arr[i] = Character.toString(word.charAt(i));
		}
		int a = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(!(arr[i].equals(arr[arr.length-1-i])))
			
			{
				a++;
				System.out.println("회문이 아닙니다");
				break;
			}
			
		}
		if(a == 0)
		{
			System.out.println("회문입니다");
		}
		
	}		
}
