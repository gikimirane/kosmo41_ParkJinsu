package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {

	public loginOk() {
		
	}
 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		System.out.println("loginOk");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
			
		request.setCharacterEncoding("UTF-8");
			
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
			
		BDao dao = BDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == -1) {
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"아이디가 존재하지 않습니다.\");\r\n" + 
					"history.go(-1);\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else if(checkNum == 0) {
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"alert(\"비밀번호가 틀립니다.\");\r\n" + 
					"history.go(-1);\r\n" + 
					"</script>");
			writer.println("</body></html>");
			writer.close();
		} else if(checkNum == 1) 
		{
			BDto dto = dao.getMember(id);
				
			if(dto == null) 
			{
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"존재하지 않는 회원 입니다.\");\r\n" + 
						"history.go(-1);\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
			} else 
			{				
				String name = dto.getName();
					
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("ValidMem", "yes");
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
						"		document.location.href=\"first.do\";\r\n" + 
						"	</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
			
	}
	

}
