package com.study.jsp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class BDao {
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;

	private static BDao instance = new BDao();
	DataSource dataSource = null;
	
	int listCount = 15;	// 한페이지 당 보여줄 게시물의 갯수
	int pageCount = 10;	// 하단에 보여줄 페이지 리스트의 갯수
	
	
	private BDao() {
		try
		{
			//lookup 함수의 파라미터는 context.xml에 설정된 
			//name(jdbc/Oracle11g)과 동일해야 한다.
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static BDao getInstance() {
		return instance;
	}
	
	public int insertMember(BDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values(?, ?, ?, ?, ?, ?)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri = BDao.MEMBER_JOIN_SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int confirmId(String id)
	{
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next())
			{
				ri = BDao.MEMBER_EXISTENT;
			}
			else
			{
				ri = BDao.MEMBER_NONEXISTENT;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				set.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw)
	{
		int ri = 0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next())
			{
				dbPw = set.getString("pw");
				if(dbPw.equals(pw))
				{
					System.out.println("login ok");
					ri = BDao.MEMBER_LOGIN_SUCCESS; //로그인 ok
				}
				else
				{
					System.out.println("login fail");
					ri = BDao.MEMBER_LOGIN_PW_NO_GOOD; // 비밀번호 x
				}
			}
			else
			{
				System.out.println("login fail");
				ri = BDao.MEMBER_LOGIN_IS_NOT; //아이디 X
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				set.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public BDto getMember(String id)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id = ?";
		BDto dto = null;
		
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next())
			{
				dto = new BDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				set.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int updateMember(BDto dto)
	{
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update members set pw=?, eMail=?,address=? where id=?";
		
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	
	public void write(String bName, String bTitle, String bContent,HttpServletRequest request) 
	{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		try {
			
			if(bUrl.equals("list.do"))
			{
				String query = "insert into mvc_board " + 
						   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bcheck) " +
						   " values " +
						   " (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, 'list')";
				
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				int rn = pstmt.executeUpdate();
			}
			else if(bUrl.equals("notice.do"))
			{
				String query = "insert into mvc_board " + 
						   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bcheck) " +
						   " values " +
						   " (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, 'notice' )";
				
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				int rn = pstmt.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	
	
	public ArrayList<BDto> list(int curPage,HttpServletRequest request)
	{
		 
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try 
		{
			if(bUrl.equals("list.do"))
			{
				con = dataSource.getConnection();
				String query = "select * " +
							   "  from (select rownum num, A.*" +
							   "  from (select * from mvc_board where bcheck = 'list'" + //검색어 추가
							   "  order by bgroup desc, bstep asc) A " + 
							   "  where rownum <= ?) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);		
				rs = pstmt.executeQuery();
					
				while(rs.next()) {
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
										bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
			}
			else if(bUrl.equals("picture.do"))
			{
				con = dataSource.getConnection();
				String query = "select * " +
							   "  from (select rownum num, A.*" +
							   "  from (select * from mvc_board where bcheck = 'picture'" + //검색어 추가
							   "  order by bgroup desc, bstep asc) A " + 
							   "  where rownum <= ?) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);		
				rs = pstmt.executeQuery();
					
				while(rs.next()) {
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");

					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
										bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
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
	
	public BPageInfo articlePage(int curPage, boolean check,HttpServletRequest request)
	{
		// 로직
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		
		
		//총 게시물의 갯수
		int totalCount = 0;
		try 
		{
			con = dataSource.getConnection();
			if(check) 
			{
				if(bUrl.equals("list.do"))
				{			
					String query = "select count(*) as total from mvc_board where bcheck = 'list' ";
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
				else if(bUrl.equals("picture.do"))
				{
					String query = "select count(*) as total from mvc_board where bcheck = 'picture' ";
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
				
			}
			else
			{
				con = dataSource.getConnection();
				
				String query = "select count(*) as total from mvc_board where bcheck = 'notice'";
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					totalCount = rs.getInt("total");
				}
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
		// 총 페이지 수
			
		int totalPage = totalCount / listCount;
			
		if(totalCount % listCount > 0)
			totalPage++;
			
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage <= 1)
			myCurPage = 1;
			
		//시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		//끝 페이지
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage) 
		    endPage = totalPage;

		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		// set
		return pinfo;
	}
	
	public BDto contentView(String strID,HttpServletRequest request)
	{
		
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		
		
		try {
			HttpSession session = request.getSession();
			String sid = (String)session.getAttribute("id");
			
			if(session.getAttribute("check") != null)
			{
				session.removeAttribute("check");
			}
			
			con = dataSource.getConnection();
			String query = "select bId from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				check = true;
			}
			
			if(check)
			{
				query ="select * from mvc_board where bId = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(strID));
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
					if(sid.equals(bName) || sid.equals("master"))
					{
						session.setAttribute("check", "yes");
					}
				}
			}
			else
			{
				query ="select * from mvc_board where bId = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(strID));
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
									bHit, bGroup, bStep, bIndent, bCheck);
					
					if(sid.equals(bName) || sid.equals("master"))
					{
						session.setAttribute("check", "yes");
					}
				}
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
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) 
	{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update mvc_board " +
					   "	set bName = ?, " +
					   "		bTitle = ?, " +
					   "		bContent = ? " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			int rn = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	public void upHit(String bId)
	{
		boolean check = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con = dataSource.getConnection();

			
			if(check)
			{
				String query ="update mvc_board set bHit = bHit + 1 where bId = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(bId));
				int rn = pstmt.executeUpdate();
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query ="delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String str,HttpServletRequest request)
	{
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		try {
			con = dataSource.getConnection();
			
			String query ="select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bCheck = rs.getString("bCheck");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
								bHit, bGroup, bStep, bIndent,bCheck);
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
		return dto;
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent,
						String bGroup, String bStep, String bIndent,HttpServletRequest request)
	{
		replyShape(bGroup, bStep);
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			if(bUrl.equals("list.do"))
			{
				String query = "insert into mvc_board " +
						   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent, bcheck)" +
						   " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?,'list')";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	private void replyShape(String strGroup, String strStep)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "update mvc_board " +
						   "   set bStep = bStep + 1 " +
						   " where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));

			
			int rn = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto> first()
	{	
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
				con = dataSource.getConnection();
				
				String query = "select * from (select * from mvc_board where bcheck = 'list' order by bhit desc) where rownum <= 3";
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");
	
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
				
					dtos.add(dto);
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
	
	public ArrayList<BDto> noticeCheck()
	{	
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
				con = dataSource.getConnection();
				
				String query = "select * from (select * from mvc_board where bcheck = 'notice' order by bid desc) where rownum <= 3";
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
				
					dtos.add(dto);
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
	
	public ArrayList<BDto> tSearch(String search, int curPage,HttpServletRequest request)
	{
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			if(bUrl.equals("list.do"))
			{
				String str = "'%" + search + "%'";
				String query = "select * " +
						   "  from (select rownum num, A.*" +
						   "  from (select * from mvc_board " + //검색어 추가
						   "  where bcheck = 'list' and bTitle like "+ str +
						   "  order by bgroup desc, bstep asc) A " + 
						   "  where rownum <= ?) B " +
						   "  where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				//pstmt.setString(1, search);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) 
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
			}
			else if(bUrl.equals("notice.do"))
			{
				String str = "'%" + search + "%'";
				String query = "select * " +
						   "  from (select rownum num, A.*" +
						   "  from (select * from mvc_board " + //검색어 추가
						   "  where bcheck = 'notice' and bTitle like "+str+
						   "  order by bgroup desc, bstep asc) A " + 
						   "  where rownum <= ?) B " +
						   "  where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				//pstmt.setString(1, "'%"+search+"%'");
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) 
				{
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
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
	
	public ArrayList<BDto> iSearch(String search, int curPage,HttpServletRequest request)
	{
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try 
		{
			con = dataSource.getConnection();
			if(bUrl.equals("list.do"))
			{
				String str = "'%" + search + "%'";
				String query = "select * " +
						   "  from (select rownum num, A.*" +
						   "  from (select * from mvc_board " + //검색어 추가
						   "  where bcheck='list' and bName like "+str+
						   "  order by bgroup desc, bstep asc) A " + 
						   "  where rownum <= ?) B " +
						   "  where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				//pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
			}
			else if(bUrl.equals("notice.do"))
			{
				String str = "'%" + search + "%'";
				String query = "select * " +
						   "  from (select rownum num, A.*" +
						   "  from (select * from mvc_board " + //검색어 추가
						   "  where bcheck='notice' and bName like "+str+
						   "  order by bgroup desc, bstep asc) A " + 
						   "  where rownum <= ?) B " +
						   "  where B.num >= ?";
				
				pstmt = con.prepareStatement(query);
				//pstmt.setString(1, search);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Timestamp bDate = rs.getTimestamp("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					String bCheck = rs.getString("bCheck");
					
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
							bHit, bGroup, bStep, bIndent, bCheck);
					dtos.add(dto);
				}
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
	public BPageInfo searchPage(int curPage, String search, HttpServletRequest request)
	{
		// 로직
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String bUrl = (String)session.getAttribute("bUrl");
		//총 게시물의 갯수
		int totalCount = 0;
		try 
		{
			if(request.getParameter("bSearch").equals("bTitle"))
			{
				con = dataSource.getConnection();
				if(bUrl.equals("list.do"))
				{
					String str = "'%" + search + "%'";
					String query = "select count(*) as total from mvc_board where bcheck = 'list' and bTitle like "+str;
					pstmt = con.prepareStatement(query);
					//pstmt.setString(1, "%"+search+"%");
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
				else if(bUrl.equals("notice.do"))
				{
					String str = "'%" + search + "%'";
					String query = "select count(*) as total from mvc_board where bcheck = 'notice' and bTitle like "+str;
					pstmt = con.prepareStatement(query);
					//pstmt.setString(1, "%"+search+"%");
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
				
			}
			else
			{
				con = dataSource.getConnection();
				if(bUrl.equals("list.do"))
				{
					String str = "'%" + search + "%'";
					String query = "select count(*) as total from mvc_board where bcheck = 'list' and bName like "+str;
					pstmt = con.prepareStatement(query);
					//pstmt.setString(1, "%"+search+"%");
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
				else if(bUrl.equals("notice.do"))
				{
					String str = "'%" + search + "%'";
					String query = "select count(*) as total from mvc_board where bcheck = 'notice' and bName like "+str;
					pstmt = con.prepareStatement(query);
					//pstmt.setString(1, "%"+search+"%");
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						totalCount = rs.getInt("total");
					}
				}
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
		// 총 페이지 수
			
		int totalPage = totalCount / listCount;
			
		if(totalCount % listCount > 0)
			totalPage++;
			
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
			
		//시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		//끝 페이지
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage) 
		    endPage = totalPage;

		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		// set
		return pinfo;
	}
	
	public BDto notice(String id,HttpServletRequest request, HttpServletResponse response)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;
		
		
		try
		{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			request.setCharacterEncoding("UTF-8");
			
			con = dataSource.getConnection();
			
			String query = "select id from members where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dto = new BDto();
				dto.setId(rs.getString("id"));
			}
			
			if(!dto.getId().equals("master"))
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"접근 할 수 없는 아이디입니다.\");\r\n" + 
						"history.back();\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
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
		return dto;
	}
	
	public ArrayList<BDto> noticeList(int curPage)
	{
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try 
		{
			con = dataSource.getConnection();
			String query = "select * " +
						   "  from (select rownum num, A.*" +
						   "  from (select * from mvc_board where bcheck = 'notice' " + //검색어 추가
						   "  order by bgroup desc, bstep asc) A " + 
						   "  where rownum <= ?) B " +
						   " where B.num >= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);		
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
					
				String bCheck = rs.getString("bCheck");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent, bCheck);
				dtos.add(dto);
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
	
	
	
}
