import java.util.Scanner;
public class Quiz11 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int multi = 1;
		
		System.out.println("������ �Է��Ͻÿ�");
		int num = scan.nextInt();
		while(num > 0)
		{
			multi = multi * num;
			System.out.print(num);
			if(num > 1)
			{
				System.out.print(" X ");
			}
			
			num--;
		}
		System.out.println(" = " + multi);

	}

}
