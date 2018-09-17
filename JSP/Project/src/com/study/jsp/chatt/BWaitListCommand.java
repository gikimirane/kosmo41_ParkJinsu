package com.study.jsp.chatt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;

public class BWaitListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		String rn = request.getParameter("roomList");
		String name = (String)session.getAttribute("id");
		
		
		WsServer2 server = WsServer2.getInstance();
		server.roomIn(rn, name,request, response);
		 
//		BDao dao = BDao.getInstance();
//		dao.roomIn(rn, name , request,response); 
	}

}
