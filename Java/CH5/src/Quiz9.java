
public class Quiz9 {

	public static void main(String[] args) {
		int sum = 0;
		int a = 1;
		do
		{
			if((a % 2) == 0)
			{
				sum = sum + a;
				a++;
			}
			else
			{
				a++;
			   continue;
			}
		}while(a <= 100);
		System.out.println("1 ���� 100 ������ ¦������ ���� " + sum);

	}

}
