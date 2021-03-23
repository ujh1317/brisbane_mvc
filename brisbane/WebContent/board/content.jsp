<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<style>
h1{text-align:center;}
tr{line-height:30px;}
</style>
</head>
<body>
<h1>글 내용 보기</h1>
<table width="700" align="center">
	<tr>
		<td bgcolor="#c8e5f9">글번호</td>
		<td bgcolor="#e1f1fc">${dto.num}</td>
		<td bgcolor="#c8e5f9">조회수</td>
		<td bgcolor="#e1f1fc">${dto.readcount}</td>
	</tr>
	<tr>
		<td bgcolor="#c8e5f9">작성자</td>
		<td bgcolor="#e1f1fc">${dto.writer}</td>
		<td bgcolor="#c8e5f9">작성일</td>
		<td bgcolor="#e1f1fc">${dto.regdate}</td>
	</tr>
	<tr>
		<td bgcolor="#c8e5f9">글제목</td>
		<td colspan="3" bgcolor="#e1f1fc">${dto.subject}</td>
	</tr>
	<tr>
		<td bgcolor="#c8e5f9">글내용</td>
		<td colspan="3" height="200" bgcolor="#e1f1fc">${content}</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" value="글수정" onClick="location='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">
			<input type="button" value="글삭제" onClick="location='${ctxpath}/board/deleteForm.do?num=${num}&pageNum=${pageNum}'">
			<input type="button" value="글쓰기" onClick="location='${ctxpath}/board/writeForm.do'">

			<input type="button" value="답글쓰기" onClick="location='${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step}&re_level=${re_level}'">
			<input type="button" value="리스트" onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">
		</td>
	</tr>
	
	
</table>
</body>
</html>