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
	    String  password="wjy981208";       //����Ϊ�Լ����ݿ������   
	    File file = new File(filename);//��ȡ����������ͼƬ��url     
	    System.out.println(filename);
	    try {
			//���ļ�
			FileInputStream fin = new FileInputStream(file);
			//��һ�����屣������
			ByteBuffer nbf = ByteBuffer.allocate((int) file.length());
			byte[] array = new byte[1024];
			int offset = 0, length = 0;
			//��������
			while ((length = fin.read(array)) > 0) {
				if (length != 1024)
					nbf.put(array, 0, length);
				else
					nbf.put(array);
				offset += length;
			}
		  //�½�һ�����鱣��Ҫд������
			byte[] content = nbf.array();
			//�������ݿ�����
			Connection  conn=  DriverManager.getConnection(url,user,password);
			//��������
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
//						�ر��ļ�
			fin.close();
			System.out.println("��ϲ���Ѿ����µļ�¼�ɹ�����ӵ����ݿ��У�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

			 
	


