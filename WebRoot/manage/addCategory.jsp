<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/header.jsp" %>
	<form action="${pageContext.request.contextPath}/manageServlet?operation=addCategory" method="post">  
		<table align="center"  border="1" width="88%" >
			<tr>
				<td>*分类名称</td>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>概述</td>
				<td>
					<textarea rows="3" cols="34" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="添加"/>
				</td>
			</tr>
		
		</table>
	</form>
  </body>
</html>
