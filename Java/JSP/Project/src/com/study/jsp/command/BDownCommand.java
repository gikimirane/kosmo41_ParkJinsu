package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.BDao;

public class BDownCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		BDao dao = BDao.getInstance();
		//sysFile 만 있음
		dao.downLoad(request, response);  

		
	}

}
