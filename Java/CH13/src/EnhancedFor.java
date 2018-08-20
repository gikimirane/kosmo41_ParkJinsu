
public class EnhancedFor {

	public static void main(String[] args) {
		String[] ar = {"java", "banana", "choco"};
		
		for(String e: ar)
		{
			System.out.print(e + " ");
		}
		System.out.println();
		
		int sum = 0;
		
		for(String e: ar)
		{
			sum += e.length();
		}
		System.out.println("sum: "+ sum);

	}

}
