abstract class Human
{
	abstract void print();
}

class Man extends Human 
{

	public void print()
	{
		System.out.println("남자 생성1");
		System.out.println("남자 생성2");
	}
}

class Woman extends Human 
{

	public void print()
	{
		System.out.println("여자 생성1");
		System.out.println("여자 생성2");
		System.out.println("여자 생성3");
	}
}

class HumanFactory
{
	public static Human create(String str)
	{
		if (str == "남자")
		{
			return new Man();
		}
		else if ( str == "여자")
		{
			return new Woman();
		}
		return null;
	}
}
public class SimpleFactorPattern {

	public static void main(String[] args) {
		Human h1 = HumanFactory.create("남자");
		h1.print();
		
		Human h2 = HumanFactory.create("여자");
		h2.print();

	}

}
