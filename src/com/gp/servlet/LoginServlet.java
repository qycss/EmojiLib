package com.gp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gp.sql.UserDao;  

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8"); 
        
        //使用request对象的getSession()获取session，如果session不存在则创建一个
    	HttpSession session = request.getSession();
    	session.setAttribute("loginState", "logout");
        
        /*还可以使用request.getParameterMap()以key-value的形式一次性获得表单
                     提交的数据（key:String value:String[]）*/
        String username = request.getParameter("username");  
        String password = request.getParameter("password");
        
        
        if(username!=""){
	        String psw =new UserDao().findPasswordByUsername(username);
	        
	        if(psw ==null){
	        	request.setAttribute("loginState", "wrong username");
	        	request.getRequestDispatcher("/login.jsp").forward(request, response);     
	            return;  
	        }else if(psw!=null&&!psw.equals(password)){   
	            request.setAttribute("loginState", "wrong password");
	            request.getRequestDispatcher("/login.jsp").forward(request, response); 
	            return;  
	        }else if(psw.equals(password)){
	        	session.setAttribute("loginState", "login");
	        	session.setAttribute("username", username);
	        	System.out.println(session.getAttribute("username"));
	            response.sendRedirect(request.getContextPath()+"/index.jsp");
	        }
        }else{
        	//清除session
        	session.invalidate();
        	//request.getRequestDispatcher("/login.jsp").forward(request, response);
        	response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
	}

}
