package com.study.jsp;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BFirstCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BSearchCommand;
import com.study.jsp.command.BUploadCommand;
import com.study.jsp.command.BWriteCommand;



@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
        super();
    }

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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		BDto dto = null;
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();		
		String com = uri.substring(conPath.length());
		
		Service service = null;
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		
		String bUrl = (String)session.getAttribute("bUrl");  
		if(session.getAttribute("cpage") != null)
		{
			curPage = (int)session.getAttribute("cpage");
		}
			
		if(com.equals("/write_view.do"))
		{
			if(bUrl.equals("notice.do"))
			{
				if(session.getAttribute("id").equals("master"))
				{
					viewPage = "notice_write.jsp";
				}
				else
				{
					writer.println("<html><head><body>");
					writer.println("<script language=\"javascript\">\r\n" + 
							"alert(\"운영자만 접근할 수 있습니다.\");\r\n" + 
							"history.go(-1);\r\n" + 
							"</script>");
					writer.println("</body></html>");
					writer.close();
				}
			}
			else if(bUrl.equals("list.do"))
			{
				viewPage = "write_view.jsp";
			}
			else if(bUrl.equals("picture.do"))
			{
				viewPage = "picture_write.jsp";
			}
			
		}
		else if(com.equals("/write.do"))
		{
			if(session.getAttribute("bUrl").equals("list.do"))
			{
				request.setAttribute("bCheck", "list");
				command = new BWriteCommand();
				command.execute(request, response);
				viewPage = "list.do";
			}
			else if(session.getAttribute("bUrl").equals("notice.do"))
			{
				request.setAttribute("bCheck", "notice");
				command = new BWriteCommand();
				command.execute(request, response);
				viewPage = "notice.do";
			}
			else if(session.getAttribute("bUrl").equals("picture.do"))
			{
				request.setAttribute("bCheck", "picture");
				command = new BWriteCommand();
				command.execute(request, response);
				viewPage = "picture.do";
			}
		}
		else if(com.equals("/list.do"))
		{   
			session.setAttribute("bUrl", "list.do");
			request.setAttribute("bCheck", "list");
			command = new BListCommand();
			command.execute(request, response);			
			viewPage = "list.jsp";
		}
		else if(com.equals("/picture.do"))
		{   
			session.setAttribute("bUrl", "picture.do");
			request.setAttribute("bCheck", "picture");
			command = new BListCommand();
			command.execute(request, response);			
			viewPage = "picture.jsp";
		}
		else if(com.equals("/content_view.do"))
		{        
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}
		else if(com.equals("/modify_view.do"))
		{        
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		}
		else if(com.equals("/modify.do"))
		{       
			command = new BModifyCommand();    
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";  
		}
		else if(com.equals("/delete.do"))
		{        
			command = new BDeleteCommand();
			command.execute(request, response);
			if(session.getAttribute("bUrl").equals("list.do"))
				viewPage = "list.do?page="+curPage; 
			else if(session.getAttribute("bUrl").equals("notice.do"))
				viewPage = "notice.do?page="+curPage;	
			
		}
		else if(com.equals("/reply_view.do"))
		{        
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		}
		else if(com.equals("/reply.do"))
		{
			if(session.getAttribute("bUrl").equals("list.do"))
			{
				request.setAttribute("bCheck", "list");
				command = new BReplyCommand();
				command.execute(request, response);				
				viewPage = "list.do?page="+curPage; 
			}
			
			else if(session.getAttribute("bUrl").equals("notice.do"))
			{
				request.setAttribute("bCheck", "notice");
				command = new BReplyCommand();
				command.execute(request, response);	
				viewPage = "notice.do?page="+curPage;	
			}
				
		}
		else if(com.equals("/joinOk.do"))
		{
			service = new joinOk();
			service.execute(request, response);
		}
		else if(com.equals("/modifyOk.do"))
		{
			service = new modifyOk();
			service.execute(request, response);
		}
		else if(com.equals("/loginOk.do"))
		{
			service = new loginOk();
			service.execute(request, response);
		}
		else if(com.equals("/logout.do"))
		{	
			logoutOk(request, response);
			viewPage="first.jsp";
		}	
		else if(com.equals("/first.do"))
		{ 	
			command = new BFirstCommand();  
			command.execute(request, response);
			viewPage = "first.jsp";
		}
		else if(com.equals("/search.do"))
		{
			if(session.getAttribute("bUrl").equals("list.do"))
			{
				request.setAttribute("bCheck", "list");
				command = new BSearchCommand(); 
				command.execute(request, response);
				viewPage = "list.jsp";
			}
			
			else if(session.getAttribute("bUrl").equals("notice.do"))
			{
				request.setAttribute("bCheck", "notice");
				command = new BSearchCommand(); 
				command.execute(request, response);
				viewPage = "notice.jsp";
			}
			else if(session.getAttribute("bUrl").equals("picture.do"))
			{
				request.setAttribute("bCheck", "picture");
				command = new BSearchCommand(); 
				command.execute(request, response);
				viewPage = "picture.jsp";
			}
					
		}
		else if(com.equals("/notice.do"))
		{
			request.setAttribute("bCheck", "notice");
			command = new BListCommand(); 
			command.execute(request, response);
			viewPage="notice.jsp";
		}
		else if(com.equals("/picture_upload.do"))
		{
			request.setAttribute("bCheck", "picture");
			command = new BUploadCommand(); 
			command.execute(request, response);
			viewPage="picture.do";
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
	public void logoutOk (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		
		
	}
}
