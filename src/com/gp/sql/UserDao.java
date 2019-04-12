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
			//Class.forName(className)���Լ򵥵����Ϊ������ַ���������ָ�����࣬����ʼ������
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			//sql��䲻�ٲ���ƴ�ӷ�ʽ��Ӧ��ռλ���ʺŵķ�ʽдsql��䡣
			String sql = "select * from users where name=?";
			//��ռλ������ֵ��ռλ��˳���1��ʼ����һ��������ռλ����λ�ã��ڶ���������ռλ����ֵ��
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,username);
			//rs�ǽ��������ѯ���ļ�¼��һ���б���ʼʱָ��ָ����ǵ�һ����¼֮ǰ�ġ�
			rs = pstmt.executeQuery();
			if(rs == null){
				return null;
			}
			//ÿrs.next()һ��ָ�붼������ƶ�һλ��ָ����һ����¼��
			if(rs.next()){
				//ȡ����¼���ֶΡ�password������Ӧ��ֵ
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
