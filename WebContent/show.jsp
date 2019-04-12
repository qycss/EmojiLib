<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.image.AffineTransformOp"%>
<%@page import="java.awt.geom.AffineTransform"%>



<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB2312">
<link href="css/index.css" rel="stylesheet" type="text/css">
<title>show</title>
</head>
<body>
	<input type="button" name="toindex" id="btnlogin" value="Index"
		onclick="window.location.href='index.jsp'" />
	<br>

	<%
		int nw = 400;
		int nh = 300;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			request.setCharacterEncoding("UTF-8");
			String tagname = request.getParameter("tagsearchinput");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql:"
					+ "//127.0.0.1:3306/gp_db?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT",
					"root", "mysql1117");
			String sql = "select binfile from bindata where label=?";
			out.clearBuffer();
			out = pageContext.pushBody();

			//Statement stmt = connection.createStatement();

			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tagname);
			//ResultSet rs = stmt.executeQuery(sql);
			rs = stmt.executeQuery();
			//System.out.println(rs);
			if (rs == null) {
				System.out.println("无查询结果！");
				
			} else {
				//System.out.println("无查询结果！");
				while (rs.next()) {
					System.out.println("tagname:" + tagname);
					response.setContentType("image/jpeg");
					Blob b = rs.getBlob("binfile");
					long size = b.length();
					byte bs[] = b.getBytes(1, (int) size);

					//压缩图片
					byte[] newdata = null;
					try {
						BufferedImage bis = ImageIO.read(new ByteArrayInputStream(bs));
						int w = bis.getWidth();
						int h = bis.getHeight();
						double sx = (double) nw / w;
						double sy = (double) nh / h;
						AffineTransform transform = new AffineTransform();
						transform.setToScale(sx, sy);
						AffineTransformOp ato = new AffineTransformOp(transform, null);
						//原始颜色
						BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
						ato.filter(bis, bid);

						//转换成byte字节
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(bid, "jpeg", baos);
						newdata = baos.toByteArray();

					} catch (IOException e) {
						e.printStackTrace();
					}

					OutputStream outs = response.getOutputStream();
					outs.write(newdata);
					outs.flush();

				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>
