class Aaaa{ }
class Bbbb extends Aaaa{ }
public class ClassCastException {

	public static void main(String[] args) {
		Aaaa pbd1 = new Bbbb();
		Bbbb pbd2 = (Bbbb)pbd1;
		
		System.out.println(".. intermediate location ..");
		
		Aaaa ed1 = new Aaaa();
		Bbbb ed2 = (Bbbb)ed1;

	}

}
