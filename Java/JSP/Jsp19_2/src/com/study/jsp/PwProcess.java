package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PmProcess")
public class PwProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	HttpSession session;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{

		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String id,pw,pw1,pw2,dpw;
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		
		id =  (String)session.getAttribute("id");
		pw = request.getParameter("pw");
		
		String query = "select pw from member where id = '"+id+"'";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, upw);
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			rs.next();
			dpw = rs.getString("pw");
			
			if(pw.equals(dpw))
			{
				pw1 = request.getParameter("pw1");
				pw2 = request.getParameter("pw2");
				
				if(pw1.equals(pw2))
				{
					query = "update member set pw = '" + pw2 + "' where id = '" + id + "'";
					Class.forName(driver);
					con = DriverManager.getConnection(url, uid, upw);				
					pstmt = con.prepareStatement(query);
					pstmt.executeQuery();
					
					session.setAttribute("pw", pw2);
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					
					writer.println("[{\"result\":\"ok\",\"desc\":\"none\"}]");
					
				}
				else
				{
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					
					writer.println("[{\"result\":\"fail2\",\"desc\":\"변경된 비밀번호가 다릅니다.\"}]");
				}
			}
			else
			{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				
				writer.println("[{\"result\":\"fail1\",\"desc\":\"현재 비밀번호가 틀립니다.\"}]");
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
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
	}

}
