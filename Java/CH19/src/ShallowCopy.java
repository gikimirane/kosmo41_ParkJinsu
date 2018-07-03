class Point1 implements Cloneable
{
	private int xPos;
	private int yPos;
	
	public Point1(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public void showPosition()
	{
		System.out.printf("[%d, %d]", xPos, yPos);
		System.out.println();
	}
	
	public void changePos(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
class Rectangle implements Cloneable
{
	public Point1 upperLeft;// 좌측상단 좌표
	public Point1 lowerRight;//우측 하단 좌표
	
	public Rectangle(int x1, int y1, int x2, int y2)
	{
		upperLeft = new Point1(x1, y1);
		lowerRight = new Point1(x2, y2);
	}
	// 좌표 정보를 수정함
	public void changePos(int x1, int y1, int x2, int y2)
	{
		upperLeft.changePos(x1, y1);
		lowerRight.changePos(x2, y2);
	}
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	//직사각형 좌표 정보 출력
	public void showPosition()
	{
		System.out.println("좌측 상단: ");
		upperLeft.showPosition();
		
		System.out.println("우측 하단: ");
		lowerRight.showPosition();
		System.out.println();
	}
	
}
public class ShallowCopy {

	public static void main(String[] args) {
		Rectangle org = new Rectangle(1,1,9,9);
		Rectangle cpy;
		
		try 
		{
			//인스턴스 복사
			cpy = (Rectangle)org.clone();
			
			//한 인스턴스의 좌표 정보를 수정
			org.changePos(2, 2, 7, 7);
			
			org.showPosition();
			cpy.showPosition();
			
			if(org.equals(cpy))
				System.out.println("aaaa");
			else
				System.out.println("bbbbb");
			
			if(org.lowerRight.equals(cpy.lowerRight))
				System.out.println("cccc");
			else
				System.out.println("dddd");
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}

	}

}
