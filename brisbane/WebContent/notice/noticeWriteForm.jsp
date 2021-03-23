<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeWriteForm</title>
<script src="script.js" type="text/javascript"></script>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<table align="center" cellspacing="50">
		<tr>
			<td>
				<font size="6">글쓰기</font>
			</td>
		</tr>
	</table>
	<form name="noticeform" method="post" action="${ctxpath}/notice/noticeWritePro.do" onsubmit="return writeSave()">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="re_step" value="${re_step}">
		<input type="hidden" name="re_level" value="${re_level}">
		
		<table align="center">
			<tr>
				<td colspan="2" align="right">
					<a href="${ctxpath}/notice/noticeList.do">글목록</a>
				</td>
			</tr>
			
			<!-- 작성자 -->
			<tr>
				<td bgcolor="#ecf7fd">작성자</td>
				<td bgcolor="f9fafb"><input type="text" name="writer" id="writer" size="20"></td>
			</tr>
			
			<!-- 글제목 -->
			<tr>
				<td bgcolor="#ecf7fd">글제목</td>
				<td bgcolor="f9fafb">
					<!-- 원글일때 -->
					<c:if test="${num==0}">
						<input type="text" name="subject" id="subject" size="40">
					</c:if>
					<!-- 답글일때 -->
					<c:if test="${num!=0}">
						<input type="text" name="subject" id="subject" size="40" value="[re]">
					</c:if>
				</td>
			</tr>
			
			<!-- 글내용 -->
			<tr>
				<td bgcolor="#ecf7fd">글내용</td>
				<td bgcolor="f9fafb"><textarea name="content" id="content" rows="10" cols="60"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<!-- 원글일때 -->
					<c:if test="${num==0}">
						<input type="submit" value="글쓰기">
					</c:if>
					<!-- 답글일때 -->
					<c:if test="${num!=0}">
						<input type="submit" value="답글쓰기">
					</c:if>
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>