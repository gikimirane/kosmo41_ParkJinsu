package Star;

public class Medic implements Unit {
	
	public Medic() {
		System.out.println("메딕 생산!!");
	}

	@Override
	public void move() {
		System.out.println("메딕 이동!!");

	}

}
