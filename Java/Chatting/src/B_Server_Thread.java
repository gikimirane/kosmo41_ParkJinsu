import java.io.*;
import java.net.*;

class B_Server_Thread extends Thread{
	B_Server mServer;
	Socket mSocket;

	PrintWriter pw;
	BufferedReader in;

	B_Server_Thread(B_Server _s, Socket _soc){
		mServer = _s;
		mSocket = _soc;

		try{
			pw = new PrintWriter(mSocket.getOutputStream(), true);
			in = new BufferedReader(
				new InputStreamReader(
				mSocket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//유저한테 메세지 받는거
	public void run(){
		String _str;
		try{
			while(true){
				_str = in.readLine();
				

				if(_str.equals("종료")){
					mServer.logOut(this);
					break;
				}else{
					mServer.sendBroadCast(_str);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void sendMessage(String _str){
		pw.println(_str);
	}
}