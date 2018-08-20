interface Calculate
{
	void cal(int a, int b); // 매개변수 둘, 반환형 void
}
public class A3_TwoParamNoReturn {

	public static void main(String[] args) {
		Calculate c;
		c = (a, b) -> System.out.println(a + b);//연산 결과가 안남는다
		c.cal(4, 3);
		
		c = (a, b) -> System.out.println(a - b);
		c.cal(4, 3);
		
		c = (a, b) -> System.out.println(a * b);
		c.cal(4, 3); 
		

	}

}
