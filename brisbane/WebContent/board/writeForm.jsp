<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="script.js" type="text/javascript"></script>
</head>
<body>
<center><h2>글쓰기, 답글쓰기</h2></center>
<form name="writeform" method="post" action="${ctxpath}/board/writePro.do" onSubmit="return writeSave()">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="re_step" value="${re_step}">
	<input type="hidden" name="re_level" value="${re_level}">
	<table align="center">
		<tr>
			<td colspan="2" align="right">
			<a href="${ctxpath}/board/list.do">글목록</a>
			</td>
		</tr>
		
		<tr>
			<td bgcolor="#ecf7fd">작성자</td>
			<td bgcolor="f9fafb">
				${writer}<input type="hidden" name="writer" id="writer" value="${writer}">
			</td>
		</tr>
		
		<tr>
			<td bgcolor="#ecf7fd">글제목</td>
			<td bgcolor="f9fafb">
			<%--원글 --%>
			<c:if test="${num==0}">
				<input type="text" name="subject" id="subject" size="70">
			</c:if>

			<%--답글 --%>
			<c:if test="${num!=0}">
				<input type="text" name="subject" id="subject" size="70" value="[답글]">
			</c:if>
			</td>
		</tr>
		<tr>
			<td bgcolor="#ecf7fd">글내용</td>
			<td bgcolor="f9fafb"><textarea name="content" id="content" rows="10" cols="90"></textarea></td>
		</tr>
		<tr>
			<td bgcolor="#ecf7fd">암호</td>
			<td bgcolor="f9fafb"><input type="password" name="pw" id="pw" size="20"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<%--원글 --%>
			<c:if test="${num==0}">
				<input type="submit" value="글쓰기">
			</c:if>

			<%--답글 --%>
			<c:if test="${num!=0}">
				<input type="submit" value="답글쓰기">
			</c:if>
			
			<input type="reset" value="다시쓰기">
			<input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>