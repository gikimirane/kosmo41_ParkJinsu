package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service {

	public joinOk() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("joinOk");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		BDto dto = new BDto();
		String id = (String)request.getParameter("id"); 
		dto.setId(id);
		String pw = (String)request.getParameter("pw");
		dto.setPw(pw);
		String name = (String)request.getParameter("name");
		dto.setName(name);
		String eMail = (String)request.getParameter("eMail");
		dto.seteMail(eMail);
		String address = (String)request.getParameter("address");
		dto.setAddress(address);
		
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		BDao dao = BDao.getInstance();
		if(dao.confirmId(dto.getId()) == BDao.MEMBER_EXISTENT)
		{
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"아이디가 이미 존재합니다.\");\r\n" + 
					"history.back();\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		} 
		else 
		{
			int ri = dao.insertMember(dto);
			if(ri == BDao.MEMBER_JOIN_SUCCESS) {
				session.setAttribute("id", dto.getId());
				
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"회원가입을 축하 합니다.\");\r\n" + 
						"document.location.href=\"login.jsp\";\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
			} 
			else 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"회원가입에 실패했습니다.\");\r\n" + 
						"document.location.href=\"login.jsp\";\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}

	}

}
