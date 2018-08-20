import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz07 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<String> lst = new ArrayList<>();
		lst.add("국어"); lst.add("영어"); lst.add("수학");
		int sum = 0;
		for(int i = 0; i < 3; i++)
		{
			System.out.println(lst.get(i)+"점수를 입력하세요");
		    int num1 = s.nextInt();
		    sum = sum + num1;
		}
		int avg = sum / lst.size();
		
		if(avg >= 90)
		{
			System.out.println("당신의 학점은 A");
		}
		else if(avg >= 80)
		{
			System.out.println("당신의 학점은 B");
		}
		else if(avg >= 70)
		{
			System.out.println("당신의 학점은 C");
		}
		else if(avg >= 50)
		{
			System.out.println("당신의 학점은 D");
		}
		else
		{
			System.out.println("당신의 학점은 F");
		}
		

	}

}
