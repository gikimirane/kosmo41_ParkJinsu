package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BDao;
import com.study.jsp.BDto;

public class BUploadCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();

		BDao dao = BDao.getInstance();
		
		ArrayList<BDto> dto = dao.upLoad(request, response);  
		
		String bName = (String)session.getAttribute("bName");
		String bTitle = (String)session.getAttribute("bTitle");
		String bContent = (String)session.getAttribute("bContent");
		
		dao.upWrite(bName, bTitle, bContent, request, dto);
		
	}

}
