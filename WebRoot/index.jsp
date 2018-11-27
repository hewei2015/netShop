<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>index.jsp</title>   
  </head>
  
  <body>
	<jsp:forward page="/clientServlet">
		<jsp:param value="showIndexCategory" name="operation"/>
	</jsp:forward>
  </body>
</html>
