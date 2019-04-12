<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
/* 
request.getContextPath()  返回站点的根目录
request.getScheme() 得到的是协议名称，默认是http
request.getServerName() 得到的是在服务器的配置文件中配置的服务器名称，比如:localhost  .baidu.com 等等
request.getServerPort() 得到的是服务器的配置文件中配置的端口号 比如 8080等等
*/
/* 
path: /WebProject
basePath: http://localhost:8080/WebProject/ 
*/
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 

<!DOCTYPE html>
<html>
 <head>
  <!-- <base>标签为页面上的所有链接规定默认地址或默认目标。 -->
  <base href="<%=basePath%>">
  <title>Index</title>
 
  
  <link href="css/index.css" rel="stylesheet" type="text/css">
 </head>
 
 <body>
  <div>
   <input type="button" name="login" id="btnlogin" value="Login" onclick="window.location.href='login.jsp'" />   

   
   <input type="button" name="register" id="btnregister" value="Register" onclick="window.location.href='register.jsp'"/>
   <!-- 显示用户名 -->
   <input type="button" name="register" id="hellouser" style="display:none" />
   
   <input type="button" name="upload" id="btnupload" value="Upload"  onclick="window.location.href='Upload.jsp'"/>
</div>
   
   <div class="form-group">
   <!-- 点击form表单的提交按钮（submit）后，会将表单数据提交至action参数所指向的页面 -->
   <!-- 浏览器使用 method 属性设置的方法将表单中的数据传送给服务器进行处理。共有两种方法：POST 方法和 GET 方法。 -->

   <form action="SearchServlet" method="post" >
   <table>
	<tr>
	 <td><input type="text" name="tagsearchinput" class="text-center1" placeholder=""  /></td>
	</tr>
   </table>
   <table>
	<tr>
	 <td><input class="search" type="submit" name="submit" value="Search"/></td>
	</tr>
   </table>
   <input  type="checkbox" name=tag value="cat"/>
   </form>
  </div>

<div id="A"></div>
<div id="B"></div>
<div id="C"></div>
<div id="D"></div>
<div id="E"></div>
<div id="F"></div>
<script type="text/javascript">
//从session中判断用户是否已经登录
//未登录时显示Register login两个button
//登录后显示upload及username
 var myName="${username}";
 alert(myName);
 if(myName=="")
	 {	
	 	document.getElementById("btnlogin").style.display="block";
	 	document.getElementById("btnregister").style.display="block";
	 	document.getElementById("btnupload").style.display="none";
	 	document.getElementById("hellouser").style.display="none";
	 	
	 }
 else
	 {
	 	
	 	document.getElementById("btnlogin").style.display="none";
	 	document.getElementById("btnregister").style.display="none";
	 	document.getElementById("btnupload").style.display="block";
	 	document.getElementById("hellouser").style.display="block";
	 	document.getElementById("hellouser").value=myName;
	 }


</script>
</body>

</html>

