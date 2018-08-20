class Point implements Cloneable
{
	private int xPos;
	private int yPos;
	
	public Point(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public void showPosition() 
	{
		System.out.printf("[%d, %d]", xPos, yPos);
		System.out.println();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
public class D4_InstanceCloning {

	public static void main(String[] args) {
		Point org = new Point(3,5);
		Point cpy;
		
		try
		{
			cpy = (Point)org.clone();
			org.showPosition();
			cpy.showPosition();
			
			if(org.equals(cpy))
			{
				System.out.println("aaaa");
			}
			else
				System.out.println("bbbb");
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}

	}

}
