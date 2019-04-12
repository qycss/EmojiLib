<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB2312">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql:"
					+ "//localhost:3306/gp_db?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT",
					"root", "123456");
// 			String url = "jdbc:mysql://localhost:3306/gb_db?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT"; 
			String sql = "select binfile from bindata where filename='01'";
			out.clearBuffer();
			out = pageContext.pushBody();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				response.setContentType("image/jpeg");
				Blob b = rs.getBlob("binfile");
				long size = b.length();
				byte bs[] = b.getBytes(1, (int) size);
				OutputStream outs = response.getOutputStream();
				outs.write(bs);
				outs.flush();
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>
