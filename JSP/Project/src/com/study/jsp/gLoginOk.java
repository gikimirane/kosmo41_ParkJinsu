package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class gLoginOk implements Service {

	public gLoginOk() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("gloginOk");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String gName = request.getParameter("gName");
		String gMail = request.getParameter("gMail");

		System.out.println(gName);
		System.out.println(gMail);
		int num = gMail.indexOf("@");
		String id = gMail.substring(0, num);
		session.setAttribute("id", id);
		session.setAttribute("name", gName);

		session.setAttribute("ValidMem", "yes");
		session.setAttribute("login", "google");
		
		if(session.getAttribute("bUrl").equals("first.do"))
		{
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"first.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else if(session.getAttribute("bUrl").equals("list.do"))
		{
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"list.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else if(session.getAttribute("bUrl").equals("notice.do"))
		{
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"notice.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else if(session.getAttribute("bUrl").equals("picture.do"))
		{
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"picture.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else if(session.getAttribute("bUrl").equals("modify.do"))
		{
			writer.println("<html><head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"modify.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}
		else
		{
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">\r\n" + 
					"		alert(\"정상적으로 로그인 되었습니다.\");\r\n" + 
					"		document.location.href=\"first.do\";\r\n" + 
					"	</script>");
			writer.println("</body></html>");
			writer.close();
		}

	}

}
