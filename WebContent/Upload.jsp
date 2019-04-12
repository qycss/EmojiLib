<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>表情包上传</title>
<link href="css/upload.css" rel="stylesheet">

</head>

<body background="http://img1.3lian.com/2015/a2/245/d/263.jpg" style="background-size: cover";>
	<form name="form1" method="post" action="PicManage.jsp">
		
		<input class="file" type="file" name="image">
		<p>
			<input class="submit" type="submit" name="Submit" value="提交">
		</p>
		
<div class="tags">		
<p class="tag" style="background-color:#ef8996;"><input type="radio" name="label" value="天线宝宝">天线宝宝</p>
<p class="tag" style="background-color:#edc68b;"><input type="radio" name="label" value="熊猫头">熊猫头</p>
<p class="tag" style="background-color:#75addf;"><input type="radio" name="label" value="猫和老鼠">猫和老鼠</p>
<p class="tag" style="background-color:#d8bce7;"><input type="radio" name="label" value="猫">猫</p>
<p class="tag" style="background-color:#c2f0b3;"><input type="radio" name="label" value="狗">狗</p>
<p class="tag" style="background-color:#f2eab0;"><input type="radio" name="label" value="韩国宝宝">韩国宝宝</p>
<p class="tag" style="background-color:#f7ceed;"><input type="radio" name="label" value="金馆长">金馆长</p>
<p class="tag" style="background-color:#b6dbe9;"><input type="radio" name="label" value="其他">其他</p>
</div>

	</form>
</body>
</html>
