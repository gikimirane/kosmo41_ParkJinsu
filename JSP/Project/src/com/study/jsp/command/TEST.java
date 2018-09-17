package com.study.jsp.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.study.jsp.BDao;
import com.study.jsp.BDto;

public class TEST {
	private static TEST instance = new TEST();
	DataSource dataSource = null;
	public TEST() {
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
	public static TEST getInstance() {
		return instance;
	}
	public BDto getArticle(int idx)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from mvc_board where bId = ?";
		BDto dto = null;	
		try
		{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dto = new BDto();
				dto.setbId(rs.getInt("bId"));
				dto.setbName(rs.getString("bName"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbGroup(rs.getInt("bGroup")); 
				dto.setbStep(rs.getInt("bStep")); 
				dto.setbIndent(rs.getInt("bIndent")); 
				dto.setbCheck(rs.getString("bCheck")); 
				dto.setFileName(rs.getString("fileName")); 
				dto.setSysFile(rs.getString("sysFile"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				rs.close();
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
	
	public String downLoad(HttpServletRequest request, HttpServletResponse response)
	{
		
		try
		{
			request.setCharacterEncoding("UTF-8");
			
			int idx = Integer.parseInt(request.getParameter("bId"));
			BDto article = TEST.getInstance().getArticle(idx);
			
			String orifile = article.getFileName();
			String filename = article.getSysFile();
			
			String uploadFileName = request.getRealPath("/PUpload/"+filename);
			
			FileInputStream fis = new FileInputStream(uploadFileName);
			
			File downFile = new File(uploadFileName);
			
			if(downFile.exists() && downFile.isFile()) {
				try {
					long filesize = downFile.length();
					
					response.setContentType("application/octet-stream");
					response.setContentLength((int)filesize);
					String strClient = request.getHeader("user-agent");
					
					if(strClient.indexOf("MSIE 5.5") != -1)
					{
						response.setHeader("Content-Disposition", "filename="+filename+";");
					}
					else {
						filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
						response.setHeader("Content-Disposition", "attachment; filename=\""+ filename+"\";");

					}
					response.setHeader("Content-Length", String.valueOf(filesize));
					response.setHeader("Content-Transfer-Encoding", "binary;");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "private");
					
					byte b[] = new byte[1024];
					
					BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downFile));
					BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
					
					int read = 0;
					
					while((read = fin.read(b)) != -1) {
						outs.write(b, 0, read);
					}
					outs.flush();
					outs.close();
					fin.close();
				}
				catch(Exception e) {
					e.getMessage();
				}
			}
			else {
				System.out.println("download error : downfile error ["+downFile+"]");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		return null;
	}
}
