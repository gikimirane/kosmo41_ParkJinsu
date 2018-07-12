import java.util.*;
public class Quiz1 {
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int num = 0; int sum = 0;
		List<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++)
		{
			System.out.println((i+1)+"번째 수를 입력하세요.");
			num = s.nextInt();
			lst.add(num);
		}
		Collections.sort(lst);
		Integer max = lst.get(4);	Integer min = lst.get(0);
		for(Iterator<Integer> itr = lst.iterator(); itr.hasNext();)
		{
			sum = sum + itr.next();
		}
		System.out.println("최대값 : "+ max);System.out.println("최소값 : " + min);
		System.out.println("합 : " + sum);
	}

}
