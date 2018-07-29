import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
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

	
	
	//생성자
	public MultiServer6()
	{
		//클라이언트의 출력스트림을 저장할 해쉬맵 생성.
		clientMap = new HashMap<String, PrintWriter>();
		//해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
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
	public void sendAllMsg(String msg,String name, String sp)
	{				
		Set<String> idset = new HashSet<>();
		Set<String> blockset = new HashSet<>();
		Set<String> cha = new HashSet<>();
		try
		{
			Connection con = ConnectionPool.getConnection("env " +noThread);
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			ConnectionPool.listCacheInfos();
			
			sql = "select id from block where blocking = '"+ name +"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				blockset.add(rs.getString(1));
			}
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

			Iterator<String> it_2 = cha.iterator();

			while(it_2.hasNext())
			{	
				try
				{					
					PrintWriter it_out = (PrintWriter) clientMap.get(it_2.next());
					it_out.println(msg);
				}
				catch(Exception e)
				{
					System.out.println("예외:"+e);
				}
			}
			

			rs.close();
			pstmt.close();
			con.close();
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
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));				
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
			out.println("현재 접속중인 ID는 ");
			while(itr.hasNext())
			{
				out.println(itr.next()+" ");
			}
		}
		public void sendToMsg(String str1, String str2, String st)// 귓속말
		{
			
			PrintWriter out2 = (PrintWriter)clientMap.get(str1);
			out2.println(str2+"(귓속말)"+str1+" "+st);
		}
		public void block(String msg, String name)
		{
			PrintWriter out2 = (PrintWriter)clientMap.get(name);
			out2.println(msg);
		}
		public void noMsg(String str, String name, Connection con, PreparedStatement pstmt, ResultSet rs)
		{
			try
			{
				int num1 = str.indexOf(" ");
				String st = str.substring(num1+1);
				String sql = "insert into nomsg(personer,id) values('" + st +"','"+ name + "')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		public String existName(Connection con, PreparedStatement pstmt, ResultSet rs, String sql)// 중복 아이디 체크
		{
			String name = "";
			while(true)
			{
				try
				{		
//					name = in.readLine(); 
//					name = URLDecoder.decode(name, "UTF-8");
					name = blackList(con, pstmt, rs, sql);
					sql = "insert into member(id) values('" + name +"')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					break;
				}
				catch(Exception e)
				{
					out.println("중복입니다");
					out.println("다른 이름을 입력하세요");	
				}

			}
				
			return name;
		}
		public String blackList(Connection con, PreparedStatement pstmt, ResultSet rs, String sql) //블랙리스트 처리
		{
			String name = "";
			int a = 0;
			while(true)
			{
				try
				{		
					name = in.readLine(); 
					name = URLDecoder.decode(name, "UTF-8");
					sql = "select blacklist from member where blacklist = '"+name+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					a = 0;
					
					while(rs.next())
					{
						if(name.equals(rs.getString(1)))
						{
							out.println("블랙리스트입니다");
							out.println("나가세요");
							a++;
							break;
						}
						
					}
					if(a == 0)
					{
						break;
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			return name;
			
		
		}
		public void exit(Connection con, PreparedStatement pstmt, ResultSet rs, String sql, String name) // 종료

		{
			try
			{
				sql = "delete member where id = '"+name+"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				sql = "delete block where id = '"+name+"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("예외1:"+e);
			}
			
		}
		//쓰레드를 사용하기 위해서 run()메서드 재정의
		@Override
		public void run()
		{			
			//String s = "";
			String no = "";
			String name = ""; // 클라이언트로부터 받은 이름을 저장할 변수
			try
			{
				Connection con = ConnectionPool.getConnection("env " +noThread);
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				ConnectionPool.listCacheInfos();			
											
				name = existName(con,pstmt,rs,sql); //중복 아이디 체크, 블랙리스트 체크
				
				sendAllMsg(name + "님이 입장하셨습니다.",name,no);

				//현재 객체가 가지고있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림.
				clientMap.put(name, out); // 해쉬맵에 키를 name으로 출력스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size()+"명 입니다.");
				
				//입력스트림이 null이 아니면 반복
				String s = "";
				String sp = "";
				while(in != null)
				{
					s = in.readLine();
					String yok = "";
					boolean msg = false;
					StringTokenizer str = new StringTokenizer(s," ");
					String st1 = str.nextToken();
					//String st2 = str.nextToken();
					System.out.println(s);
					
					sql = "select server from nomsg";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						yok = s.replace(rs.getString(1), "****");
						s = yok;
					}
					
					sql = "select personer from nomsg where id = '" + name + "'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						if(msg == false)
						{
							yok = s.replace(rs.getString(1), "xxxxx");
							sp = yok;
							msg = true;
						}
						else
						{
							yok = sp.replace(rs.getString(1), "xxxxx");
							sp = yok;
						}
						
					}
					
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
						int num1 = s.indexOf(" ");
						String st = s.substring(num1+1);
						num1 = st.indexOf(" ");
						st = st.substring(num1);
						sendToMsg(str.nextToken(),name,st);
					}
					else if(st1.equals("/bk"))
					{
						int num1 = s.indexOf(" ");
						String st = s.substring(num1+1);
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
					}
					else if(st1.equals("/ng"))
					{
						noMsg(s,name,con,pstmt,rs);
//						int num1 = s.indexOf(" ");
//						String st = s.substring(num1+1);
//						sql = "insert into nomsg(personer,id) values('" + st +"','"+ name + "')";
//						pstmt = con.prepareStatement(sql);
//						pstmt.executeUpdate();
					}
					else
					{
						sendAllMsg(name+" => "+s,name,name+" => "+sp);
					}
					
				}
				exit(con,pstmt,rs,sql,name);
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
					Connection con = ConnectionPool.getConnection("env " +noThread);

					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
					exit(con,pstmt,rs,sql,name);
				}
				catch(Exception e)
				{
					System.out.println("예외1:"+e);
				}
				clientMap.remove(name);
				sendAllMsg(name + "님이 퇴장하셨습니다.",name,no);
				System.out.println("현재 접속자 수는 " + clientMap.size()+ "명입니다");
				
	
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
				try
				{
					Connection con = ConnectionPool.getConnection("env " +noThread);

					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
		
					ConnectionPool.listCacheInfos();
					sql = "delete member where id = '"+name+"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					con.close();
				}
				catch(Exception e)
				{
					System.out.println("예외1:"+e);
				}
			}
		}
	}
}
