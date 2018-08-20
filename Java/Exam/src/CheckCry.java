interface Animal1
{
	void cry();
}

class Cat1 implements Animal1
{
	public void cry()
	{
		System.out.println("야옹~");
	}
}

class Dog implements Animal1
{
	public void cry()
	{
		System.out.println("멍멍!");
	}
}
public class CheckCry {

	public static void main(String[] args) {
		Animal1 cat = new Cat1();
		Animal1 dog = new Dog();
		
		checkAnimal(cat);
		checkAnimal(dog);
	}
	
	public static void checkAnimal(Animal1 animal)
	{
		
		
		if(animal instanceof Cat1)
		{
			System.out.println("고양이");
			animal.cry();
		}
		else if(animal instanceof Dog)
		{
			System.out.println("강아지");
			animal.cry();
		}


	}

}