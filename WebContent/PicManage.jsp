
<%@page import="javafx.scene.control.Alert"%>
<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<%@ page import="java.nio.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
    <body>
     <%
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //加载JDBC驱动程序
        String url = "jdbc:mysql://localhost:3306/gp_db?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT"; 
        String  user="root";    
        String  password="123456";       //密码为自己数据库的密码  
        
        request.setCharacterEncoding("UTF-8");
        String filename=request.getParameter("image");

        String label=request.getParameter("label");
        System.out.println(label);
        File file = new File(filename); //获取表单传过来的图片的url  

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
			String sqlstr = "insert into bindata (binfile,label) values(?,'"+label+"')";
			PreparedStatement pstmt = conn.prepareStatement(sqlstr);//预处理
			
			pstmt.setBytes(1,content);
			pstmt.executeUpdate();
			pstmt.close();
			
//			关闭文件
			fin.close();
			out.println("恭喜，已经将新的记录成功地添加到数据库中！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
   %> 
 
    </body>
 
</html>
 
 
