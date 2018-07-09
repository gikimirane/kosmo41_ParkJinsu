interface Printable
{
	void print(String s); // 매개변수 하나, 반환형 void
}
public class A2_OneParamNoReturn {

	public static void main(String[] args) {
		Printable p;
		p = (String s) -> { System.out.println(s);};// 줄임 없는 표현
		p.print("Lambda exp one.");
		
		p = (String s) -> System.out.println(s); // 중괄호 생략
		p.print("Lambda exp two.");
		
		p = (s) -> System.out.println(s); // 매개변수 형 생략
		p.print("Lambda exp three.");
		
		p = s -> System.out.println(s); // 매개변수 소괄호 생략
		p.print("Lambda exp four.");

	}

}

/* 메소드 몸체가 둘 이상의 문장으로 이뤄져 있거나, 매개변수의 수가
 * 둘 이상인 경우에는 각각 중괄호와 소괄호의 생략이 불가능하다.
*/