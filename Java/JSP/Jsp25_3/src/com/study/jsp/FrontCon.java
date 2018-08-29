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
		
		Service service = null;
		
		if(command.equals("/joinOk.do"))
		{
			service = new joinOk();
			service.execute(request, response);
		}
		else if(command.equals("/modifyOk.do"))
		{
			service = new modifyOk();
			service.execute(request, response);
		}
		else if(command.equals("/loginOk.do"))
		{
			service = new loginOk();
			service.execute(request, response);
		}
		else if(command.equals("/logout.do"))
		{
			System.out.println("login");
			System.out.println("------------------");
			
			logoutOk(request, response);
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
