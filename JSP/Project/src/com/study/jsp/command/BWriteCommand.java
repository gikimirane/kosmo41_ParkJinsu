package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.BDao;
import com.study.jsp.FDto;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String fileName = "";
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		fileName = request.getParameter("filename");
		
		ArrayList<FDto> dto = new ArrayList<>();
		
		BDao dao = BDao.getInstance();
		dao.write(bName, bTitle, bContent, request,dto); 
	}

}
