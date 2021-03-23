<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:if test="${check==1}">
<c:remove var="memId" scope="session"/>
<table width="270" cellpadding="5">
<tr height="40">
<td align="center"><font size="+1"><b>회원정보가 삭제되었습니다.</b></font></td>
</tr>
<tr height="40">
<td align="center">
<p>안녕히가세요.</p>
<meta http-equiv="Refresh" content="5;url=${ctxpath}/member/main.do">
</td>
</tr>

</table>
</c:if>
<c:if test="${check==-1}">
<script>
alert("암호 틀림");
history.back();
</script>
</c:if>



</body>
</html>