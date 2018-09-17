package com.study.jsp.chatt;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.study.jsp.BDto;
import com.study.jsp.chatting;

/**
 * sessions가 각 호출 시 마다 생성되므로 static으로 정해 줘야 한다.
 * 브라우져가 websocket을 지원 해야 한다.
 * 웹 소켓 연결 수 별도 close 동작 없이 브라우져를 닫을 경우 자동으로 OnClose가 호출 된다.
 */

@ServerEndpoint("/websocketendpoint2")
public class WsServer2 {
	
	private static WsServer2 instance = new WsServer2();
	HttpServletRequest request; 
	HttpServletResponse response;

	DataSource dataSource = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int check = 0;
	
	int rlistCount = 5;
	int rpageCount = 10;
	
	
	public WsServer2() {
		try
		{
			
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static WsServer2 getInstance() {
		return instance;
	}
	
	//sessions는 해쉬맵과 같은 의미
	private static final java.util.Set<Session> sessions = 
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
	private static final java.util.Map<String, Session> client = 
			java.util.Collections.synchronizedMap(new java.util.HashMap<String,Session>());
	
	private static final java.util.Map<String, String> rname = 
			java.util.Collections.synchronizedMap(new java.util.HashMap<String,String>());
	
	/**
	 * @OnOpen allows us to intercept the creation of a new session.
	 * The session class allows us to send data to the user.
	 * In the method onOpen, we'll let the user know that the handshake was
	 * successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id : " + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Establicshed");
			
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		sessions.add(session);
		
	}
	
	@OnClose
	public void onClose(Session session) { //창을 닫고 나가도 close
		System.out.println("Session " + session.getId() + " has ended");
		Set<String> set = client.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			
			String str = it.next();
			if(session.getId().equals(client.get(str).getId()))
			{
				try {
					System.out.println("에러다다다다");
					int i = 0;
					ArrayList<String> lst = new ArrayList<>();
					con = dataSource.getConnection();
					
					String sql = "delete room where waitroom = '"+str+"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					client.remove(str);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally
				{
					try
					{			
						if(pstmt != null)pstmt.close();
						if(con != null)con.close();
					}
					catch(Exception e)
					{
						
					}
				}		
			}
		}
		
		sessions.remove(session);
		
	}
	
	/**
	 * When a user sends a message to the server,
	 * this method will intercept the message and allow us to react to it.
	 * For now the message is read as a String.
	 */
	
	@OnMessage
	public void onMessage(String message, Session session) {	
		StringTokenizer str = new StringTokenizer(message,"| ");	
		String str1 = str.nextToken();
		int num1 = message.indexOf("|");
		String msg = message.substring(num1 + 1);
		
		client.put(str1, session);
		try
		{
			System.out.println("name"+str1);
			con = dataSource.getConnection();
			if(!msg.equals(""))
			{
				StringTokenizer all = new StringTokenizer(msg,":");
				String st = all.nextToken();
				num1 = msg.indexOf(":");
				String msg2 = msg.substring(num1+1);
				System.out.println("st = " + st+", msg2 = "+msg2);
				if(st.equals("All")) sendAllSessionToMessage(session, msg2); //공지사항
				else if(st.equals("out")) getOut(str1,session, msg2);//강퇴
				if(msg.equals("/mrlist")) roomList(str1,session); //내 방 인원 리스트
				else if(msg.equals("/mwlist")) roomWaitList(str1,session); //내 방 대기실 리스트
				else if(msg.equals("/Bomb")) rBomb(str1,session); // 방 폭발
				else if(st.equals("ivout")) invite(str1, msg2,session); // 초대하기
				else if(st.equals("ivIn")) ivIn(str1,session); // 초대 수락
				else if(st.equals("ivNo")) ivNo(str1, session); // 초대 거절
				else if(st.equals("nomsg")) noMsg(str1,msg2, session);// 금칙어
				else if(msg.equals("/Awlist")) allList(str1,session); //전체 접속자
				else if(st.equals("ear")) //귓속말
				{
					StringTokenizer ear = new StringTokenizer(msg2,":");
					String earName = ear.nextToken();
					num1 = msg2.indexOf(":");
					String msg3 = msg2.substring(num1+1);
					System.out.println("earName = "+earName+", msg3 = "+msg3);
					earChatt(str1,earName, msg3, session);
				}
				
				else if(rname.get(str1) == null)
				{	

					sendWaitSessionToMessage(session, str1,msg);
				}
				else
				{

					sendRoomSessionToMessage(session, str1,msg);
				}
			}
			System.out.println("Message from " + session.getId() + ": " + msg);
//			try {
//
//				final Basic basic = session.getBasicRemote();
//				basic.sendText(str1+" : " +msg);
//			}
//			catch(IOException ex) {
//				ex.printStackTrace();
//			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	public ArrayList<BDto> waitList() //대기실 리스트
	{
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		try
		{
			con = dataSource.getConnection();

			String sql = "select waitroom from room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String waitRoom = rs.getString("waitroom");
				BDto dto = new BDto(waitRoom);
				dtos.add(dto);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return dtos;
	}
		
	public void getOut(String name, Session session, String outName)//강퇴
	{
		try
		{
			Set<String> set = new HashSet<>();
			final Basic basic = session.getBasicRemote();
			String bj = "";
			String rn = rname.get(name);
			
			System.out.println("강퇴자 : " +outName);
			String sql = "select bj from room where roomlist = '" + rn +"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next() == true)
			{
				bj = rs.getString(1);
			}
			rs.close(); pstmt.close(); 
			System.out.println("abcde");
			if(bj.equals(name))
			{
				String outrn = rname.get(outName);
				if(outrn == null)
				{
					basic.sendText("방에 없는 사람입니다");
				}
				else if(outrn.equals(rn))
				{
					sql = "delete room where "+ rn + " = '" + outName + "'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close(); 
					try {
						for(Session sess : WsServer2.sessions) {
							if(client.get(outName).getId().equals(sess.getId()) )
							{
								sess.getBasicRemote().sendText("/outYou");
						
							}
								
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
					sql = "delete room where id = '" + outName + "'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					rs.close(); pstmt.close(); 
					
					sql = "select "+rn+" from room where "+rn+" is not null";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						set.add(rs.getString(1));
					}
					Iterator<String> it = set.iterator();
					while(it.hasNext())
					{
						String roomMember = it.next();
						try
						{
							for(Session sess : WsServer2.sessions) {
								if(client.get(roomMember).getId().equals(sess.getId()))
								{
									session.getBasicRemote().sendText(outName+"님이 강퇴되셨습니다.");
																
								}
							}
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
					rname.remove(outName);
					sql = "insert into room(waitroom) values('" + outName +"')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();	
					
					sql = "insert into room(id, outid) values('"+name+"', '"+outName+"')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close(); 
					
					sql = "insert into room(waitroom) values('" + outName +"')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
				}
			}
			else
			{
				basic.sendText("방장의 권한입니다.");
			}					
		}				
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void rBomb(String name,Session session) //방 폭발
	{
		String rn = rname.get(name);
		String bj = "";
		try
		{
			final Basic basic = session.getBasicRemote();
			String sql = "select bj from room where roomlist = '" + rn + "'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				bj = rs.getString(1);
			}
			
			if(bj.equals(name))
			{
				Set<String> roomMember = new HashSet<>();
				sql = "select "+rn+" from room where "+rn+" is not null";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String rid = rs.getString(1);
					roomMember.add(rid);
				}
				
				sql = "alter table room drop column "+rn;
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				
				sql = "delete room where roomlist = '"+rn+"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
						
				Iterator<String> it_2 = roomMember.iterator();
				while(it_2.hasNext())
				{
					String rid = it_2.next();
					rname.remove(rid);
					try {
						for(Session sess : WsServer2.sessions) {
							if(client.get(rid).getId().equals(sess.getId()) )
							{
								sess.getBasicRemote().sendText("/break");
						
							}
								
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				basic.sendText("방장이 아닙니다");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void roomList(String name,Session session) // 내방 리스트
	{
		String str="";
		try
		{
			final Basic basic = session.getBasicRemote();
			String rn = rname.get(name);
			
			
			String sql = "select "+rn+" from room where "+rn+" is not null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String str2 = rs.getString(1);
				if(!str2.equals(name))
					str = str + "<input type='checkbox' name='roomMember' value='"+str2+"'>"+str2 + "<br>";
				
			}
			basic.sendText("/mrList"+str);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void roomWaitList(String name, Session session) //내방 대기실 인원 리스트
	{
		Set<String> ekey = client.keySet();
		Set<String> rkey = rname.keySet();
		Set<String> set = new HashSet<>();
		String str="";
		Iterator<String> it = ekey.iterator();
		try
		{
			final Basic basic = session.getBasicRemote();
			String sql = "select waitroom from room where waitroom is not null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				set.add(rs.getString("waitroom"));
			}
//			while(it.hasNext())
//			{
//				String ep = it.next(); 
//				if(!rkey.contains(ep))
//				{
//					str = str + "<input type='checkbox' name='waitMember' value='"+ep+"'>"+ep + "<br>";
//				}
//			}
			Iterator<String> itt = set.iterator();
			while(itt.hasNext())
			{
				String id = itt.next();
				str = str + "<input type='checkbox' name='waitMember' value='"+id+"'>"+id + "<br>";
			}
			basic.sendText("/mwList"+str);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void roomIn(String rn, String name, HttpServletRequest request, HttpServletResponse response)//방 참여
	{
		int check = 1;
		this.check = check;

		Set<String> set = new HashSet<>();
		Set<String> id = new HashSet<>();
		try
		{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();		
			request.setCharacterEncoding("UTF-8");
			con = dataSource.getConnection();

			String sql = "select roomlist from room where roomlist is not null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				set.add(rs.getString(1));
				
			}
	
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
				rs.close(); pstmt.close(); con.close();
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"방이 존재하지 않습니다.\");\r\n" + 
						"history.go(-1);\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
				return;	
			}
			
			
			int num = 0; int num1 = 0; 
			
			sql = "select count from room where roomlist = '" + rn + "'"; // 제한인원
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next() == true)
			{
				num = rs.getInt(1);
			}
			
			sql = "select count(" + rn + ") from room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next() == true)
			{
				num1 = rs.getInt(1);
			}
		
			if(num1 > num-1)
			{
				rs.close(); pstmt.close(); con.close();
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"방이 가득 찼습니다.\");\r\n" + 
						"history.go(-1);\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
				return;	
			}
			Set<String> rbj = new HashSet<>();
			Set<String> room = new HashSet<>();
			
			sql = "select id from room where outid = '"+name+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String bj = rs.getString("id");
				if(bj == null)
				{
					break;
				}
				else
				{
					rbj.add(bj);
				}
			}
			
			Iterator<String> itt = rbj.iterator();
			
			
			while(itt.hasNext())
			{	
				sql = "select roomlist from room where bj = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, itt.next());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					room.add(rs.getString("roomlist"));
				}
							
			}
			if(room.contains(rn))
			{
				rs.close(); pstmt.close(); con.close();
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"강퇴당한 방은 들어갈 수 없습니다.\");\r\n" + 
						"history.go(-1);\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
				return;	
			}

			else
			{	
				rname.put(name, rn);
				
				sql = "insert into room("+rn+") values('" + name +"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				rs.close();
				pstmt.close();
				con.close();

				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"방에 입장하셨습니다.\");\r\n" + 
						"		document.location.href='chattClient.jsp?rn="+rn+"&id="+name+"';" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
				
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public void roomOut(String name,HttpServletRequest request, HttpServletResponse response) // 방 탈출
	{

		int i = 0;
		ArrayList<String> lst = new ArrayList<>();
		try
		{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();		
			request.setCharacterEncoding("UTF-8");
			
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"방에서 나왔습니다.\");\r\n" + 
					"		document.location.href=\"chatt.do\";\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
			con = dataSource.getConnection();
			
			String rn = rname.get(name);
			
			if(rn == null)
				return;
			
			String sql = "delete room where "+ rn + " = '" + name + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close(); 
			
			
			sql = "delete room where id = '" + name + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "select count("+rn+") from room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next() == true)
			{
				i = rs.getInt(1);
			}
			rs.close(); pstmt.close(); 
			
			if(i == 0)
			{
				sql = "alter table room drop column "+ rn;
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				
				sql = "delete room where roomlist = '" + rn +"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				con.close(); rs.close(); pstmt.close(); 
				rname.remove(name);
				
				
			}
			else
			{
				String bjname = "";
				sql = "select bj from room where roomlist = '" + rn + "'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true)
				{
					bjname = rs.getString(1);
				}
				rs.close(); pstmt.close(); 
				
				if(bjname.equals(name))
				{
					sql = "select "+ rn +" from room where "+rn+" is not null";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						String rid = rs.getString(1);
						lst.add(rid);
					}
					rs.close(); pstmt.close();
					
					sql = "update room set bj = '"+lst.get(0) +"' where roomlist = '" + rn +"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close(); 
				}				
				rname.remove(name);
				con.close(); rs.close(); pstmt.close(); 
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"방에서 나왔습니다.\");\r\n" + 
						"		document.location.href=\"chatt.do\";\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
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
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public void roomCreate(String name, String rn, String rcount, HttpServletRequest request, HttpServletResponse response) throws IOException	//방 생성		
	{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();		
		request.setCharacterEncoding("UTF-8");

		rname.put(name, rn);

		int count = Integer.parseInt(rcount);
		
	
		try
		{		
			con = dataSource.getConnection();
			
			String sql = "delete room where waitroom = '"+name+"'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			
			sql = "insert into room(roomlist, bj, count, ox) values(?,?,?,'공개방')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rn);
			pstmt.setString(2, name);
			pstmt.setInt(3, count);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "alter table room add ("+rn+"    varchar2(20))";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into room("+rn+") values(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt.close();
			
			
			
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"공개방을 개설하였습니다.\");\r\n" + 
					"		document.location.href='chattClient.jsp?rn="+rn+"&id="+name+"&count="+count+"';" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"제한인원은 숫자를 입력하세요.\");\r\n" + 
					"history.go(-1);\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		finally
		{
			try
			{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public void allList(String name, Session session)
	{
		Set<String> ekey = client.keySet();
		String str="";
		Iterator<String> it = ekey.iterator();
		try
		{
			final Basic basic = session.getBasicRemote();

			while(it.hasNext())
			{
				String ep = it.next(); 
				
				str = str + "<input type='checkbox' name='allMember' value='"+ep+"'>"+ep + "<br>";
				
			}
			basic.sendText("/alList"+str);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void pwRoom(String name, String rn, String rcount, String pw, HttpServletRequest request, HttpServletResponse response) throws IOException //비공개 방	
	{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();		
		request.setCharacterEncoding("UTF-8");
		
		rname.put(name, rn);
		int count = Integer.parseInt(rcount);
		
		try
		{		
			con = dataSource.getConnection();
			
			String sql = "alter table room add ("+rn+"    varchar2(20))";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into room(roomlist, bj, count, ox, pw) values(?,?,?,'비공개방', ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rn);
			pstmt.setString(2, name);
			pstmt.setInt(3, count);
			pstmt.setString(4, pw);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into room("+rn+") values(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt.close();
			
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"비공개방을 개설하였습니다.\");\r\n" + 
					"		document.location.href='chattClient.jsp?rn="+rn+"&id="+name+"&count="+count+"&pw="+pw+"';" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"제한인원은 숫자를 입력하세요.\");\r\n" + 
					"history.go(-1);\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		finally
		{
			try
			{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
		
	public ArrayList<BDto> roomList(int curPage, String id, HttpServletRequest request) //방 리스트
	{

		int i = 0;
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * rlistCount + 1;
		int nEnd = (curPage - 1) * rlistCount + rlistCount;
	
		try 
		{

			con = dataSource.getConnection();
			String query = "select * " +
							"  from (select rownum num, A.*" +
							"  from (select * from room where roomlist is not null) A " +
							"  where rownum <= ?) B " +
							" where B.num >= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);		
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				int num = rs.getInt("num");
				String roomList = rs.getString("roomList");
				String bj = rs.getString("bj");
				int count = rs.getInt("count");
				String ox = rs.getString("ox");

					
				BDto dto = new BDto(num,roomList,bj,count,ox);
				dtos.add(dto);
			}
			
			query = "select count(waitroom) from room where waitroom = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				i = rs.getInt(1);
			}
			
			if(i == 0)
			{
				query = "insert into room(waitroom) values(?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.executeUpdate();				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	private void sendRoomSessionToMessage(Session self, String name, String message) // 방 대화
	{

		Set<String> yok = new HashSet<>();
		String rn = rname.get(name);
		System.out.println(rn);
		ArrayList<String> lst = new ArrayList<String>();	//방에 있는 사람
		ArrayList<String> lst2 = new ArrayList<String>();	// 블럭
		ArrayList<String> lst3 = new ArrayList<String>();	// lst - lst2
		try
		{
			String sql = "select " + rn + " from room where "+rn+" is not null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String str = rs.getString(1);
				lst.add(str);	
			}
			rs.close(); pstmt.close(); 
				System.out.println("fffff");
			if(lst.size() == 0)
			{
				return;
			}
				// ----------------------------------------------------------------------------------
			sql = "select id from block where blocking = '" + name + "' and id is not null"; //블럭
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				lst2.add(rs.getString(1));
			}
			rs.close(); pstmt.close();
				
			Iterator<String> it = lst.iterator();
			while(it.hasNext())
			{
				String str = it.next();
				if(!lst2.contains(str))
				{
					lst3.add(str);
				}
			}	
				//--------------------------------------------------- 금칙어
			sql = "select id from nomsg";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String no = rs.getString(1);
				yok.add(no);
			}
				
			it = lst3.iterator();
			while(it.hasNext())
			{
				String ymsg = message;
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
					try {
//						if( ! self.getId().equals(client.get(str).getId())) //나에겐 보내지 않고 나 이외 세션에 있는 사람에게 보냄
							client.get(str).getBasicRemote().sendText(name+" : " + ymsg);
						
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else
	 			{
					System.out.println(client.get(str));
//					if( ! self.getId().equals(client.get(str).getId())) //나에겐 보내지 않고 나 이외 세션에 있는 사람에게 보냄
						client.get(str).getBasicRemote().sendText(name+" " + message);
				}

			}
			rs.close(); pstmt.close(); 
				
		}
		catch(Exception e)
		{
			System.out.println("방 대화 에러");
			e.printStackTrace();
		}
			
	}
	
	private void sendAllSessionToMessage(Session self, String message) { //공지사항 전용
		
		try {
			for(Session session : WsServer2.sessions) {
				if( ! self.getId().equals(session.getId()) ) //나에겐 보내지 않고 나 이외 세션에 있는 사람에게 보냄
					session.getBasicRemote().sendText("공지사항 : " + message);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	private void sendWaitSessionToMessage(Session self, String name, String message) //대기실 대화
	{
		try
		{
			Set<String> noid = new HashSet<>();
			Set<String> yok = new HashSet<>();
			Set<String> idset = new HashSet<>();
			Set<String> blockset = new HashSet<>();
			Set<String> cha = new HashSet<>();
			Set<String> wait = new HashSet<>();

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
			Set<String> key = client.keySet();
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
				String ymsg = message;
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
//					if( ! self.getId().equals(client.get(str).getId())) //나에겐 보내지 않고 나 이외 세션에 있는 사람에게 보냄
						client.get(str).getBasicRemote().sendText(name +" : " + ymsg);
				}
				else
				{
//					if( ! self.getId().equals(client.get(str).getId())) //나에겐 보내지 않고 나 이외 세션에 있는 사람에게 보냄
						client.get(str).getBasicRemote().sendText(name + " " + message);
				}
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void invite(String name, String ivname, Session session) // 초대하기
	{
		try
		{
			Set<String> set = new HashSet<>();
			final Basic basic = session.getBasicRemote();
			String rn = rname.get(name);

			if(rn == null)
			{
				basic.sendText("방안에서 해야합니다");	
				return;
			}
			String sql = "select count(ivin) from member where ivin = '" + ivname +"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int i = rs.getInt(1);
			
			if(i > 0)
			{
				basic.sendText("이미 초대받은 사람입니다.");	
				return;
			}
			
			sql = "select waitroom from room where waitroom is not null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				set.add(rs.getString("waitroom"));
			}
			if(!set.contains(ivname))
			{
				basic.sendText("대기실에 없는 분입니다.");	
				return;
			}
			
			sql = "insert into member(ivout, ivin) values('" + name + "', '"+ ivname +"')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "delete room where id = '" + name + "' and outid ='"+ivname+"'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();

			client.get(ivname).getBasicRemote().sendText("/ivnYou"+name);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void ivIn(String name, Session session) //초대 수락
	{
		Set<String> set = new HashSet<>();
		String ivRn=null;
		try
		{
			final Basic basic = session.getBasicRemote();
			
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
				
				sql = "select roomlist from room where roomlist = '"+rn+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					ivRn = rs.getString("roomlist");
				}
				
				if(!(ivRn == null))
				{
					int num = 0; int num1 = 0; 
					
					sql = "select count from room where roomlist = '" + rn + "'"; // 제한인원
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						num = rs.getInt(1);
					}
					
					sql = "select count(" + rn + ") from room";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next() == true)
					{
						num1 = rs.getInt(1);
					}
				
					if(num1 > num-1)
					{
						basic.sendText("Full");
						return;
					}
					
					rname.put(name, rn);
					
					sql = "insert into room("+rn+") values('" + name +"')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					basic.sendText("/invite"+rn+","+name);
				}
				
				sql = "delete member where ivin = '" + name +"'";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void ivNo(String name, Session session) //초대 거절
	{
		try
		{
			String sql = "select ivout from member where ivin = '"+ name +"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			String ivname = rs.getString(1);
			client.get(ivname).getBasicRemote().sendText("초대를 거절하셨습니다.");	
			
			sql = "delete member where ivin = '" + name + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void noMsg(String name, String str, Session session) throws IOException //금칙어
	{
		try
		{
			final Basic basic = session.getBasicRemote();
			if(str != null)
			{
				String sql = null;
				int i = 0;
				sql = "select count(cou) from nomsg where id = ? and personal = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, str);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					i = rs.getInt(1);
				}
				
				if(i == 0)
				{
					sql = "insert into nomsg(personal,id, cou) values('" + str +"','"+ name + "','o')";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					basic.sendText(str+"을(를) 금칙어로 입력하셨습니다.");
				}
				else
				{
					sql = "delete nomsg where personal = '" + str + "' and id = '" + name +"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					basic.sendText(str+"을(를) 금칙어에서 삭제하셨습니다.");
				}
				
			}
			else
			{
				try {
					basic.sendText("금칙어를 입력하세요");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void earChatt(String name, String earName, String msg, Session session) //귓속말
	{
		Set<String> yok = new HashSet<>();
		Set<String> id = new HashSet<>();
		String ymsg = "";
		try
		{
			String sql = "select id from nomsg";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String no = rs.getString(1);
				yok.add(no);
			}
			System.out.println("earName  = " +earName+", msg = " + msg);
			Iterator<String> it = yok.iterator();
			while(it.hasNext())
			{
				ymsg = msg;
				String str = it.next();
				
				sql = "select personal from nomsg where id = '"+str+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					String nomsg = rs.getString(1);
					String noo = ymsg.replace(nomsg, "xxxxx");
					ymsg = noo;
					
				}
				
			}
			if(yok.contains(earName))
			{
				client.get(name).getBasicRemote().sendText(name +"|귓속말 : " + ymsg);
				client.get(earName).getBasicRemote().sendText(name +"|귓속말 : " + ymsg);
			}
			else
			{
				client.get(name).getBasicRemote().sendText(name +"|귓속말 : " + msg);
				client.get(earName).getBasicRemote().sendText(name +"|귓속말 : " + msg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@OnError
	public void onError( Throwable e, Session session) {
		System.out.println("aaa");
		e.printStackTrace();
	}


}
