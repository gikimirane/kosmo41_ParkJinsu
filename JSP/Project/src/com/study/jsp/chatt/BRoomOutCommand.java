package com.study.jsp.chatt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;

public class BRoomOutCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{ 
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("id");
		
		
		WsServer2 server = WsServer2.getInstance();
		server.roomOut(name,request,response);
	

	}

}
