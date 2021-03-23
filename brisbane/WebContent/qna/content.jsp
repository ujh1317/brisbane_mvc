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

	<table align="center" width="700">
		<tr><td colspan="4" align="right"><br><font size="7"><b>Q&A</b></font></td></tr>
		
		<tr>
			<td width="70" bgcolor="#c8e5f9">글제목</td>		
			<td width="250" bgcolor="#e1f1fc">${dto.subject}</td>		
			<td width="70" bgcolor="#c8e5f9">작성일</td>		
			<td bgcolor="#e1f1fc">
		
			<c:if test="${dto.regdate eq dto.modifydate}">
			${dto.regdate}
			</c:if>
			
			<c:if test="${dto.regdate ne dto.modifydate}">
			[수정일]${dto.modifydate}
			</c:if>
			
			</td>		
		</tr>
		
	</table>

	<table align="center" width="700">
		
		<tr>
			<td width="70" bgcolor="#c8e5f9">작성자</td>		
			<td width="250" bgcolor="#e1f1fc">${dto.writer}</td>		
			<td width="70" bgcolor="#c8e5f9">카테고리</td>		
			<td bgcolor="#e1f1fc">${dto.category}</td>		
			<td width="70" bgcolor="#c8e5f9">조회수</td>		
			<td bgcolor="#e1f1fc">${dto.readcount}</td>		
		</tr>
		
		<tr height="200">
			<td colspan="6" bgcolor="#ecf7fd">${content}</td>
		</tr>
		
	</table>	
	
	<table align="center" width="700">
		
		<tr>
			<td align="left">
				<input type="button" value="리스트" onClick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
				<input type="button" value="글쓰기" onClick="location='${ctxpath}/qna/writeForm.do'">
			</td>
		
			<td align="right">
			
			<c:if test="${sessionScope.memId eq dto.writer}">
				<input type="button" value="글수정" onClick="location='${ctxpath}/qna/updateForm.do?num=${num}&pageNum=${pageNum}'">
				<input type="button" value="글삭제" onClick="location='${ctxpath}/qna/delete.do?num=${num}&pageNum=${pageNum}'">
			</c:if>
			<c:if test="${sessionScope.memId eq dto.writer or sessionScope.memId eq 'admin'}">
				<input type="button" value="답글쓰기" onClick="location='${ctxpath}/qna/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step}&re_level=${re_level}'">
			</c:if>
			</td>
		</tr>
		<br><br>
	</table>	

</body>
</html>













