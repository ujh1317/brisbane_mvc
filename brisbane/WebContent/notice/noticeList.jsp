<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeList</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<table align="center" cellspacing="30">
		<tr>
			<td>
				<font size="6"><b>공지사항</b></font>
			</td>
		</tr>
	</table>
	<c:if test="${sessionScope.memId eq 'admin'}">
		<table width="700" align="center">
			<tr>
				<td align="right">
						<a href="${ctxpath}/notice/noticeWriteForm.do">글쓰기</a>
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count==0}">
		<table border="1"  align="center" width="700">
			<tr>
				<td>
					게시판에 저장된 글이 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count>0}">
		<table align="center" width="700">
			<tr bgcolor="#ecf7fd">
				<td width="50" align="center">글번호</td>
				<td width="250" align="center">글제목</td>
				<td width="100" align="center">작성자</td>
				<td width="150" align="center">등록일</td>
				<td width="50" align="center">조회수</td>
			</tr>
			<c:forEach var="dto" items="${noticeList}">
				<tr bgcolor="#f9fafb">
					<td align="center">
						<c:out value="${number}"/>
						<c:set var="number" value="${number-1}"/>
					</td>
					
					<%-- 글제목 --%>
					<td>
						<%-- 답글 --%>
						<c:if test="${dto.re_level>0}">
							<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16">
							<img src="../imgs/re.gif">
						</c:if>
					
						<%--원글 --%>
						<c:if test="${dto.re_level==0}">
							<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16">
						</c:if>
						
						<%-- content.do --%>
						<c:if test="${!empty sessionScope.memId}">
						<a href="${ctxpath}/notice/noticeContent.do?num=${dto.num}&pageNum=${pageNum}">${dto.subject}</a>
						</c:if>
						<c:if test="${empty sessionScope.memId}">
							${dto.subject}
						</c:if>
						
						<%-- 조회수 best --%>
						<c:if test="${dto.readcount>10}">
							<img src="../imgs/hot.gif" border="0" height="16">
						</c:if>
					</td>
					
					<%-- 작성자 --%>
					<td align="center">
						<c:if test="${!empty sessionScope.memId}">
							<a href="mailto:iu@naver.com">${dto.writer}</a>
						</c:if>
						<c:if test="${empty sessionScope.memId}">
							${dto.writer}
						</c:if>
					</td>
					
					<%-- 등록일 --%>
					<td align="center">${dto.regdate}</td>
					
					<%-- 조회수 --%>
					<td align="right">${dto.readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<%-- 페이지처리 --%>
	<c:if test="${count>0}">
	 	<table align="center">
	 		<tr>
	 			<td>
				 	<%-- 에러방지 --%>
				 	<c:if test="${endPage>pageCount}">
				 		<c:set var="endPage" value="${pageCount}"/>
				 	</c:if>
				 	
				 	<%-- 이전블럭 --%>
				 	<c:if test="startPage>10">
				 		<a href="${ctxpath}/notice/noticeList.do?pageNum=${startPage-10}">[이전]</a>
				 	</c:if>
				 	
				 	<%-- 페이지처리 --%>
				 	<c:forEach var="i" begin="${startPage}" end="${endPage}">
				 		<a href="${ctxpath}/notice/noticeList.do?pageNum=${i}">[${i}]</a>
				 	</c:forEach>
				 	
				 	<%-- 다음블럭 --%>
				 	<c:if test="${endPage<countPage}">
				 		<a href="${ctxpath }/notice/noticeList.do?pageNum=${startPage+10}">[다음]</a>
				 	</c:if>
	 	
	 			</td>
	 		</tr>
	 	</table>
 	</c:if>
</body>
</html>