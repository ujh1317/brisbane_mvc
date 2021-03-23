<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--deletePro.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
 
 <c:if test="${check==1}">
  <meta http-equiv="Refresh" content="0;url=${ctxpath}/faq/list.do?pageNum=${pageNum}">
 </c:if>
 
 
 <c:if test="${check==0}">
   암호가 틀립니다 <br>
   <a href="javascript:history.go(-1)">
    [글삭제 폼으로 되돌아가기]
   </a>
 </c:if>
 
 
</body>
</html>