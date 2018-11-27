<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/client/header.jsp" %>
	<h1>新用户注册</h1>	
	<form action="${pageContext.request.contextPath}/clientServlet?operation=regist" method="post"> 
		<table border="1" width="88%" style="text-align:center" align="center">
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="username" />
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="password" />
				</td>
			</tr>
			<tr>
				<td>电话</td>
				<td>
					<input type="text" name="cellphone" />
				</td>
			</tr>
			<tr>
				<td>手机</td>
				<td>
					<input type="text" name="mobilephone" />
				</td>
			</tr>
			<tr>
				<td>住址</td>
				<td>
					<input type="text" name="address" />
				</td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td>
					<input type="text" name="email" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="注册" />
				</td>
			</tr>
		</table>	
	</form>
  </body>
</html>
