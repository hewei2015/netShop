<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />  
    <title>首页</title>   
  </head>
  
<body style="text-align:center">
<br />
	<a href="${pageContext.request.contextPath}">首页</a>
	<span style="width:800px"></span>
	<c:if test="${empty user}"><!-- 也可以是：${sessionScope.user==null} -->
		<a href="${pageContext.request.contextPath}/client/login.jsp">登录</a>
		<a href="${pageContext.request.contextPath}/client/regist.jsp">注册</a>
	</c:if>
	<c:if test="${!empty user}">
		欢迎您：${user.username}&nbsp;&nbsp;<!-- ${sessionScope.user.username} -->
		<a href="${pageContext.request.contextPath}/clientServlet?operation=logout">注销</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/clientServlet?operation=showUsersOrders">我的订单</a>&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/client/showCart.jsp">我的购物车</a>
<br />
<script language="javascript">
	function qiehuan(num){
		for(var id = 0;id<=9;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
	}
</script>
<div id=menu_out>
  <div id=menu_in>
    <div id=menu>
      <UL id=nav>
        <LI><A class=nav_on id=mynav0 onmouseover=javascript:qiehuan(0) href="#"><SPAN>所有分类</SPAN></A></LI>
        <LI class="menu_line"></LI>
    </UL>
      <div id=menu_con>
        <div id=qh_con0 style="DISPLAY: block">
          <UL>
          <c:forEach items="${sessionScope.cs}" var="c">
	            <LI><A href="${pageContext.request.contextPath}/clientServlet?operation=showCategoryBook&categoryId=${c.id}"><SPAN>${c.name }</SPAN></A></LI>
	            <LI class=menu_line2></LI>
            </c:forEach>
            </UL>
         </div>
      </div>
    </div>
  </div>
  </div>

