
public class Compare {
	boolean compare(int num1, int num2, int num3)
	{
		if(num1 > num2)
		{
			System.out.println(num2+" 는 제가 생각한 숫자보다 작습니다");
			System.out.println("[ "+num3+" ]"+"의 기회가 남았습니다");
			System.out.println();
			return false;
		}
		else if(num1 < num2)
		{
			System.out.println(num2+" 는 제가 생각한 숫자보다 큽니다");
			System.out.println("[ "+num3+" ]"+"의 기회가 남았습니다");
			System.out.println();
			return false;
		}
		else if(num1 == num2)
		{
			System.out.println(num2+" 는 정답입니다. 축하합니다!");
			return true;
		}
		return false;
	}

}
