interface Calculate1
{
	int cal(int a, int b); // 값을 반환하는 추상 메소드
}
public class A4_TwoParamAndReturn {

	public static void main(String[] args) {
		Calculate1 c;
		c = (a, b) -> {return a + b;}; //return문의 중괄호는 생략 불가
		//int num = c.cal(4, 3); 이와같이 연산결과가 남는다.
		System.out.println(c.cal(4, 3));
		
		c = (a, b) -> a + b;
		System.out.println(c.cal(4, 3)); //연산결과가 남으면, 별도로 명시하지 않아도 반환 대상이 됨

	}

}
