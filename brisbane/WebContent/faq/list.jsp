<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="/module/header.jsp"%>
 
<fmt:requestEncoding value="UTF-8"/>
<%--list.jsp --%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
<font size="6" align="center"><b>FAQ</b></font>
 <center><b>글목록(전체 글갯수:${count})</b></center>
 
 <table width="700" align="center">
   <tr>
    <td align="right">
      <a href="${ctxpath}/faq/writeForm.do">글쓰기</a>
    </td>
   </tr>
 </table>
 
 <c:if test="${count==0}">
  <table border="1" align="center" width="700">
    <tr>
      <td align="center">
        게시판에 저장된 글이 없습니다
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
   
   <c:forEach var="dto" items="${faqList}">
    <tr bgcolor="#f9fafb">
      <%--글번호 --%>
      <td align="center">
        <c:out value="${number}"/>
        <c:set var="number" value="${number-1}"/>
      </td>
      
      <%--글제목 --%>
      <td> 
        <!-- 답글 -->
        <c:if test="${dto.re_level>0}">
         <img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
         <img src="../imgs/re.gif">
        </c:if>
        
        <!-- 원글 -->
        <c:if test="${dto.re_level==0}">
         <img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
        </c:if>
        
        <!--content.do-->
        <a href="${ctxpath}/faq/content.do?num=${dto.num}&pageNum=${pageNum}">
         ${dto.subject}
        </a>
        
        <!-- 20이상 조회 하면  hot.gif 표시  -->
        <c:if test="${dto.count>20}">
          <img src="../imgs/hot.gif" border="0" height="10"/>
        </c:if>
      </td>
      
      <!-- 글쓴이 -->
      <td> 
       <a href="mailto:hong@naver.com">${dto.name}</a>
      </td>
      
      <!-- 날짜 -->
      <td>
       ${dto.regdate}
      </td>
      
      <!-- 조횟수 -->
      <td>
        ${dto.count}
      </td>
      
      <!-- ip -->
      <td>
        ${dto.ip}
      </td>
    </tr>
   </c:forEach>
  </table>
 </c:if>
 <!-- 실행 -->
 <!-- 이전블럭  페이지처리 다음블럭 -->
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
  
  <!-- 에러방지 -->
  <c:if test="${endPage>pageCount}">
    <c:set var="endPage" value="${pageCount}"/>
  </c:if>
  
  <!-- 이전블럭 -->
  <c:if test="startPage>10">
    <a href="${ctxpath}/faq/list.do?pageNum=${startPage-10}">
    [이전블럭]
    </a>
  </c:if>
  
  <!-- 페이지 처리 -->
  <c:forEach var="i" begin="${startPage}" end="${endPage}">
    <a href="${ctxpath}/faq/list.do?pageNum=${i}">
     [${i}]
    </a>
  </c:forEach>
  
  <!-- 다음 블럭 -->
  <c:if test="${endPage<pageCount}">
   <a href="${ctxpath}/faq/list.do?pageNum=${startPage+10}">
   [다음블럭]
   </a>
  </c:if>
  
  </td></tr>
  </table>
  
 </c:if> 
 
</body>
</html>