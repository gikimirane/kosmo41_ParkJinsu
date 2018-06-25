
public class ScopeBasic {

	public static void main(String[] args) {
		boolean ste = true;
		int num1 = 11;
		
		if(ste)
		{
			//int num1 = 22; //안에 있는 지역변수는 밖에 있는 변수와 겹쳐서는 안된다. 
			num1++;
			System.out.println(num1);
		}
		{
			int num2 = 33;
			num2++;
			System.out.println(num2);
		}
		
		//System.out.println(num2); //안에 있는 지역변수는 밖에 있는 변수와 겹쳐서는 안된다. 

	}

}
