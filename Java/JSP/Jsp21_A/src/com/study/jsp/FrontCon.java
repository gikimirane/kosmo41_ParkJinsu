package com.study.jsp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public FrontCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
		
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("actionDo");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDTO dto = null;
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		
		if(command.equals("/joinOk.do"))
		{
			System.out.println("joinOk");
			System.out.println("------------------");
			
			joinOk(request, response);
		}
		else if(command.equals("/modifyOk.do"))
		{
			System.out.println("modify");
			System.out.println("------------------");
			modifyOk(request, response);
		}
		else if(command.equals("/loginOk.do"))
		{
			System.out.println("login");
			System.out.println("------------------");
			
			loginOk(request, response);
		}
		else if(command.equals("/logout.do"))
		{
			System.out.println("login");
			System.out.println("------------------");
			
			logoutOk(request, response);
		}

	}
	
	public void loginOk (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("loginOk");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
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
			MemberDTO dto = dao.getMember(id);
			
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
						"		document.location.href=\"main.jsp\";\r\n" + 
						"	</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
		
	}
	
	public void modifyOk (HttpServletRequest request, HttpServletResponse response)
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
	
	public void joinOk (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("joinOk");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
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
		
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		MemberDAO dao = MemberDAO.getInstance();
		if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT)
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
			if(ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
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
	public void logoutOk (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		response.sendRedirect("login.jsp");
		
		
	}
	

}
