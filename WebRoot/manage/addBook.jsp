<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/header.jsp" %>
	<h2 align="center">添加图书</h2>
	<form action="${pageContext.request.contextPath}/manageServlet?operation=addBook" method="post" enctype="multipart/form-data" >
		<table border="0" width="88%" align="center">
			<tr>
				<td>书名</td>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>
					<input type="text" name="author"/>
				</td>
			</tr>
			<tr>
				<td>售价</td>
				<td>
					<input type="text" name="price"/>
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="img"/> <!-- ★和javabean的名字不一样 -->
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<textarea rows="3" cols="38" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td>所属分类</td>
				<td>
					<select name="category_id">
						<c:forEach items="${cs}" var="c">
							<option value="${c.id}">${c.name}</option> 
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="添加图书"></td>
			</tr>
		</table>
	</form>
	${message}
  </body>
</html>
