package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {

	public modifyOk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("modifyOk");
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
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
			
		id = (String)session.getAttribute("id");
		dto.setId(id);
		
		MemberDAO dao= MemberDAO.getInstance();
		int ri = dao.updateMember(dto);
		
		if(ri == 1) {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"정보가 수정되었습니다.\");\r\n" + 
					"document.location.href=\"main.jsp\";\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else 
		{
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정보수정에 실패했습니다.\");\r\n" + 
					"		history.go(-1);\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}

	}

}
