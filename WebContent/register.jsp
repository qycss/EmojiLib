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
  <title>Register</title>
  
  <!-- 页面自适应手机屏幕-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes">
  
  <!-- Pragma(cache模式) 
            说明：是用于设定禁止浏览器从本地机的缓存中调阅页面内容，
            设定后一旦离开网页就无法从Cache中再调出。
            这样设定，访问者将无法脱机浏览。 -->
  <meta http-equiv="pragma" content="no-cache">
  <!-- 清除缓存（再访问这个网站要重新下载！） -->
  <meta http-equiv="cache-control" content="no-cache">
  <!-- 设定页面的到期时间 -->
  <meta http-equiv="expires" content="0">
  <!-- 关键字，给搜索引擎用的 -->
  <meta http-equiv="keywords" content="login">
  <!-- 页面描述  -->
  <meta http-equiv="description" content="The page for login.">
  
  <link href="css/login.css" rel="stylesheet" type="text/css">
  
  <script type="text/javascript">
    var loginState = "${loginState}";
    if(loginState == "wrong username"){
    	alert("该用户不存在！");
    }else if(loginState == "wrong password"){
    	alert("密码错误！");
    }
  </script>
  
 </head>
 
 <body>


<div class="welcome">Welcome</div>



 <div class="form-group">
   <!-- 点击form表单的提交按钮（submit）后，会将表单数据提交至action参数所指向的页面 -->
   <!-- 浏览器使用 method 属性设置的方法将表单中的数据传送给服务器进行处理。共有两种方法：POST 方法和 GET 方法。 -->
   <form action="RegisterServlet" method="post" >
   
   <!-- registerServlet逻辑未完成 -->
   <table>
	<tr>
	 <td><input type="text" name="username" class="text-center1" placeholder="Username" value="" /></td>
	</tr>
	<tr>
	 <td><input type="password" name="password" class="text-center" placeholder="Password" value="" /></td>
	</tr>
   </table>
   <table>
	<tr>
	 <td><input class="login" type="submit" name="submit" value="Register"/></td>
	</tr>
   </table>
   </form>
  </div>
<!-- 动画 -->			
<div id="A"></div>
<div id="B"></div>
<div id="C"></div>
<div id="E"></div>
<div id="F"></div>
</body>
</html>

