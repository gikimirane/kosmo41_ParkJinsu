/*
��Ʈ��ũ
TCP, UDP

����(Socket) ���, Http ���

Socket

ServerSocket

����
1. ����� ���� ���
2. ����ڰ� ������ �޽����� �����ؼ� ������ڿ��� �߽�

*/
import java.io.*;
import java.net.*;

class A_Server{
	public static void main(String args[]){
		try{
			ServerSocket ser = new ServerSocket(7787);
			System.out.println("���� ������");
			Socket _soc = ser.accept();
			System.out.println("Ŭ���̾�Ʈ�� ��������");
			
			while(true){
				BufferedReader in = 
					new BufferedReader(
					new InputStreamReader(_soc.getInputStream()));

				String str = in.readLine();
				System.out.println("����� ���� : "+str);

				if(str.equals("����")){
					break;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}