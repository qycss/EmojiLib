
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
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //����JDBC��������
        String url = "jdbc:mysql://localhost:3306/gp_db?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT"; 
        String  user="root";    
        String  password="123456";       //����Ϊ�Լ����ݿ������  
        
        request.setCharacterEncoding("UTF-8");
        String filename=request.getParameter("image");

        String label=request.getParameter("label");
        System.out.println(label);
        File file = new File(filename); //��ȡ����������ͼƬ��url  

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
			String sqlstr = "insert into bindata (binfile,label) values(?,'"+label+"')";
			PreparedStatement pstmt = conn.prepareStatement(sqlstr);//Ԥ����
			
			pstmt.setBytes(1,content);
			pstmt.executeUpdate();
			pstmt.close();
			
//			�ر��ļ�
			fin.close();
			out.println("��ϲ���Ѿ����µļ�¼�ɹ�����ӵ����ݿ��У�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
   %> 
 
    </body>
 
</html>
 
 
