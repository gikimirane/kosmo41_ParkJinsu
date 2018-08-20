class BoxC<T>
{
	private T ob;
	
	public void set (T o)
	{
		ob = o;
	}
	public T get()
	{
		return ob;
	}
}

class BoxFactory
{
	public static <T> BoxC<T> makeBox(T o)
	{
		BoxC<T> box = new BoxC<T>();
		box.set(o);
		return box;
	}
}
public class C1_GenericMethodBoxMaker2 {

	public static void main(String[] args) {
		BoxC<String> sBox = BoxFactory.makeBox("Sweet");
		System.out.println(sBox.get());
		
		BoxC<Double> dBox = BoxFactory.makeBox(7.59);
		System.out.println(dBox.get());
	}

}
