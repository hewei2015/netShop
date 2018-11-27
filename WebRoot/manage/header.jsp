<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
      contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://hw.com/jsp/jstl/myfunctions" prefix="myfn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
  <head>  
    <title>首页</title>   
  </head>
  
  <body style="text-align:center">
  
  <br />
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
        <LI><A class=nav_on id=mynav0 onmouseover=javascript:qiehuan(0) href="#"><SPAN>分类管理</SPAN></A></LI>
        <LI class="menu_line"></LI>
        <li><a href="#" onmouseover="javascript:qiehuan(1)" id="mynav1" class="nav_off"><span>图书管理</span></a></li>
        <li class="menu_line"></li>
        <li><a href="#" onmouseover="javascript:qiehuan( 2)" id="mynav2" class="nav_off"><span>订单管理</span></a></li>
        <li class="menu_line"></li>
        
        
      </UL>
      <div id=menu_con>
        <div id=qh_con0 style="DISPLAY: block">
          <UL>
            <LI><a href="${pageContext.request.contextPath}/manage/addCategory.jsp"><span>添加分类</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="${pageContext.request.contextPath}/manageServlet?operation=listCategory"><SPAN>查询分类</SPAN></A></LI>
            <LI class=menu_line2></LI>
           
          </UL>
        </div>
        <div id=qh_con1 style="DISPLAY: none">
          <UL>
            <LI><a href="${pageContext.request.contextPath}/manageServlet?operation=showCategoryUI"><span>添加图书</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="${pageContext.request.contextPath}/manageServlet?operation=showAllBook"><SPAN>查询图书</SPAN></A></LI>
            <LI class=menu_line2></LI>
           
          </UL>
        </div>
        <div id=qh_con2 style="DISPLAY: none">
          <UL>
            <LI><a href="${pageContext.request.contextPath}/manageServlet?operation=showAllOrders0"><span>待处理订单</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="${pageContext.request.contextPath}/manageServlet?operation=showAllOrders1"><SPAN>已处理订单</SPAN></A></LI>
            <LI class=menu_line2></LI>
          </UL>
        </div>  
      </div>
    </div>
  </div>
</div>