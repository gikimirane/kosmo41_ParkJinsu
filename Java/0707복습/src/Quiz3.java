import java.util.*;

public class Quiz3 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<Integer> lst = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			System.out.println("정수를 입력하세요");
			int num = s.nextInt();
			lst.add(num);
		}
		System.out.print("짝수 : ");
		for(Iterator<Integer> itr = lst.iterator(); itr.hasNext();)
		{
			int num2 = itr.next();
			if(num2 % 2 == 0)
			{
				System.out.print(num2+" ");
			}
		}
		System.out.println();
		System.out.print("홀수 : ");
		for(Iterator<Integer> itr = lst.iterator(); itr.hasNext();)
		{
			int num2 = itr.next();
			if(num2 % 2 == 1)
			{
				System.out.print(num2 + " ");
			}
		}
		

	}

}
