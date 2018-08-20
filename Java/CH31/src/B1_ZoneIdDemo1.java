import java.time.ZoneId;
//시간대 고려한 코드 작성을 위해서는 ZoneId 인스턴스 생성
public class B1_ZoneIdDemo1 {

	public static void main(String[] args) {
		ZoneId paris = ZoneId.of("Europe/Paris");
		System.out.println(paris); //파리의 시간대 정보를 반영한 ZonedId 생성

	}

}
