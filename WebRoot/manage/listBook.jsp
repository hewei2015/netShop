<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/header.jsp" %>
<h3>书籍列表</h3>
	<table border="1" width="88%" align="center" style="text-align:center">
		<tr>
			<th>书名</th>
			<th>作者</th>
			<th>描述</th>
			<th>所属分类</th>
			<th>图片</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.records}" var="b">
			<tr>
				<td>${ b.name}</td>
				<td>${b.author }</td>
				<td>${b.description }</td>
				<td>${myfn:getCategoryNameByIdEL(b.category_id)}</td>
				<td>
					<a href="${pageContext.request.contextPath}/files/${b.image}">查看图片</a>
				</td>
				<td>
					<a href="#" >[修改]</a>&nbsp;&nbsp;
					<a href="#" >[删除]</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 视频内容简单分页内容开始 -->
	<!--  
	第${page.pagenum}页/共${page.totalpage}页&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/manageServlet?operation=showAllBook&pagenum=
		${page.pagenum-1>0?pagenum-1:1}">上一页</a>
	<a href="${pageContext.request.contextPath}/manageServlet?operation=showAllBook&pagenum=
		${page.pagenum+1>page.totalpage?page.totalpage:(page.pagenum+1)}">下一页</a>
		-->
	<%@ include file="/commons/page.jsp" %>
		
	<!-- 视频内容简单分页内容结束 -->
  </body>
</html>
