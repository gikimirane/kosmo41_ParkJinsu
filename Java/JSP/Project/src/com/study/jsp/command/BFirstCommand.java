package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BDao;
import com.study.jsp.BDto;

public class BFirstCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		
		HttpSession session = request.getSession();
		
		session.setAttribute("first", "yes");
		
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.first();   
		request.setAttribute("best", dtos);
	}

}
