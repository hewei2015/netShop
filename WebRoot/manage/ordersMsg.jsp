<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/header.jsp" %>
	订单号：${o.ordernum}&nbsp;&nbsp;</br>金额：￥${o.price }元</br>
	明细如下：</br>
		<table border="1" width="88%" style="text-align:center" align="center">
			<tr>
				<th>书名</th>
				<th>作者</th>
				<th>单价</th>
				<th>小计</th>
				<th>数量</th>
			</tr>
			<c:forEach items="${o.items }" var="i">
				<tr>
					<td>${i.book.name}</td>
					<td>${i.book.author}</td>
					<td>￥${i.book.price}元</td>
					<td>${i.num}</td>
					<td>￥${i.price}元</td>
				</tr>
			</c:forEach>
		</table>
   	收货信息如下：</br>
   		地址：${o.user.address}</br> 		
   		电话:${o.user.cellphone}</br>
   		</hr>
   		<c:if test="${o.state==0 }">
   		<a href="${pageContext.request.contextPath}/manageServlet?operation=sureSend&ordersId=${o.id}">确认发货</a>
 	</c:if>
  </body>
</html>
