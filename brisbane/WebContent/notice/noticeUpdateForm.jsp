<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeUpdateForm</title>
<script src="script.js" type="text/javascript"></script>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<table align="center" cellspacing="50">
		<tr>
			<td>
				<font size="6">글수정</font>
			</td>
		</tr>
	</table>
	<form name="noticeform" method="post" action="${ctxpath}/notice/noticeUpdatePro.do?pageNum=${pageNum}" onsubmit="return writeSave()">
		<table align="center">
			<tr>
				<td bgcolor="#ecf7fd">작성자</td>
				<td bgcolor="f9fafb">
					${dto.writer}<input type="hidden" name="writer" value="${dto.writer}">
					<input type="hidden" name="num" value="${num}">
				</td>
			</tr>
			<tr>
				<td bgcolor="#ecf7fd">글제목</td>
				<td bgcolor="f9fafb">
					<input type="text" name="subject" size="70" value="${dto.subject}">
				</td>
			</tr>
			<tr>
				<td bgcolor="#ecf7fd">글내용</td>
				<td bgcolor="f9fafb">
					<textarea name="content" rows="10" cols="90">${dto.content}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align:center;" bgcolor="#ecf7fd">IP</td>
				<td style="text-align:right;" bgcolor="f9fafb">${dto.ip}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
					<input type="button" value="목록" onclick="location='${ctxpath}/notice/noticeList.do?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>