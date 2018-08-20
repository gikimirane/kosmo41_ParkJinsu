package ZOO;
// Tiger는 default로 선언되었으므로 동일 패키지 내에서만 인스턴스 생성가능
class Tiger
{
	//빈 클래스
}
//MyTiger는 public으로 선언되었으므로 어디서든 인스턴스 생성가능
public class MyTiger {
	
	public void makeTiger1() {
		//Tiger와 같은 패키지 안에 있으니 Tiger인스턴스 생성가능
		Tiger hodol = new Tiger();

	}

}
