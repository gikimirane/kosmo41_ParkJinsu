class Apple1
{
	public String toString()
	{
		return "I am an apple.";
	}
}

class Orange1 
{
	public String toString()
	{
		return "I am an orange.";
	}
}

class AppleBox1
{
	private Apple1 ap;
	
	public void set(Apple1 a)
	{
		ap = a;
	}
	
	public Apple1 get()
	{
		return ap;
	}
}

class OrangeBox1 
{
	private Orange1 or;
	
	public void set(Orange1 o)
	{
		or = o;
	}
	
	public Orange1 get()
	{
		return or;
	}
}
public class A1_FruitAndBox1 {

	public static void main(String[] args) {
		// 과일 담는 박스 생성
		AppleBox1 aBox = new AppleBox1();
		OrangeBox1 oBox = new OrangeBox1();
		
		// 과일을 박스에 담는다
		aBox.set(new Apple1());
		oBox.set(new Orange1());
		
		//박스에서 과일을 꺼낸다
		Apple1 ap = aBox.get();
		Orange1 og = oBox.get();
		
		System.out.println(ap);
		System.out.println(og);

	}

}

//어쩔 수 없는 형 변환의 과정이 수반된다
//그리고 이는 컴파일러의 오류 발견 가능성을 낮추는 결과로 이어진다
