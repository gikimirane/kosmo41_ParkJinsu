package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormEx2")
public class FormEx2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{		
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String cal = request.getParameter("cal");
		
		if(isNumber(num1, num2))
		{
			doSuccess(num1,num2,cal,request,response);
		}
		else
		{
			doFail(request,response);
		}	
	}
	
	boolean isNumber(String num1, String num2)
	{
		try
		{
			Integer.parseInt(num1);
			Integer.parseInt(num2);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	void doSuccess(String num1, String num2, String cal,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		int number1 = Integer.parseInt(num1);
		int number2 = Integer.parseInt(num2);
		int number3 = 0;
			
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head></head><body>");
		
		if(cal.equals("plus"))
		{
			number3 = number1 + number2;
			out.println(number1 +" + " + number2 + " = " + number3 );
		}
		else if(cal.equals("minus"))
		{
			number3 = number1 - number2;
			out.println(number1 +" - " + number2 + " = " + number3 );
		}
		else if(cal.equals("multi"))
		{
			number3 = number1 * number2;
			out.println(number1 +" x " + number2 + " = " + number3 );
		}
		else if(cal.equals("devide"))
		{
			number3 = number1 / number2;
			out.println(number1 +" / " + number2 + " = " + number3 );
		}
		
		out.println("</body></html>");
		
		out.close();
	}
	
	void doFail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head></head><body>");
		out.println("<h1>숫자를 입력하세요</h1>");
		out.println("</body></html>");
		
		out.close();
	}
}
