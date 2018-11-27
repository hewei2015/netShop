<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
第${page.pagenum}页/共${page.totalpage}页&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/${page.url}&pagenum=${page.pagenum-1>0?page.pagenum-1:1}">上一页</a>
<a href="${pageContext.request.contextPath}/${page.url}&pagenum=
		${page.pagenum+1>page.totalpage?page.totalpage:(page.pagenum+1)}">下一页</a>
		
<a href="${pageContext.request.contextPath }/${page.url}&pagenum=${page.totalpage}">尾页</a>
<select id="s1">
	<c:forEach begin="1" end="${page.totalpage}" var="num">
		<option value="${num}" ${page.pagenum==num?'selected="selected"':''}>${num}</option>
	</c:forEach>
</select>
<a href="javascript:jump()">跳转</a>
<script type="text/javascript">
	function jump() {
		var num = document.getElementById("s1").value;
		window.location.href = "${pageContext.request.contextPath }/${page.url}&pagenum="+ num;
	}
</script>