package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BDao;
import com.study.jsp.FDto;

public class BUploadCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		String fileName = "";
		BDao dao = BDao.getInstance();
		
		ArrayList<FDto> dto = dao.upLoad(request, response); 
		
		String bName = (String)session.getAttribute("bName");
		String bTitle = (String)session.getAttribute("bTitle");
		String bContent = (String)session.getAttribute("bContent");
		
		dao.write(bName, bTitle, bContent, request, dto);
		
	}

}
