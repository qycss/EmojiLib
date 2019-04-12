package com.gp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	public String findPasswordByUsername(String username) {
		String psw = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/gp_db?&useSSL=false&serverTimezone=UTC";
			String user="root";
			String password="123456";
			//Class.forName(className)可以简单的理解为：获得字符串参数中指定的类，并初始化该类
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			//sql语句不再采用拼接方式，应用占位符问号的方式写sql语句。
			String sql = "select * from users where name=?";
			//对占位符设置值，占位符顺序从1开始，第一个参数是占位符的位置，第二个参数是占位符的值。
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,username);
			//rs是结果集。查询出的记录是一个列表，初始时指针指向的是第一条记录之前的。
			rs = pstmt.executeQuery();
			if(rs == null){
				return null;
			}
			//每rs.next()一次指针都会向后移动一位，指向下一条记录。
			if(rs.next()){
				//取出记录中字段“password”所对应的值
				psw = rs.getString("password");
			}else{
				psw = null; 
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}
			catch(SQLException e){
			}
		}
		return psw;
	}
	
	
}
