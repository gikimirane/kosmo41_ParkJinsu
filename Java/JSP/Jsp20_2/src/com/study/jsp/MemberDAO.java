package com.study.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String uid = "scott";
//	private String upw = "tiger";
	
	private DataSource dataSource;
	
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			//lookup 함수의 파라미터는 context.xml에 설정된 name과 동일해야 한다.
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}
		catch(Exception e) {
			System.out.println("========================\n");
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> memberSelect() {
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//con = DriverManager.getConnection(url, uid, upw);
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member");
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				
				MemberDTO dto = new MemberDTO(id, pw, name, phone, gender);
				dtos.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				if(rs != null) rs.close();
				if(stmt != null)stmt.close();
				if(con != null)con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	public ArrayList<MemberDTO> memberSelect02() {
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member where id = 'abcde'");
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				
				MemberDTO dto = new MemberDTO(id, pw, name, phone, gender);
				dtos.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				if(rs != null) rs.close();
				if(stmt != null)stmt.close();
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
