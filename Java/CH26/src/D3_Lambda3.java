interface PrintableC
{
	void print(String s);
}
public class D3_Lambda3 {

	public static void main(String[] args) {
		PrintableC prn = (s) -> {System.out.println(s);};
		
		prn.print("what is Lambda");

	}

}
