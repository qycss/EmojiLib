package com.gp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8"); 
        
        //ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
    	HttpSession session = request.getSession();
    	String state = (String) session.getAttribute("loginState");
    	
    	if (state =="login")
    	{
    		String username = (String) session.getAttribute("username");
    	}
	}
}
    	