
public class C1_Varargs {
	public static void showAll(String... varg)
	{
		System.out.println("LEN: " + varg.length);
		
		for(String s : varg)
			System.out.println(s + '\t');
		System.out.println();
	}

	public static void main(String[] args) {
		showAll("Box");
		showAll("Box","Toy");
		showAll("Box","Toy","Apple");

	}

}
