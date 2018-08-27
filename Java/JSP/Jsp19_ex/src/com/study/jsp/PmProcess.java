package com.study.jsp;

import java.io.IOException;
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
public class PmProcess extends HttpServlet {
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
					
				}
			}
			else
			{
				
			}
		}
		catch(Exception e)
		{
			
		}
		
	}

}
