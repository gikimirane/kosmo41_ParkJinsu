package com.study.jsp.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.BDao;
import com.study.jsp.BDto;
import com.study.jsp.BPageInfo;

public class BSearchCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String search = request.getParameter("search");
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch(Exception e) {
			
		}
		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.searchPage(nPage, search, request);
		request.setAttribute("page", pinfo); 
		
		nPage = pinfo.getCurPage();
		
		
		
		if(search.equals(""))
		{		
			try
			{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
					
				request.setCharacterEncoding("UTF-8");
				
				writer.println("<html><head><body>");
				writer.println("<script language=\"javascript\">\r\n" + 
						"alert(\"검색어를 입력하세요.\");\r\n" + 
						"history.go(-1);\r\n" + 
						"</script>");
				writer.println("</body></html>");
				writer.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return;
		}
		if(request.getParameter("bSearch").equals("bTitle"))
		{		
			ArrayList<BDto> dtos = dao.tSearch(search, nPage);
			request.setAttribute("list", dtos);
		}
		else
		{
			ArrayList<BDto> dtos = dao.iSearch(search, nPage);
			request.setAttribute("list", dtos);
		}
		  
	
	}

}
