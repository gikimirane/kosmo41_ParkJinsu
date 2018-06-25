class MyBook2
{
	int price;
	String title;
	
	MyBook2(String t, int p)
	{
		title = t;
		price = p;
	}
	
	MyBook2(MyBook2 copy)
	{
		title = copy.title;
		price = copy.price;
	}
	
	void print()
	{
		System.out.println("제     목 : " + title);
		System.out.println("가     격 : " + price);
	}
}
public class Books2 {

	public static void main(String[] args) {
		MyBook2 book1 = new MyBook2("게임스쿨", 10000);
		book1.print();
		
		MyBook2 book2 = new MyBook2(book1);
		book2.title = "모바일게임";
		book2.print();

	}

}
