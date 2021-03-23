<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>

<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<center><b>글목록(전체 글 갯수:${count})</b></center>

<table width="700" align="center">
	<tr>
		<td align="right">
			<a href="${ctxpath}/board/writeForm.do">글쓰기</a>
		</td>
	</tr>
</table>	
	<c:if test="${count==0}">
		<table border="1">
			<tr>
				<td align="center" border="1" width="700">
				게시판에 저장된 글이 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count>0}">
		<table align="center" width="700">
			<tr bgcolor="#ecf7fd">
				<td width="50">글번호</td>
				<td width="250">글제목</td>
				<td width="100">작성자</td>
				<td width="150">작성일</td>
				<td width="50">조횟수</td>
				<td width="100">IP</td>
			</tr>
			
			<c:forEach var="dto" items="${boardList}">
				<tr bgcolor="f9fafb">
					<td align="center">
						<c:out value="${number}"/>
						<c:set var="number" value="${number-1}"/>
					</td>

					<td>
						<c:if test="${dto.re_level>0}">
						<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
						<img src="../imgs/re.gif"/>
						</c:if>
						
						<c:if test="${dto.re_level==0}">
							<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
						</c:if>
						
						<a href="${ctxpath}/board/content.do?num=${dto.num}&pageNum=${pageNum}">
						${dto.subject}
						</a>
						
						<c:if test="${dto.readcount>20}">
							<img src="../imgs/hot.gif" border="0" height="10"/>
						</c:if>
						
						
					</td>
					
					<td>
						<a href="mailto:hong@naver.com">
						${dto.writer}
						</a>
					</td>
					
					<td>
						${dto.regdate}
					</td>
					<td>
						${dto.readcount}
					</td>
					<td>
						${dto.ip}
					</td>
					
				</tr>
			</c:forEach>	
		</table>
	</c:if>
	
	<%--
	int pageCount=count/pageSize+(count%pageSize==0?0:1);
	
	 --%>
	<c:if test="${count>0}">
	<table align="center">
	<tr><td>
	<%--
		<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1}"/>
		<c:set var="endPage" value="${startPage+pageBlock-1}"/>
	 --%>	
		<c:if test="${endPage>pageCount}">
			<c:set var="endPage" value="${pageCount}"/>
		</c:if>
		<c:if test="${startPage>10}">
			<a href="${ctxpath}/board/list.do?pageNum=${startPage-10}">
			[이전 블럭]
			</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="${ctxpath}/board/list.do?pageNum=${i}">
			[${i}]
			</a>
		</c:forEach>
		
		<c:if test="${endPage<pageCount }">
			<a href="${ctxpath}/board/list.do?pageNum=${startPage+10}">
			[다음 블럭]
			</a>
		</c:if>
		</td></tr>
		</table>
	</c:if>

</body>
</html>
















