package com.gp.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
public class EmojiDao {
	public void addEmoji(String filename) throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/gp_db?&useSSL=false&serverTimezone=UTC";
		String  user="root";    
	    String  password="wjy981208";       //密码为自己数据库的密码   
	    File file = new File(filename);//获取表单传过来的图片的url     
	    System.out.println(filename);
	    try {
			//打开文件
			FileInputStream fin = new FileInputStream(file);
			//建一个缓冲保存数据
			ByteBuffer nbf = ByteBuffer.allocate((int) file.length());
			byte[] array = new byte[1024];
			int offset = 0, length = 0;
			//读存数据
			while ((length = fin.read(array)) > 0) {
				if (length != 1024)
					nbf.put(array, 0, length);
				else
					nbf.put(array);
				offset += length;
			}
		  //新建一个数组保存要写的内容
			byte[] content = nbf.array();
			//创建数据库连接
			Connection  conn=  DriverManager.getConnection(url,user,password);
			//保存数据
			Statement stmt =conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sqlstr = "select * from bindata where filename='01'";
			ResultSet rs = stmt.executeQuery(sqlstr);
			if (rs.next()) 
			{
				rs.updateBytes(2, content);
				rs.updateRow();
			} else {
				rs.moveToInsertRow();
				rs.updateString(1, "01");
					rs.updateBytes(2, content);
					rs.insertRow();
				}
	 
				rs.close();
//						关闭文件
			fin.close();
			System.out.println("恭喜，已经将新的记录成功地添加到数据库中！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

			 
	


