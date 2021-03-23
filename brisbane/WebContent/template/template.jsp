 <%
 response.setHeader("Cache-Control","no-store");
 response.setHeader("Pragma","no-cache");

 %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    

<html>
<body>
<div id="bodywrap">
	<div align="right">
		<jsp:include page="/module/top.jsp" flush="false"/>
	</div>
	<div id="logo" align="center">
		<a href="${ctxpath}/member/main.do"><img src="../imgs/brisbane.png" width="200"/></a>
	</div>
	<div align="center">
		<jsp:include page="/module/left.jsp" flush="false"/>
	</div>
	<div id="page" align="center">
		<jsp:include page="${CONTENT}" flush="false"/>
	</div>
	<div align="center">
		<jsp:include page="/module/bottom.jsp" flush="false"/>
	</div>

</div>

</body>
</html>