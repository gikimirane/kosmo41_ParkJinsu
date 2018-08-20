class AccessWay
{
	static int num = 0;
	
	AccessWay() 
	{
		incrCnt();
	}
	void incrCnt()
	{
		num++;
	}
}
public class ClassVarAccess {

	public static void main(String[] args) {
		AccessWay way = new AccessWay();
		way.num++;//외부에서 인스턴스의 이름을 통한 접근
		System.out.println(way.num);
		AccessWay.num++;//외부에서 클래스의 이름을 통한 접근. 보통 이 방식을 써야한다.
		System.out.println("num = " + AccessWay.num);

	}

}
