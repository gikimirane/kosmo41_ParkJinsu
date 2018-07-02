class ParentAdder
{
	public int add(int a, int b)
	{
		System.out.println("부모 add");
		return a + b;
	}
}

class ChildAdder extends ParentAdder
{

	public double add(double a, double b)
	{
		System.out.println("덧셈을 진행합니다");
		return a + b;
	}
}
public class OverrideMistake {

	public static void main(String[] args) {
		ParentAdder adder = new ChildAdder();
		System.out.println(adder.add(3, 4));
	}

}

//시그니처가 같다면 오버라이딩
//시그니처가 다르기 때문에 오버로딩이 됨
//@Override
//상위 클래스의 메서드를 오버라이딩 하는 걱싱 목적이라는 선언!
//오버라이딩을 하는 형태가 아니면 컴파일러가 오류 메시지 전달.
