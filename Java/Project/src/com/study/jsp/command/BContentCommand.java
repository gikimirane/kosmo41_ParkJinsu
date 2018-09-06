package com.study.jsp.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BDao;
import com.study.jsp.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{	
		String bId = request.getParameter("bId");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId,request);
		request.setAttribute("content_view", dto); 
	}

}
