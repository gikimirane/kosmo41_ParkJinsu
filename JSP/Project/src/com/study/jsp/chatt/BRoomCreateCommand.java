package com.study.jsp.chatt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;

public class BRoomCreateCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{ 
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("id");
		String rname = request.getParameter("rname");
		String rcount = request.getParameter("rcount");

		WsServer2 server = WsServer2.getInstance();
		
		try 
		{
			server.roomCreate(name,rname,rcount,request,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	

	}

}
