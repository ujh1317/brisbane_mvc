<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<link href="style.css" rel="stylesheet" type="text/css">
<c:if test="${check==1}">
<meta http-equiv="refresh" content="0;url=${ctxpath}/board/list.do?pageNum=${pageNum}">
</c:if>

<c:if test="${check==0}">
암호가 틀립니다.<br>
<a href="javascript:history.back()">[수정 폼으로 돌아가기]</a>
</c:if>