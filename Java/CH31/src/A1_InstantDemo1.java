import java.time.Duration;
import java.time.Instant;

public class A1_InstantDemo1 {

	public static void main(String[] args) {
		Instant start = Instant.now(); // 현재 시각 정보를 담음
		System.out.println("시작: " + start.getEpochSecond());
		
	//반환 값은 '1970 - 01-01 00:00:00'을 기준으로 지나온 시간을 초단위로 계산한 결과
		
		System.out.println("Time flies like an arrow");
		
		Instant end = Instant.now();// 현재 시각 정보를 담음
		System.out.println("끝: " + end.getEpochSecond());
		
		Duration between = Duration.between(start, end); //두 시각의 차 계산
		
		System.out.println("밀리 초 단위 차: "+between.toMillis());

	}

}
// 이 예제를 기반으로 병렬 스트림과 순차 스트림의 성능 테스트 코드를 작성 할 수 있다