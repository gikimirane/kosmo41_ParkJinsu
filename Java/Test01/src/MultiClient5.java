/*콘솔 멀티채팅 믈라이언트 프로그램*/
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

public class MultiClient5 {

	public static void main(String[] args) {
		
		System.out.println("이름을 입력하세요");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		try
		{
			//String ServerIP = "localhost";
			String ServerIP = args[0];
			Socket socket = new Socket(args.length == 0 ? ServerIP : args[0], 9999); //소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다....");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
			Thread receiver = new Receiver5(socket);
			receiver.start();
			
			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
			Thread sender = new Sender5(socket, s_name);
			sender.start();
			
		}
		catch(Exception e)
		{
			System.out.println("예외[MultiClient class:"+e);
		}

	}

}
