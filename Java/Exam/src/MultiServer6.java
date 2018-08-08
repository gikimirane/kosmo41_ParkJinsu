import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.sql.*;

//test의 test2 
public class MultiServer6 {
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	Map<String, PrintWriter> clientMap2;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int count = 0;

	// 생성자
	public MultiServer6() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성
		clientMap = new HashMap<String, PrintWriter>();
		clientMap2 = new HashMap<String, PrintWriter>();

		// 해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
		Collections.synchronizedMap(clientMap2);

	}

	public void init() {
		try {
			con = ConnectionPool.getConnection();

			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객체 생성
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());

				Thread msr = new MultiServerT(socket); // 쓰레드 생성.
				msr.start(); // 쓰레드 시동.
			}
		} catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("2");
				e.printStackTrace();
			}
		}
	}

	// 접속자 리스트 보내기
	public void list(PrintWriter out) {

		// 출력스트림을 순차적으로 얻어와서 해당 메세지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "현재 전체 접속자 [";
		while (it.hasNext()) {
			msg = msg + (String) it.next() + ",";
		}
		msg = msg.substring(0, msg.length() - 1) + "]"; // -1은 , 를 없애기위해서
		out.println(msg);
	}

	// 귓속말
	public void to(String name, String s) {
		System.out.println("귓속말 발견!!!");

		Iterator<String> it = clientMap.keySet().iterator();

		StringTokenizer t1 = new StringTokenizer(s);
		int nTmp1 = s.indexOf(" ");
		String strTmp1 = s.substring(nTmp1 + 1);
		t1.nextToken();
		String strTmp2 = t1.nextToken();
		nTmp1 = strTmp1.indexOf(" ");
		String strTmp3 = strTmp1.substring(nTmp1 + 1);

		// System.out.println("strTmp1: " + strTmp1);
		// System.out.println("strTmp2: " + strTmp2);
		// System.out.println("strTmp3: " + strTmp3); 출력확인용
		try {
			PrintWriter pr = (PrintWriter) clientMap.get(strTmp2); // strTmp2 (귓 받는사람)
			pr.println(name + " (귓속말): " + strTmp3); // (귓하는사람 + 귓속말 + 내용)
		} catch (Exception e) {
			System.out.println("귓속말을 할수가 없습니다." + e);
		}

	}

	// 접속된 모든 클라이언트들에게 메세지를 전달
	public void sendAllMsg(String user, String msg) {

		// 출력스트림을 순차적으로 얻어와서 해당 메세지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(" ")) {
					it_out.println(msg);
				} else {
					it_out.println("[" + user + "]" + msg);
				}
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}

	public void sendMsg(String user, String msg, String toname) {
		System.out.println("귓속말 발견!!!");
		Iterator<String> it = clientMap.keySet().iterator();

		if (!msg.equals("//to")) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(toname);
				it_out.println(user + "(고정 귓속말): " + msg);
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}

	public void sendroomMsg(String user, String msg, String roomname) {
		try {

			String sql = "select id from " + roomname;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println(roomname + "의방에서 대화를 하고있습니다.");

			while (rs.next()) {
				try {
					PrintWriter it_out = (PrintWriter) clientMap.get(rs.getString("id"));

					if (user.equals(" ")) {
						it_out.println(msg);
					} else {
						it_out.println("[" + user + "]" + msg);
					}

				} catch (Exception e) {
					System.out.println("예외:" + e);
				}

			}

		} catch (SQLException sqle) {
			System.out.println("17");
			sqle.printStackTrace();
		}
	}

	public void sendwaitingMsg(String user, String msg) {

		try {

			String sql = "select id from WAITINGROOM ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("대기실 채팅");

			while (rs.next()) {
				try {
					PrintWriter it_out = (PrintWriter) clientMap.get(rs.getString("id"));

					if (user.equals(" ")) {
						it_out.println(msg);
					} else {
						it_out.println("[" + user + "]" + msg);
					}

				} catch (Exception e) {
					System.out.println("예외:" + e);
				}

			}

		} catch (SQLException sqle) {
			System.out.println("17");
			sqle.printStackTrace();
		}
	}

	public void room(String user, String msg) {

		StringTokenizer t1 = new StringTokenizer(msg);
		t1.nextToken();
		String roomname = t1.nextToken();

		System.out.println(roomname + "방 생성되었습니다.");
		System.out.println(user + "님이 방을생성하고 " + roomname + "방에 입장하였습니다.");

		try {

			String sql = "create table " + roomname + " (id varchar(10), " + "password varchar(10))";
			pstmt = con.prepareStatement(sql);

			// int updateCount =
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "delete from waitingroom where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "insert into " + roomname + " values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, "2222");
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "insert into roomlist " + " values (?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomname);
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("3");
			sqle.printStackTrace();
		}
	}

	public void roomdelete(String user, String msg) {

		StringTokenizer t1 = new StringTokenizer(msg);
		t1.nextToken();
		String roomname = t1.nextToken();
		System.out.println(roomname + "방제거");

		try {

			String sql = "drop table " + roomname + " where password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "2222");
			pstmt.executeUpdate();
			// --------------------------------------------------------------------------
			sql = "delete from roomlist  where roomlist = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomname);
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("4");
			sqle.printStackTrace();
		}
	}

	public void roomin(String user, String msg) {

		StringTokenizer t1 = new StringTokenizer(msg);
		t1.nextToken();
		String roomname = t1.nextToken();

		System.out.println(roomname + "의 방에 " + user + "님이 입장하셨습니다.");

		try {
			con = ConnectionPool.getConnection();
			String sql = "delete from waitingroom where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "insert into " + roomname + " values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, "1111");
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("5");
			sqle.printStackTrace();
		}
	}

	public void roomout(String user, String msg) {

		StringTokenizer t1 = new StringTokenizer(msg);
		t1.nextToken();
		String roomname = t1.nextToken();

		System.out.println(roomname + "의 방에 " + user + "님이 나가셨습니다.");

		try {

			String sql = "delete from " + roomname + " where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "insert into waitingroom values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, "1111");
			pstmt.executeUpdate();
			// ----------------------------------------------------------------

		} catch (SQLException sqle) {
			System.out.println("6");
			sqle.printStackTrace();
		}
	}

	public void roomlist(PrintWriter out) {

		String roomlist = "현재방 리스트 [ ";
		try {

			String sql = "select roomlist from roomlist ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomlist = roomlist + (String) rs.getString(1) + ",";
			}
			roomlist = roomlist.substring(0, roomlist.length() - 1) + " ]"; // -1은 , 를 없애기위해서
			out.println(roomlist);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void roominlist(String roomname, PrintWriter out) {
		String roominlist = roomname + "의 방에 접속한 사용자: [";

		try {

			String sql = "select id from " + roomname;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roominlist = roominlist + (String) rs.getString(1) + ",";
			}
			roominlist = roominlist.substring(0, roominlist.length() - 1) + "]"; // -1은 , 를 없애기위해서
			out.println(roominlist);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void myroomlist(String user, String roomname, PrintWriter out) {
		String myroomlist = roomname + "은 님이 방장인 방에 접속한 사용자:[ ";

		try {

			String sql = "select id from " + roomname + " where password = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "2222");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals(user)) {
					sql = "select id from " + roomname;
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						myroomlist = myroomlist + (String) rs.getString(1) + ",";

						myroomlist = myroomlist.substring(0, myroomlist.length() - 1) + "]"; // -1은 , 를 없애기위해서
						out.println(myroomlist);
					}
				} else {
					out.println("당신은 방은 없습니다.");
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void roomsuper(String user, String roomname, String s, PrintWriter out) {

		try {
			StringTokenizer t1 = new StringTokenizer(s);
			t1.nextToken();
			String toname = t1.nextToken();
			// ----------------------------------------------------
			String sql = "update " + roomname + " set password = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "1111");
			pstmt.setString(2, user);
			pstmt.executeUpdate();

			sql = "update " + roomname + " set password = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "2222");
			pstmt.setString(2, toname);
			pstmt.executeUpdate();

			sendroomMsg("공지", "방장의 권한이 변경되었습니다.", roomname);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void waitinglist(PrintWriter out) {
		String waitinglist = "대기실에 접속한 사용자: [";

		try {

			// ----------------------------------------------------

			String sql = "select id from waitingroom ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				waitinglist = waitinglist + (String) rs.getString(1) + ",";
			}
			waitinglist = waitinglist.substring(0, waitinglist.length() - 1) + "]"; // -1은 , 를 없애기위해서
			out.println(waitinglist);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void invitation(String user, String roomname, String toname, PrintWriter out) { // 초대

		String msg = user + "님이 " + roomname + "의 방에 당신을 초대하였습니다 (수락:Y,거절:N)";
		try {

			String sql = "select id from waitingroom ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				try {
					if (rs.getString(1).equals(toname)) {
						PrintWriter it_out = (PrintWriter) clientMap.get(rs.getString(1));

						if (user.equals(" ")) {
							it_out.println(msg);
						} else {
							it_out.println("[" + user + "]" + msg);
						}
					}

				} catch (Exception e) {
					System.out.println("예외:" + e);
				}

			}

		} catch (SQLException sqle) {
			System.out.println("17");
			sqle.printStackTrace();
		}
	}

	public void invitationin(String user, String roomname, String s) {
		String invitationname = s;

		invitationname = "";

		try {
			String sql = "delete from waitingroom where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.executeUpdate();
			// ----------------------------------------------------------------
			sql = "insert into invitation " + " values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, "1111");
			pstmt.executeUpdate();

			// -------------------------------------------------------------------

		} catch (SQLException sqle) {
			System.out.println("5");
			sqle.printStackTrace();
		}
	}

	public void invitationin2(String user, String roomname, String s) {
		String invitationname = "";

		// 출력스트림을 순차적으로 얻어와서 해당 메세지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		// StringTokenizer t1 = new StringTokenizer(msg);
		// String roomname = t1.nextToken();
		//
		try {

			// ----------------------------------------------------

			String sql = "select id from invitation ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				invitationname = invitationname + (String) rs.getString(1);
			}

			while (rs.next()) {
				try {
					PrintWriter it_out = (PrintWriter) clientMap.get(rs.getString(user));

					if (user.equals(" ")) {
						it_out.println(s);
					} else {
						it_out.println("[" + user + "]" + s);
					}

				} catch (Exception e) {
					System.out.println("예외:" + e);
				}

			}

		} catch (SQLException sqle) {
			System.out.println("17");
			sqle.printStackTrace();
		}
	}

	public void forcedexit(String user, String roomname, String toname, String s, PrintWriter out) {

		try {
			String sql = "select id from " + roomname + " where password = 2222";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(user)) {

					sql = "delete from " + roomname + " where id = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, toname);
					pstmt.executeUpdate();
					// ----------------------------------------------------------------
					sql = "insert into waitingroom " + " values(?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, toname);
					pstmt.setString(2, "1111");
					pstmt.executeUpdate();
				} else {
					System.out.println("222222222");
					out.println("당신은 방장이 아닙니다.");
				}
			} else {
				out.println("당신은 방장이 아닙니다.");
			}

			System.out.println("33333333333");

		} catch (

		SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void forcedexit2(String user, String s, PrintWriter out, String s2) {

		try {

			String sql = "select id from waitingroom ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println(user);
			while (rs.next()) {
				if (rs.getString(1).equals(user)) {
//					System.out.println("야야야야야" + user);
					count = 2;
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void END(String s2) {
		s2 = "/out ";
	}

	public void Block(String name, PrintWriter out) {
		Scanner S = new Scanner(System.in);
		String roop = "";

		try {
			// ----------------------------------------------------
			String sql = "select * from BLOCK";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (name.equals(rs.getString("id"))) {
					while (true) {

						System.out.println("차단된 사용자가 접속을 하려고 합니다.");
						out.println("차단된 사용자입니다. 다시 로그인하세요.");
						roop = S.nextLine();

					}
				}
			}
			System.out.println("정상적인 사용자가 로그인하였습니다.");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void DbOpen() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("8");
			cnfe.printStackTrace();
		}
	}

	public void Dblogin(String w) {

		String a = w;

		try {
			// String sql = "create table test2(id varchar(10), " +
			// " password varchar(10))";
			// pstmt = con.prepareStatement(sql);
			// int updateCount = pstmt.executeUpdate();
			// System.out.println("createCount : " + updateCount);
			// ------------------------------------------------------

			String sql = "insert into waitingroom values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a);
			pstmt.setString(2, "1111");
			int updateCount = pstmt.executeUpdate();
			System.out.println("inser tCount: " + updateCount);

			// --------------------------------------------------------
			sql = "select * from waitingroom";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// while (rs.next()) {
			// System.out.print("id: " + rs.getString(1));
			// }

			// //---------------------------------------------------
			//
			// sql = "drop table test2";
			// pstmt = con.prepareStatement(sql);
			// updateCount = pstmt.executeUpdate();
			// System.out.println("dropCount : " + updateCount);

		} catch (SQLException sqle) {
			System.out.println("9");
			sqle.printStackTrace();
		}
	}

	public void Dblogout(String q) {

		// String a = q;

		try {
			// System.out.println("AAAA");
			con = ConnectionPool.getConnection();
			String sql = "delete from waitingroom where id = ? and password = ? ";
			// System.out.println("BBBB");
			pstmt = con.prepareStatement(sql);
			// System.out.println("CCCC");
			pstmt.setString(1, q);
			// System.out.println("DDDD");
			pstmt.setString(2, "1111");
			int updateCount = pstmt.executeUpdate();
			// System.out.println("ㅋㅋㅋ"); 출력확인용
			// sql = "commit";

		} catch (SQLException sqle) {
			System.out.println("10");
			sqle.printStackTrace();
		}
	}

	public int msgCheck(String a, String name) {

		if (a.contains("시발") || a.contains("병신") || a.contains("멍청이") || a.contains("조성준천재")) {
			System.out.println("금칙어를 사용하신분이있습니다.");
			return 1;
		} else {
			return 2;
		}

	}

	public static void main(String[] args) {
		// 서버 객체생성
		MultiServer6 ms = new MultiServer6();
		ms.init();

	}

	//////////////////////////////////////////////////////////////////////////
	// 내부 클래스
	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드
	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;

		// 생성자.
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}

		// 쓰레드를 사용하기 위해서 run()메서드 재정의

		@Override
		public void run() {

			String name = ""; // 클라이언트로부터 받은 이름을 저장할 변수

			try {
				try {

					name = in.readLine(); // 클라이언트에서 처음으로 보내는 메세지는
					Block(name, out);
					String sql = "select id from waitingroom";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					int count = 2;

					while (true) {
						while (rs.next()) {
							// System.out.println("2222222");
							if (name.equals(rs.getString("id"))) {
								// System.out.println("333333333");
								count = 1;
								do {
									System.out.println("중복된 아이디가 접속을 시도하고 있습니다.");
									out.println("이름을 다시입력하세요");
									System.out.println(rs.getString("id"));
									name = in.readLine();
								} while (name.equals(rs.getString("id")));
							} else {
								count = 2;
							}
						}
						if (count == 2) {
							break;
						}
						System.out.println(count);
						rs = pstmt.executeQuery();
						System.out.println("1111111");
					}

				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}

				// 클라이언트가 사용할 이름이다.
				Dblogin(name);

				sendAllMsg(name, "님이 입장하셨습니다.");

				// 현재 객체가 가지고있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림.
				clientMap.put(name, out); // 해쉬맵에서 키를 name으로 출력 스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				// 입력스트림이 null이 아니면 반복

				String s = "";
				String s1 = "";
				String s2 = "";
				while (in != null) {
					s = in.readLine();

					if (s.indexOf("/w ") >= 0) { // 귓속말
						to(name, s);
					} else if (s.indexOf("/to") >= 0) { // 고정귓속말
						int nTmp1 = s.indexOf(" ");
						String strTmp1 = s.substring(nTmp1 + 1);
						String A = strTmp1;
						while (!s.equals("//to")) { // 고정 취소
							s = in.readLine();
							sendMsg(name, s, A);
						}
					}
					// else if (s.indexOf("/block") >= 0) {
					// Block(s);
					// }
					else if (s.equals("/roomlist")) {
						roomlist(out);
					} else if (s.equals("/alllist")) {// 리스트 뽑기
						list(out);
					} else if (s.indexOf("/waitinglist") >= 0) {
						waitinglist(out);
					} else if (s.equals("/notice")) {
						s = in.readLine();
						sendAllMsg(name, s);
					} else if (s.equals("q") || s.equals("Q")) { // 채팅방나가기
						break;
					} else if (s.indexOf("/room ") >= 0) { // 방만들기 /room 방이름
						room(name, s);

						StringTokenizer t1 = new StringTokenizer(s);
						t1.nextToken();
						String roomname = t1.nextToken();
						sendwaitingMsg(name, " 님이 대기실에 퇴장하셨습니다.");
						while (!(s.indexOf("/out") >= 0)) {
							s = in.readLine();

							if (s.equals("/inlist")) {
								roominlist(roomname, out);
							} else if (s.indexOf("/w ") >= 0) { // 귓속말
								to(name, s);
							} else if (s.indexOf("/추방 ") >= 0) {
								StringTokenizer t2 = new StringTokenizer(s);
								t2.nextToken();
								String toname = t2.nextToken();
								forcedexit(name, roomname, toname, s, out);
							} else if (s.equals("/myroomlist")) {
								myroomlist(name, roomname, out);
							} else if (s.equals("/alllist")) {// 리스트 뽑기
								list(out);
							} else if (s.indexOf("/super ") >= 0) {
								roomsuper(name, roomname, s, out);
							} else {
								sendroomMsg(name, s, roomname);
							}
							forcedexit2(name, s, out, s2);
							if (count == 2) {
								s = "/out";
							}
							// else if (s.indexOf("/초대 ") >= 0) {// /초대 방이름 초대받는사람이름
							// StringTokenizer t2 = new StringTokenizer(s);
							// t2.nextToken();
							// String roomname1 = t2.nextToken();
							// String toname = t2.nextToken();
							// invitation(name, roomname1, toname, out);
							// invitationin(toname, roomname,s);
							// s = in.readLine();
							// if ((s.equals("y") && s.equals("Y"))) {
							// invitationin2(name, roomname, s);
							// } else if (s.equals("n") || s.equals("N")) {
							// sendroomMsg(name, "님이", " 초대를 거절하였습니다.");
							// }
							// }
						}
						sendwaitingMsg(name, " 님이 대기실에 입장하셨습니다.");
						if (count != 2) {
							roomout(name, s);
						}
						count = 0;

					} else if (s.indexOf("/deleteroom ") >= 0) { // 방지우기
						roomdelete(name, s);
					} else if (s.indexOf("/in ") >= 0) { // 방입장
						roomin(name, s);
						StringTokenizer t1 = new StringTokenizer(s);
						t1.nextToken();
						String roomname = t1.nextToken();
						sendwaitingMsg(name, " 님이 대기실에 퇴장하셨습니다.");
						out.println(name + "님이 방에 입장하였습니다.");
						while (!(s.indexOf("/out") >= 0)) {
							s = in.readLine();

							System.out.println(s);

							if (s.equals("/inlist")) {
								roominlist(roomname, out);
							} else if (s.equals("/alllist")) {// 리스트 뽑기
								list(out);
							} else if (s.equals("/myroomlist")) {
								myroomlist(name, roomname, out);
							} else if (s.indexOf("/w ") >= 0) { // 귓속말
								to(name, s);
							} else if (s.indexOf("/추방 ") >= 0) {
								StringTokenizer t2 = new StringTokenizer(s);
								t2.nextToken();
								String toname = t2.nextToken();
								forcedexit(name, roomname, toname, s, out);
							} else {
								sendroomMsg(name, s, roomname);
							}
							forcedexit2(name, s, out, s2);
							if (count == 2) {
								s = "/out";
							}
						}
						out.println(name + "님이 방을 퇴장하였습니다.");
						sendwaitingMsg(name, " 님이 대기실에 입장하셨습니다.");

						if (count != 2) {
							roomout(name, s);
						}
						count = 0;

					} else {
						if (msgCheck(s, name) == 1) { // 금칙어사용
							s = name + "님이 금칙어를사용했습니다.";
							sendwaitingMsg(name, s);
						} else {
							System.out.println("[" + name + "]" + s); // 기본
							sendwaitingMsg(name, s);
							// StringTokenizer t1 = new StringTokenizer(s2);
							// t1.nextToken();
							// String roomname = t1.nextToken();
							// sendroomMsg(name,s);
						}
					}

				}

				// System.out.println("Bye....");
			} catch (Exception e) {
				System.out.println("예외처리!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println("예외:" + e);
			} finally {
				// 예외가 발생할때 퇴장. 해쉬맵에서 해당 데이터 제거(remove)
				// 보통 종료하거나 나가면 java.net.SocketException: 예외발생
				clientMap.remove(name);

				// Dblogout(name);

				sendAllMsg(name, "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
				Dblogout(name);
				try {
					in.close();
					out.close();
					socket.close();
				} catch (Exception e) {
					System.out.println("11");
					e.printStackTrace();
				}
			}
		}

	}
}
