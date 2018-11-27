<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/client/header.jsp" %>
	<h1>您近期的订单如下：</h1>
		<table border="1" width="88%" style="text-align:center" align="center">
			<tr>
				<th>订单号</th>
				<th>金额</th>
				<th>订单状态</th>
				<th>明细</th>
			
			</tr>
			<c:forEach items="${ os}" var="o">
				<tr>
					<td>${o.ordernum}</td>
					<td>￥${o.price }元</td>
					<td>${o.state==0?'未发货':'已发货'}</td>
					<td>
						<a href="${pageContext.request.contextPath}/clientServlet?operation=showOrdersDetail&ordersId=${o.id}">订单详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
  </body>
</html>
