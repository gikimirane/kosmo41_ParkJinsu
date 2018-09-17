package com.study.jsp.chatt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;

public class BPwRoomCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{ 
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("id");
		String rname = request.getParameter("rname");
		String rcount = request.getParameter("rcount");
		String pw = request.getParameter("pw");
		WsServer2 server = WsServer2.getInstance();

		try 
		{
			server.pwRoom(name,rname,rcount,pw,request,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	

	}

}
