import java.util.Scanner;

public class Quiz5{

public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);	
		System.out.println("단어를 입력해주세요");
		String word = s.next();
		String word2 = new String();

		String[] array_word = new String[word.length()]; // 스트링을 담을 배열

		for(int i=array_word.length-1,j = 0;i>=0;i--,j++){ //스트링을 한글자씩 끊어 배열에 저장
			array_word[j] = Character.toString(word.charAt(i));
			word2 = word2 + array_word[j];
		}
		System.out.println(word2);
		 if (word.equals(word2) )
		    {
		        System.out.println("회문입니다");
		    }
		 else
		 {
			 System.out.println("회문이 아닙니다");
		 }

}
}