/*
네트워크
TCP, UDP

소켓(Socket) 통신, Http 통신

Socket

ServerSocket

서버
1. 사용자 접속 대기
2. 사용자가 전달한 메시지를 수신해서 모든사용자에게 발신

*/
import java.io.*;
import java.net.*;

class A_Server{
	public static void main(String args[]){
		try{
			ServerSocket ser = new ServerSocket(7787);
			System.out.println("서버 응답대기");
			Socket _soc = ser.accept();
			System.out.println("클라이언트가 접속했음");
			
			while(true){
				BufferedReader in = 
					new BufferedReader(
					new InputStreamReader(_soc.getInputStream()));

				String str = in.readLine();
				System.out.println("여기는 서버 : "+str);

				if(str.equals("종료")){
					break;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}