<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>noticeContent</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script>
	function del(url){
		var del = confirm('<c:out value="${dto.num}"/>번 글을 삭제하시겠습니까?');
		if(del==true){
			location.href=url;
			alert('삭제되었습니다.');
		}else{
			alert('취소되었습니다.');
		}//if
	}
</script>

</head>
<body>
	<table align="center" cellspacing="50">
		<tr>
			<td>
				<font size="6">공지사항</font>
			</td>
		</tr>
	</table>
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
			<td colspan="3" height="200" bgcolor="#e1f1fc">
				${content}
			</td>
		</tr>
		<tr>
			<td colspan="4" bgcolor="#e1f1fc" style="text-align:right;">IP : ${dto.ip}</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<c:if test="${sessionScope.memId eq 'admin'}">
					<input type="button" value="수정" onclick="location='${ctxpath}/notice/noticeUpdateForm.do?num=${num}&pageNum=${pageNum}'">
					<input type="button" value="삭제" onclick="del('${ctxpath}/notice/noticeDelete.do?num=${num}&pageNum=${pageNum}')" >
					<input type="button" value="글쓰기" onclick="location='${ctxpath}/notice/noticeWriteForm.do'">
				</c:if>
				<input type="button" value="목록" onclick="location='${ctxpath}/notice/noticeList.do?pageNum=${pageNum}'">
				<input type="button" value="답글쓰기" onclick="location='${ctxpath}/notice/noticeWriteForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step}&re_level=${re_level}'">
			</td>
		</tr>
	</table>
</body>
</html>