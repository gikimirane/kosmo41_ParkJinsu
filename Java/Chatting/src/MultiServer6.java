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
			Set<String> noid = new HashSet<>();
			Set<String> yok = new HashSet<>();
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
			rs.close(); pstmt.close(); 
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
			
			sql = "select id from nomsg";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String no = rs.getString(1);
				yok.add(no);
			}
			
			it = wait.iterator();
			while(it.hasNext())
			{	
				String ymsg = msg;
				String str = it.next();
				if(yok.contains(str))
				{
					sql = "select personal from nomsg where id = '"+str+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						String nomsg = rs.getString(1);
						String noo = ymsg.replace(nomsg, "xxxxx");
						ymsg = noo;
						
					}
					PrintWriter it_out = (PrintWriter) clientMap.get(str);
					it_out.println(URLEncoder.encode(ymsg, "UTF-8"));
				}
				else
				{
					PrintWriter it_out = (PrintWriter) clientMap.get(str);
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
				}
				
			}
			rs.close(); pstmt.close(); con.close();
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
				out.println("--------------------------------------------");
				out.println(URLEncoder.encode("현재 접속중인 ID는 ", "UTF-8"));
			}
			catch(UnsupportedEncodingException e) {}
			while(itr.hasNext())
			{
				try {
				out.println(URLEncoder.encode(itr.next()+" ", "UTF-8"));
				}catch(UnsupportedEncodingException e) {}
			}
			out.println("--------------------------------------------");
		}
		public void sendToMsg(String str1, String str2, String st)// 귓속말
		{
			try {
			PrintWriter out2 = (PrintWriter)clientMap.get(str1);
			out2.println(URLEncoder.encode(str2+"(귓속말)"+str1+" "+st, "UTF-8"));
			}catch(UnsupportedEncodingException e) {}
			
		}
		public void block(String st, String name)//block
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;	
				
				sql = "insert into block values('"+name+"','"+st+"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				
				sql = "select count(*) from block where id = '" + name +"' and blocking = '" + st+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					if(rs.getInt(1) == 2)
					{
						sql = "delete block where id = '" + name +"' and blocking = '" + st + "'";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
					}
				}
				rs.close(); pstmt.close(); con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			

		}
		public void noMsg(String st, String name) //개인 금칙어
		{
			try
			{
				
//				int num1 = str.indexOf(" ");
//				String st = str.substring(num1+1);
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
						}
					}
					rs.close(); pstmt.close(); con.close();
					
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
			int a = 0;
			
			try
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;	
				while(true)
				{					
					boolean check = true;
					name = in.readLine(); 
					name = URLDecoder.decode(name, "UTF-8");
					Connection con = ConnectionPool.getConnection();
					sql = "select count(blacklist) from member";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						a = rs.getInt(1);
					}
					rs.close(); pstmt.close(); con.close();
					if(a == 0)
					{
						break;
					}
					con = ConnectionPool.getConnection();
					sql = "select blacklist from member where blacklist is not null";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						String bname = rs.getString(1);
						if(name.equals(bname))
						{
							out.println(URLEncoder.encode(name+ "는 블랙리스트입니다", "UTF-8"));
							out.println(URLEncoder.encode("나가세요", "UTF-8"));
							check = false;
						}					
					}
					rs.close();	pstmt.close(); con.close();	
					if(check)
					{
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
						break;
					}
					else
					{
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
						continue;
					}
				}
				
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
				
				sql = "delete member where ivin = '" + name +"'";
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
					it_out.println(URLEncoder.encode("공지사항 : " +msg, "UTF-8"));
				}
				catch(Exception e)
				{
					System.out.println("예외:"+e);
				}
			}
		}
		@SuppressWarnings("resource")
		public void room(String rn, String name) throws UnsupportedEncodingException //방 생성
		{
			ArrayList<String> lst = new ArrayList<>();
			int a = 0;
			try
			{
				
				if(rname.get(name) != null)
				{
					out.println(URLEncoder.encode("방 안에서 방을 만들수 없습니다","UTF-8" ));
				}
				else
				{
					
					Connection con = ConnectionPool.getConnection();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					String sql = "select count(roomlist) from room where roomlist = '"+rn+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						a = rs.getInt(1);
					}
					rs.close(); pstmt.close(); con.close();
					if(a > 0)
					{
						out.println(URLEncoder.encode("같은 이름의 방은 만들수 없습니다","UTF-8" ));
						return;
					}
				
					out.println(URLEncoder.encode("제한 인원을 입력하세요","UTF-8" ));
					String s = in.readLine();
					int num1 = Integer.parseInt(s);
					

					out.println(URLEncoder.encode("비공개 방을 만드시겠습니까? <y / n>","UTF-8" ));
					String s2 = in.readLine();
					if(s2.equals("y"))
					{
						out.println(URLEncoder.encode("비밀번호를 입력하세요","UTF-8" ));
						String s3 = in.readLine();
						
						rname.put(name, rn);
						String str = rname.get(name);
						out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));
						con = ConnectionPool.getConnection();
						pstmt = null;
						
						sql = "alter table room add ("+str+"     varchar2(20))";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
						
						con = ConnectionPool.getConnection();
						sql = "insert into room(roomlist, bj, count, ox, pw) values('" + str + "', '"+name+"', " + num1 +",'비공개방','"+s3+"')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
						
						con = ConnectionPool.getConnection();
						sql = "insert into room("+str+") values('" + name +"')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
					}
					else
					{
						out.println(URLEncoder.encode("공개방으로 개설됩니다.","UTF-8" ));	
						rname.put(name, rn);
						String str = rname.get(name);
						out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));
						con = ConnectionPool.getConnection();
						pstmt = null;
						
						sql = "alter table room add ("+str+"     varchar2(20))";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
						
						con = ConnectionPool.getConnection();
						sql = "insert into room(roomlist, bj, count, ox) values('" + str + "', '"+name+"', " + num1 +",'공개방')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
						
						con = ConnectionPool.getConnection();
						sql = "insert into room("+str+") values('" + name +"')";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
					}
				}
			}
			catch(Exception e)
			{
				out.println(URLEncoder.encode("숫자를 입력하셔야 합니다","UTF-8" ));
				out.println(URLEncoder.encode("다시 방을 만드세요","UTF-8" ));
			}
		}
		public void roomExit(String name) // 방 탈출
		{
			int i = 0;
			Set<String> set = new HashSet<>();
			ArrayList<String> lst = new ArrayList<>();
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
				pstmt.close(); con.close();
				out.println(URLEncoder.encode("방에서 나왔습니다", "UTF-8"));
				
				con = ConnectionPool.getConnection();
				sql = "delete room where id = '" + name + "'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close(); con.close();
					
				con = ConnectionPool.getConnection();
				sql = "select count("+rn+") from room";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					i = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
				
				if(i == 0)
				{
					con = ConnectionPool.getConnection();
					sql = "alter table room drop column "+ rn;
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					
					sql = "delete room where roomlist = '" + rn +"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					con.close();
					rname.remove(name);
				}
				else
				{
					String bjname = "";
					con = ConnectionPool.getConnection();
					sql = "select bj from room where roomlist = '" + rn + "'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						bjname = rs.getString(1);
					}
					rs.close(); pstmt.close(); con.close();
					
					if(bjname.equals(name))
					{
						con = ConnectionPool.getConnection();
						sql = "select "+ rn +" from room where "+rn+" is not null";
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next())
						{
							String rid = rs.getString(1);
							lst.add(rid);
						}
						rs.close(); pstmt.close(); con.close();
						
						con = ConnectionPool.getConnection();
						sql = "update room set bj = '"+lst.get(0) +"' where roomlist = '" + rn +"'";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						pstmt.close(); con.close();
					}				
					sendRMsg(name+"님이 방에서 나갔습니다.",name);
					rname.remove(name);
				}
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
			ArrayList<String> lst2 = new ArrayList<String>();	
			ArrayList<String> lst3 = new ArrayList<String>();	
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				String sql = "select " + rn + " from room where "+rn+" is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String str = rs.getString(1);
					lst.add(str);	
				}
				rs.close(); pstmt.close(); con.close();
				
				if(lst.size() == 0)
				{
					return;
				}
				
				con = ConnectionPool.getConnection();
				sql = "select id from block where blocking = '" + name + "' and id is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					lst2.add(rs.getString(1));
				}
				rs.close(); pstmt.close(); con.close();
				
				Iterator<String> it = lst.iterator();
				while(it.hasNext())
				{
					String str = it.next();
					if(!lst2.contains(str))
					{
						lst3.add(str);
					}
				}
				it = lst3.iterator();
				while(it.hasNext())
				{
					try
					{		
						PrintWriter out3 = (PrintWriter)clientMap.get(it.next());	
						out3.println(URLEncoder.encode(msg, "UTF-8"));
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}

				}
				
			}
			catch(Exception e)
			{
				System.out.println("ddddd");
				e.printStackTrace();
			}
		}
		public void roomIn(String rn, String name) // 방 참여
		{
			int a = 0;
			Set<String> set = new HashSet<>();
			Set<String> id = new HashSet<>();
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				String sql = "select count(roomlist) from room";
				pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					a = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
				
				if(a == 0)
				{
					out.println(URLEncoder.encode("방이 없습니다","UTF-8" ));
					return;		
				}
				con = ConnectionPool.getConnection();
				sql = "select roomlist from room where roomlist is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					set.add(rs.getString(1));
				}
				rs.close(); pstmt.close(); con.close();
				Iterator<String> it = set.iterator();
				while(it.hasNext())
				{
					String rid = it.next();
					if(rn.contains(rid))
					{
						id.add(rid);				
					}
				}
				if(id.size() == 0)
				{
					out.println(URLEncoder.encode("방이 없습니다","UTF-8" ));
					return;
				}
				
				
				int num = 0; int num1 = 0; String str = "";	
				con = ConnectionPool.getConnection();
				pstmt = null;
				sql = "select count from room where roomlist = '" + rn + "'"; // 제한인원
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					num = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
				
				con = ConnectionPool.getConnection();
				sql = " select count(" + rn + ") from room";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					num1 = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
				
				if(num1 > num-1)
				{
					out.println(URLEncoder.encode("방이 가득 찼습니다","UTF-8" ));
					return;
				}
				Set<String> rbj = new HashSet<>();
				Set<String> room = new HashSet<>();
				con = ConnectionPool.getConnection();
				sql = "select id from room where outid = '"+name+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String bj = rs.getString(1);
					if(bj == null)
					{
						break;
					}
					else
					{
						rbj.add(bj);
					}
				}
				rs.close(); pstmt.close(); con.close();
				
				Iterator<String> itt = rbj.iterator();
				while(itt.hasNext())
				{
					String st = rname.get(itt.next());
					room.add(st);			
				}
				if(room.contains(rn))
				{
					out.println(URLEncoder.encode("강퇴당한 방은 들어갈 수 없습니다.","UTF-8" ));
					return;
				}
				else if(rname.get(name) != null)
				{
					out.println(URLEncoder.encode("방 안에서 다른 방으로 들어갈 수 없습니다.","UTF-8" ));
				}

				else
				{
					con = ConnectionPool.getConnection();
					pstmt = null;
					sql = "select count(pw) from room where roomlist = '"+rn+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						num = rs.getInt(1);
					}
					rs.close(); pstmt.close(); con.close();
					
					while(true)
					{
						if(num == 1)
						{
							
							out.println(URLEncoder.encode("비밀번호를 입력하세요", "UTF-8"));
							String s = in.readLine();
							con = ConnectionPool.getConnection();
							sql = "select pw from room where roomlist = '"+rn+"'";
							pstmt = con.prepareStatement(sql);
							rs = pstmt.executeQuery();
							if(rs.next() == true)
							{
								str = rs.getString(1);
							}
							rs.close(); pstmt.close(); con.close();
							if(str.equals(s))
							{
								out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));								
								con = ConnectionPool.getConnection();
								sql = "insert into room("+rn+") values('" + name +"')";
								pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();
								pstmt.close();
								con.close();
								rname.put(name, rn);
								sendRMsg(name+"님이 들어오셨습니다",name);
								break;
							}
							else
							{
								out.println(URLEncoder.encode("다른 비밀번호를 입력하세요", "UTF-8"));
								out.println();
							}
						}
						else if(num == 0)
						{
							out.println(URLEncoder.encode("방에 입장하셨습니다", "UTF-8"));							
							con = ConnectionPool.getConnection();
							sql = "insert into room("+rn+") values('" + name +"')";
							pstmt = con.prepareStatement(sql);
							pstmt.executeUpdate();
							pstmt.close();
							con.close();
							rname.put(name, rn);
							sendRMsg(name+"님이 들어오셨습니다",name);
							break;
						}
						
					}
				}
				
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void rList()// 방 리스트
		{
			ArrayList<String> lst = new ArrayList<String>();
			ArrayList<String> lst2 = new ArrayList<String>();
			ArrayList<Integer> lst3 = new ArrayList<>();
			ArrayList<String> lst4 = new ArrayList<String>();
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select roomlist, bj, count, ox from room where roomlist is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();				
				while(rs.next())
				{
					String st = rs.getString(1);
					lst.add(st);
					lst2.add(rs.getString(2));
					lst3.add(rs.getInt(3));
					lst4.add(rs.getString(4));
//					out.println(URLEncoder.encode(rs.getString(1), "UTF-8"));
				}
				rs.close();
				pstmt.close();
				con.close();
				
				if(lst.size() == 0)
				{
					out.println(URLEncoder.encode("현재 방이 없습니다.", "UTF-8"));
				}
				else
				{			
					out.println(URLEncoder.encode("현재 방 리스트입니다.", "UTF-8"));
					out.println("-----------------------------------------------------------------------------------------");
					
					Iterator<String >it = lst.iterator();
					Iterator<String> it_2 = lst2.iterator();
					Iterator<Integer> it_3 = lst3.iterator();
					Iterator<String> it_4 = lst4.iterator();
					while(it.hasNext())
					{
						String str = it.next();
						String str2 = it_2.next();
						int num1 = it_3.next();
						String str3 = it_4.next();
						con = ConnectionPool.getConnection();
						sql = "select count("+str+") from room";
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						if(rs.next() == true)
						{
							out.println(URLEncoder.encode(str + "    방장: "+str2+"     제한인원: "+rs.getInt(1)+"/" + num1+"       공개/비공개 : "+str3, "UTF-8"));
						}
						rs.close(); pstmt.close(); con.close();
					}
					out.println("----------------------------------------------------------------------------------------");
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void wList() //대기실 인원 리스트
		{
			Set<String> ekey = clientMap.keySet();
			Set<String> rkey = rname.keySet();
			Iterator<String> it = ekey.iterator();
			try
			{
				out.println("---------------------------------------------------");
				out.println(URLEncoder.encode("현재 대기실에 있는 인원 ", "UTF-8"));
				while(it.hasNext())
				{
					String ep = it.next(); 
					if(!rkey.contains(ep))
					{
						out.println(URLEncoder.encode(ep, "UTF-8"));
					}
				}
				out.println("---------------------------------------------------");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void mrList(String name) //내방 인원 리스트
		{
			Set<String> set = new HashSet<>();
			String rn = rname.get(name);
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "select "+ rn + " from room where "+rn+" is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String rid = rs.getString(1);
					set.add(rid);
				}
				rs.close(); pstmt.close(); con.close();
				
				Iterator<String> it = set.iterator();
				out.println("---------------------------------------------------");
				out.println(URLEncoder.encode("현재 방에 있는 인원은 ", "UTF-8"));
				while(it.hasNext())
				{
					out.println(URLEncoder.encode(it.next(), "UTF-8"));
				}
				out.println("---------------------------------------------------");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
		public void out(String outname, String name) //강퇴
		{
			try
			{
				String bj = "";
				String rn = rname.get(name);
				if(rn == null)
				{
					out.println(URLEncoder.encode("방에서 해야 합니다", "UTF-8"));
					return;
				}
				else
				{
					Connection con = ConnectionPool.getConnection();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					String sql = "select bj from room where roomlist = '" + rn +"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						bj = rs.getString(1);
					}
					rs.close(); pstmt.close(); con.close();
					
					if(bj.equals(name))
					{
						String outrn = rname.get(outname);
						if(outrn == null)
						{
							out.println(URLEncoder.encode("방에 없는 사람입니다", "UTF-8"));
						}
						else if(outrn.equals(rn))
						{
							roomExit(outname);
							rname.remove(outname);
							sendRMsg(outname + "님이 강퇴되셨습니다", name);
							PrintWriter oname = (PrintWriter) clientMap.get(outname);
							oname.println(URLEncoder.encode("강퇴 당하셨습니다", "UTF-8"));
							
							con = ConnectionPool.getConnection();
							sql = "insert into room(id, outid) values('"+name+"', '"+outname+"')";
							pstmt = con.prepareStatement(sql);
							pstmt.executeUpdate();
							pstmt.close(); con.close();
						}
					}
					else
					{
						out.println(URLEncoder.encode("방장의 권한입니다", "UTF-8"));
					}
					
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void rBomb(String name) //방 폭발
		{
			String rn = rname.get(name);
			String bj = "";
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select bj from room where roomlist = '" + rn + "'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					bj = rs.getString(1);
				}
				rs.close(); pstmt.close(); con.close();
				
				if(bj.equals(name))
				{
					Set<String> set = new HashSet<>();
					con = ConnectionPool.getConnection();
					sql = "select "+rn+" from room where "+rn+" is not null";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						String rid = rs.getString(1);
						set.add(rid);
					}
					rs.close(); pstmt.close(); con.close();
										
					con = ConnectionPool.getConnection();
					sql = "alter table room drop column "+rn;
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					sql = "delete room where roomlist = '"+rn+"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close(); con.close();
					
				
					Iterator<String> it_2 = set.iterator();
					while(it_2.hasNext())
					{
						String rid = it_2.next();
						rname.remove(rid);
						PrintWriter bomb = (PrintWriter)clientMap.get(rid);	
						bomb.println(URLEncoder.encode("방이 폭발하였습니다", "UTF-8"));
					}
				}
				else
				{
					out.println(URLEncoder.encode("방장이 아닙니다", "UTF-8"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		public void ivOut(String ivname, String name) //초대
		{
			Set<String> wname = new HashSet<>();
			
			try
			{
				String rn = rname.get(name);

				if(rn == null)
				{
					out.println(URLEncoder.encode("방안에서 해야합니다", "UTF-8"));	
					return;
				}
				
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "select count(ivin) from member where ivin = '" + ivname +"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				int i = rs.getInt(1);
				
				if(i > 0)
				{
					out.println(URLEncoder.encode("이미 초대받은 사람입니다.", "UTF-8"));	
					rs.close(); pstmt.close(); con.close();
					return;
				}
				
				sql = "insert into member(ivout, ivin) values('" + name + "', '"+ ivname +"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close(); con.close();
				
				PrintWriter ivout = (PrintWriter)clientMap.get(ivname);	
				ivout.println(URLEncoder.encode(name + "님이 당신을 초대하였습니다. 들어가시겠습니까? < Y / N >", "UTF-8"));	
			}
			catch(Exception e)
			{
				out.println("error : iv");
				e.printStackTrace();
			}
		}
		public void ivIn(String name) //초대수락
		{
			Set<String> set = new HashSet<>();
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select ivin from member where ivin = '" + name + "'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String ivin = rs.getString(1);
					set.add(ivin);
				}
				rs.close(); pstmt.close(); 
				if(set.contains(name))
				{
					sql = "select ivout from member where ivin = '" + name +"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					String ivout = rs.getString(1);
					
					String rn = rname.get(ivout);
					roomIn(rn, name);
					
					sql = "delete member where ivin = '" + name +"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					rs.close(); pstmt.close(); con.close();
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void ivNo(String name) //초대 거절
		{
			try
			{
				Connection con = ConnectionPool.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select ivout from member where ivin = '"+ name +"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				String ivname = rs.getString(1);
				PrintWriter it = (PrintWriter) clientMap.get(ivname);
				it.println(URLEncoder.encode("초대를 거절하셨습니다", "UTF-8"));	
				
				sql = "delete member where ivin = '" + name + "'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				
				rs.close(); pstmt.close(); con.close();
			}
			catch(Exception e)
			{
				
			}
		}
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
				
				if(con != null) con.close();
				//현재 객체가 가지고있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림.
				clientMap.put(name, out); // 해쉬맵에 키를 name으로 출력스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size()+"명 입니다.");
				//입력스트림이 null이 아니면 반복
				String s = "";
							
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
					ConnectionPool.listCacheInfos();									
					sql = "select server from nomsg where server is not null";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						yok = s.replace(rs.getString(1), "****");
						s = yok;
					}					
					rs.close(); pstmt.close(); con.close();
					
						
					if(s.equals("q") || s.equals("Q")) break; //종료 
					else if(st1.equals("/list")) sendList(); //리스트					
					else if(st1.equals("/to"))// 귓속말
					{
						num1 = st.indexOf(" ");
						st = st.substring(num1);
						sendToMsg(str.nextToken(),name,st);
					}
					else if(st1.equals("/bk")) block(st,name);//블럭
					else if(st1.equals("/ng")) //금칙어 
					{
						noMsg(st,name);
					}
					else if(st1.equals("/all")) every(st); //공지사항
					else if(st1.equals("/room")) room(st, name); //방생성 
					else if(st1.equals("/in")) roomIn(st, name);//방 참여
					else if(st1.equals("/rlist")) rList(); //방 리스트
					else if(st1.equals("/wlist")) wList();//대기실 리스트
					else if(st1.equals("/mrlist"))mrList(name); //내방 인원 리스트
					else if(st1.equals("/out")) out(st, name); //강퇴
					else if(st1.equals("/exit")) roomExit(name); //방 나가기
					else if(st1.equals("/rbomb")) rBomb(name); //방폭발
					else if(st1.equals("/iv")) ivOut(st,name); //초대 하기
					else if(st1.equals("y")) ivIn(name); //초대 받기
					else if(st1.equals("n")) ivNo(name); //초대 거절
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
			}
			catch(Exception e)
			{
				System.out.println("예외3:"+e);
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
					System.out.println("예외4:"+e);
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
			}
		}
	}
}
