import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MultiServer6 {
	ArrayList<String> lst = new ArrayList();
	private String noThread = "00";
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	Map<String, String> rname = new HashMap<String, String>();
	
	
	//생성자
	public MultiServer6()
	{
		//클라이언트의 출력스트림을 저장할 해쉬맵 생성.
		clientMap = new HashMap<String, PrintWriter>();
		//해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
		Collections.synchronizedMap(rname);
	}
	
	public void init()
	{
		
		try
		{

			serverSocket = new ServerSocket(9999); //9999포트로 서버소켓 객체생성
	
			System.out.println("서버가 시작되었습니다.");
		
			while(true)
			{
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":"+socket.getPort());
			
				Thread msr = new MultiServerT(socket); //쓰레드 생성
				msr.start(); //쓰레드 시동
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				serverSocket.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
	
	//접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String msg,String name)
	{		

		try
		{
			Set<String> idset = new HashSet<>();
			Set<String> blockset = new HashSet<>();
			Set<String> cha = new HashSet<>();
			Set<String> wait = new HashSet<>();
			Connection con = ConnectionPool.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;		
			
//-------------------------------------------------------------------------------------------			
			sql = "select id from block where blocking = '"+ name +"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				blockset.add(rs.getString(1));
			}
			rs.close();
			pstmt.close();
			con.close();
//---------------------------------------------------------------------------------------------			
			Set<String> key = clientMap.keySet();
			Iterator<String> it = key.iterator();
			while(it.hasNext())
			{
				String id = (String)it.next();
				idset.add(id);
			}
			it = idset.iterator();
			while(it.hasNext())
			{
				String str = (String)it.next();
				if(!blockset.contains(str))
				{
					cha.add(str);
				}
			}
			Set<String> waitkey = rname.keySet();
			it = cha.iterator();
			while(it.hasNext())
			{
				String str = (String)it.next();
				if(!waitkey.contains(str))
				{
					wait.add(str);
				}
			}
			
			Iterator<String> it_2 = wait.iterator();

			while(it_2.hasNext())
			{	
				try
				{					
					PrintWriter it_out = (PrintWriter) clientMap.get(it_2.next());
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
				}
				catch(Exception e)
				{
					System.out.println("예외:"+e);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		//서버객체 생성
		MultiServer6 ms = new MultiServer6();
		ms.init();

	}
	
	////////////////////////////////////////////////////////////////
	//내부 클래스
	//클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드
	class MultiServerT extends Thread
	{
		private String noThread = "00";
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;
		
		
		Scanner s = new Scanner(System.in);
		 MultiServerT(int n)
		{
			if(n<10)
				noThread = "0" + n;
			else
				noThread = "" + n;
			
		}

		//생성자
		public MultiServerT(Socket socket)
		{
			this.socket = socket;
			try
			{
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));				
			}
			catch(Exception e)
			{
				System.out.println("예외:"+e);
			}
		}
		public void sendList() // 리스트
		{
			Set<String> a = clientMap.keySet();
			Iterator<String> itr = a.iterator();
			try
			{
				out.println(URLEncoder.encode("현재 접속중인 ID는 ", "UTF-8"));
			}
			catch(UnsupportedEncodingException e) {}
			while(itr.hasNext())
			{
				try {
				out.println(URLEncoder.encode(itr.next()+" ", "UTF-8"));
				}catch(UnsupportedEncodingException e) {}
			}
		}
		public void sendToMsg(String str1, String str2, String st)// 귓속말
		{
			try {
			PrintWriter out2 = (PrintWriter)clientMap.get(str1);
			out2.println(URLEncoder.encode(str2+"(귓속말)"+str1+" "+st, "UTF-8"));
			}catch(UnsupportedEncodingException e) {}
			
		}
		public void block(String msg, String name)//block
		{
			try {
			PrintWriter out2 = (PrintWriter)clientMap.get(name);
			out2.println(URLEncoder.encode(msg, "UTF-8"));
			}catch(UnsupportedEncodingException e) {}
		}
		public void noMsg(String str, String name) //개인 금칙어
		{
			try
			{
				int num1 = str.indexOf(" ");
				String st = str.substring(num1+1);
				if(st != null)
				{
					Connection con = ConnectionPool.getConnection();
					PreparedStatement pstmt = null;
					String sql = null;
					sql = "insert into nomsg(personal,id) values('" + st +"','"+ name + "')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					con.close();
				}
				else
				{
					try {
						out.println(URLEncoder.encode("금칙어를 입력하세요", "UTF-8"));
					} catch (UnsupportedEncodingException e) {}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		public String existName()// 중복 아이디 체크
		{
			String name = "";
			while(true)
			{			
				try
				{	
					int a = 0;
					
					name = blackList();
					Connection con = ConnectionPool.getConnection();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;	
					sql = "select id from member where id = '" + name +"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						if(rs.getString(1).equals(name))
						{
							try {
							out.println(URLEncoder.encode("중복입니다. 다시 입력하세요", "UTF-8"));	
							}catch(UnsupportedEncodingException e) {}
							a++;
							break;
						}
					}
					rs.close();
					pstmt.close();
					con.close();
					if(a == 0)
					{
						con = ConnectionPool.getConnection();
						sql = "insert into member(id) values('"+name+"')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close(); con.close();
						break;
					}
				}
				catch(SQLException e)
				{
					System.out.println("eeeeeee");
					e.printStackTrace();
				}	
			}
			return name;
		}
		public String blackList() //블랙리스트 처리
		{
			String name = "";

			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;	
				while(true)
				{	
//					int a = 0;
					name = in.readLine(); 
					name = URLDecoder.decode(name, "UTF-8");
					
					sql = "select blacklist from member where blacklist = '"+name+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						out.println(URLEncoder.encode(rs.getString(1)+ "는 블랙리스트입니다", "UTF-8"));
						out.println(URLEncoder.encode("나가세요", "UTF-8"));
						continue;
					}				
					rs.close();
					pstmt.close();
					con.close();
					break;
//					if(a == 0)
//					{
//						break;
//					}					
				}
//				rs.close();
//				pstmt.close();
//				con.close();
			}
			catch(Exception e)
			{
				System.out.println("error");
				e.printStackTrace();
			}
			return name;
			
		
		}
		public void exit(String name) // 종료
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				//ResultSet rs = null;
				String sql = null;	
				
				sql = "delete member where id = '"+name+"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
//				con.close();
//				con = ConnectionPool.getConnection();
				
				sql = "delete block where id = '"+name+"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				sql = "delete nomsg where id = '" + name + "'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();				
				con.close();
				
				roomExit(name);
				
			}
			catch(SQLException e)
			{
				System.out.println("예외1:"+e);
			}
			
		}
		public void every(String msg) // 공지사항
		{
			Iterator it = clientMap.keySet().iterator();
			
			while(it.hasNext())
			{
				try
				{
					PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
				}
				catch(Exception e)
				{
					System.out.println("예외:"+e);
				}
			}
		}
		public void room(String rname, String name) //방 생성
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				
				String sql = "alter table room add ("+rname+"     varchar2(20))";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				
				con = ConnectionPool.getConnection();
				sql = "insert into room(roomlist) values('" + rname + "')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				
				con = ConnectionPool.getConnection();
				sql = "insert into room("+rname+") values('" + name +"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("방 에러");
			}
		}
		public void roomExit(String name) // 방 탈출
		{
			int i = 0;
			try
			{
				String rn = rname.get(name);
				if(rn == null)
					return;
				
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "delete room where "+ rn + " = '" + name + "'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				
				out.println(URLEncoder.encode("방에서 나왔습니다", "UTF-8"));
				sendRMsg(name+"님이 방에서 나갔습니다.",name);
				
				sql = "select count("+rn+") from room";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					i = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				if(i == 0)
				{
					sql = "alter table room drop column "+ rn;
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
				}
				sql = "delete room where roomlist = '" + rn +"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void sendRMsg(String msg, String name) // 방 대화
		{
			String rn = rname.get(name);
			System.out.println(rn);
			ArrayList<String> lst = new ArrayList<String>();
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				String sql = "select " + rn + " from room";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					try
					{				
						PrintWriter out3 = (PrintWriter)clientMap.get(rs.getString(1));	
						out3.println(URLEncoder.encode(msg, "UTF-8"));
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
//					lst.add(rs.getString(1));	
				}
				rs.close();
				pstmt.close();
				con.close();
//				if(lst.toString() == null)
//				{
//					return;
//				}
//				Iterator<String> it = lst.iterator();
//				while(it.hasNext())
//				{
//					try
//					{				
//						PrintWriter out3 = (PrintWriter)clientMap.get(it.next());	
//						out3.println(URLEncoder.encode(msg, "UTF-8"));
//						
//					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//
//				}
				
			}
			catch(Exception e)
			{
				System.out.println("ddddd");
				e.printStackTrace();
			}
		}
		public void roomIn(String rname, String name) // 방 참여
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				
				String sql = "insert into room("+rname+") values('" + name +"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void rList()// 방 리스트
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select roomlist from room";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				out.println(URLEncoder.encode("현재 방 리스트입니다.", "UTF-8"));
				out.println("-------------------------------------------------------");
				while(rs.next())
				{
					out.println(URLEncoder.encode(rs.getString(1), "UTF-8"));
				}
				
				out.println("-------------------------------------------------------");
				rs.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//쓰레드를 사용하기 위해서 run()메서드 재정의

		@Override
		public void run()
		{			
			//String s = "";
			String name = ""; // 클라이언트로부터 받은 이름을 저장할 변수
			try
			{
				name = existName(); //중복 아이디 체크, 블랙리스트 체크
				sendAllMsg(name + "님이 입장하셨습니다.",name);
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;		
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				//현재 객체가 가지고있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림.
				clientMap.put(name, out); // 해쉬맵에 키를 name으로 출력스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size()+"명 입니다.");
				//입력스트림이 null이 아니면 반복
				String s = "";
				
//				Connection con = ConnectionPool.getConnection();
				
				while(in != null)
				{
					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");
					String yok = "";
					StringTokenizer str = new StringTokenizer(s," ");
					String st1 = str.nextToken();
					int num1 = s.indexOf(" ");
					String st = s.substring(num1+1);
					System.out.println(s);
					con = ConnectionPool.getConnection();
					pstmt = null;
					rs = null;
					sql = null;	
//					con = ConnectionPool.getConnection();
					ConnectionPool.listCacheInfos();
									
					sql = "select server from nomsg";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						yok = s.replace(rs.getString(1), "****");
						s = yok;
						sql = "select personal from nomsg where id = '"+name+"'";
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next())
						{
							yok = s.replace(rs.getString(1), "xxxx");
							s = yok;
						}
					}					
					rs.close(); pstmt.close(); con.close();
						
					System.out.println("nonononono");
					if(s.equals("q") || s.equals("Q")) //종료
					{
						break;
					}
					else if(st1.equals("/list")) //리스트
					{			
						sendList();
					}
					else if(st1.equals("/to"))// 귓속말
					{
						num1 = st.indexOf(" ");
						st = st.substring(num1);
						sendToMsg(str.nextToken(),name,st);
					}
					else if(st1.equals("/bk"))
					{
						con = ConnectionPool.getConnection();
						pstmt = null;
						rs = null;
						sql = null;
						
						sql = "insert into block values('"+name+"','"+st+"')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						sql = "select count(*) from block where id = '"+name+"' and blocking = '" + st +"'";
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next())
						{
							if(rs.getInt(1) == 2)
							{
								sql = "delete block where id = '" + name +"'";
								pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();
							}
						}
						block("/bk " + st,name);
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(con != null)con.close();
					}
					else if(st1.equals("/ng"))
					{
						noMsg(s,name);
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(con != null)con.close();
					}
					else if(st1.equals("/all"))
					{
						every(st);
					}
					else if(st1.equals("/room"))
					{

						room(st, name);
						rname.put(name, st);
						out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));
					}
					else if(st1.equals("/in"))
					{

						roomIn(st, name);
						rname.put(name, st);
						out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));
					}
					else if(st1.equals("/rlist"))
					{
						rList();
					}
					else if(st1.equals("/exit"))
					{
						roomExit(name);
						
						rname.remove(name);
					}
					else
					{
						if(rname.get(name) != null)
						{
							sendRMsg(name+" => "+s, name);
						}
						else
						{
							sendAllMsg(name+" => "+s,name);
						}
					}
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null)con.close();
				}
				
				
//				exit(name);
			}
			catch(Exception e)
			{
				System.out.println("예외1:"+e);
			}
			finally
			{
				
				//예외가 발생할때 퇴장. 해쉬맵에서 해당 데이터 제거.
				//보통 종료하거나 나가면 java.net.SocketException: 예외발생
				try
				{ // 강제 종료시 삭제

					exit(name);
					
					rname.remove(name);
					
//					Connection con = ConnectionPool.getConnection();
					clientMap.remove(name);
					sendAllMsg(name + "님이 퇴장하셨습니다.",name);
					System.out.println("현재 접속자 수는 " + clientMap.size()+ "명입니다");
					
				}
				catch(Exception e)
				{
					System.out.println("예외1:"+e);
				}
				
				
	
				try
				{
					in.close();
					out.close();
					socket.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
//				try
//				{
//					Connection con = ConnectionPool.getConnection();
//
//					PreparedStatement pstmt = null;
//	
//					String sql = null;
//		
//					ConnectionPool.listCacheInfos();
//					sql = "delete member where id = '"+name+"'";
//					pstmt = con.prepareStatement(sql);
//					pstmt.executeUpdate();
//
//					pstmt.close();
//					con.close();
//				}
//				catch(SQLException e)
//				{
//					System.out.println("예외1:"+e);
//				}
			}
		}
	}
}
