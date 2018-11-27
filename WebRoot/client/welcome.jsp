<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/client/header.jsp" %>
<h1>欢迎光临</h1>
	<!-- 显示分页数据，不分类的 -->
	<table align="center">
		<tr >
			<c:forEach items="${page.records}" var="b">
				<td>
					<table>
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/files/${b.image}"/>
							</td>
						</tr>
						<tr>
							<td>
								书名：${b.name }<br />
								作者：${b.author }<br />
								原价：<strike>888</strike><br/>
								震撼价：${b.price}
							</td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/clientServlet?operation=buyBook&bookId=${b.id}">购买</a>
							</td>
						</tr>
					</table>
				</td>
			</c:forEach>
		</tr>
	</table>
			
		<tr align="center">
			<td>
				<%@ include file="/commons/page.jsp" %>
			</td>
		</tr>
	
  </body>
</html>
