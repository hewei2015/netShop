<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/header.jsp" %>
	<h2>分类列表</h2>
	<c:if test="${empty cs}">
		对不起，没有分类，请先添加
	</c:if>
	<c:if test="${!empty cs}">
	<table border="1" width="88%" style="text-align:center" align="center">
		<tr>
			<th>选择</th>
			<th>分类名称</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${cs}" var="c">
			<tr>
				<td>
					<input type="checkbox" name="cids" value="${c.id }"/>
				</td>
				<td>${c.name }</td>
				<td>${c.description }</td>
				<td>
					<a href="#" >更改</a>
					<a href="#" >删除</a>
				</td>
			<tr>
		</c:forEach>
	</table>
	</c:if>
  </body>
</html>
