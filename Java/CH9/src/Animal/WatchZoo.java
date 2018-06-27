package Animal;

public class WatchZoo {

	public void makeTiger2() {
		//Cat은 public으로 선언되었으므로 어디서든 인스턴스 생성가능
		ZOO.MyTiger hosum = new ZOO.MyTiger();
		
		}
	public void makeTiger3()
	{
		//Tiger는 default로 선언되었으므로 이 위치에서 인스턴스 생성불가
		//ZOO.Tiger hosum = new ZOO.Tiger();//컴파일 오류 발생
	}

}
