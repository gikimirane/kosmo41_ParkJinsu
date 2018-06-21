
public class ComAssignOp {

	public static void main(String[] args) {
		short num = 10;
		num = (short)(num + 77L);//num과 77L을 더한 후 short로 데이터 형변환
		int rate = 3;
		rate = (int)(rate * 3.5);//rate와 3.5를 곱한 후 int로 형변환
		System.out.println(num);
		System.out.println(rate);
		
		num = 10; // num에 10을 대입
		num += 77L;//형 변환은 필요하지 않다
		rate = 3; // rate에 3을 대입
		rate *= 3.5;//형 변환은 필요하지 않다.
		System.out.println(num);
		System.out.println(rate);

	}

}
