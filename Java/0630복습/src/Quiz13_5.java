import java.util.Scanner;

public class Quiz13_5 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("단어를 입력해주세요");
		String word = s.next();

		String[] array_word = new String[word.length()]; // 스트링을 담을 배열
		String[] arr = new String[array_word.length];
		for(int i=0;i<array_word.length;i++){ //스트링을 한글자씩 끊어 배열에 저장
			array_word[i] = Character.toString(word.charAt(i));
			arr[array_word.length-1-i] = array_word[i];
		}
		int a = 0;
		for(int i = 0; i < array_word.length;i++)
		{
			if(!(array_word[i].equals(arr[i])))
			{
				a++;
				System.out.println("회문이 아닙니다.");
				break;
			}
		}
		if(a==0)
		{
			System.out.println("회문입니다");
		}
		

	}
}