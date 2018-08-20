class Circle2 {
	private double rad = 0;
	final double PI = 3.14;
	
	public Circle2(double r)
	{
		setRad(r);
	}
	public void setRad(double r) //데이터를 주는 것은 setter
	{
		if(r < 0)
		{
			rad = 0;
			return;
		}
		rad = r;
	}
	public double getRad()//데이터를 얻는 것은 getter
	{
		return rad;
	}
	
	public double getArea()
	{
		{
			return (rad * rad) * PI;
		}
	}
}	
public class InforHideCircle {

	public static void main(String[] args) {
		Circle2 c = new Circle2(1.5);
		System.out.println(c.getArea());
		
		c.setRad(2.5);
		System.out.println(c.getArea());
		c.setRad(-3.3);
		System.out.println(c.getArea());
		//c.rad = -4.5;//컴파일 오류 발생 안하지만 메소드를 통하지 않고 직접 대입시키면 결과에 문제가 발생
		System.out.println(c.getArea());

	}

}


	


