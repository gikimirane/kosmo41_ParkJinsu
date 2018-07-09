import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class B4_DateTimeFormatterDemo {

	public static void main(String[] args) {
		ZonedDateTime date = ZonedDateTime.of(LocalDateTime.of(2019, 4, 25, 11, 20), ZoneId.of("Asia/Seoul"));
		
		//출력의 포맷 정보는 java.time.format.DateTimeFormatter 인스턴스에 담는다.
		DateTimeFormatter fm1 = DateTimeFormatter.ofPattern("yy-M-d");
		DateTimeFormatter fm2 = DateTimeFormatter.ofPattern("yyyy-MM-d, H:M:S");
		DateTimeFormatter fm3 = DateTimeFormatter.ofPattern("yyyy-MM-d, a HH:MM:SS VV");
		
		//LocalDatem, LocalTime, LocalDateTime, ZonedDateTime에 모두 존재하는 format 메소드 호출한다
		System.out.println(date.format(fm1));
		System.out.println(date.format(fm2));
		System.out.println(date.format(fm3));

	}

}
