<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  
  <link href="style.css" rel="stylesheet" type="text/css">
  <style type="text/css">
  h2{text-align:center;}
  </style>
  <script src="script.js" type="text/javascript"></script>
  
</head>
<body>
 <h2> 글수정</h2>
 <form name="writeForm" method="post" action="${ctxpath}/faq/updatePro.do?pageNum=${pageNum}" onSubmit="return writeSave()">
  <table align="center">
   
   <tr>
     <td bgcolor="#ecf7fd">글쓴이</td>
     <td bgcolor="f9fafb">
     <input type="text" name="name" id="name" size="70" value="${dto.name}">
     <input type="hidden" name="num" id="num" value="${num}">
     </td>
   </tr>
   
   <tr>
     <td bgcolor="#ecf7fd">글제목</td>
     <td bgcolor="f9fafb">
       <input type="text" name="subject" id="subject" size="70" value="${dto.subject}">
     </td>
   </tr>
   
   <tr> 
     <td bgcolor="#ecf7fd">글내용</td>
     <td bgcolor="f9fafb">
       <textarea name="content" id="content" rows="10" cols="90">${dto.content}</textarea>
     </td>
   </tr>
   
   <tr>
    <td bgcolor="#ecf7fd">암호</td>
    <td bgcolor="f9fafb"><input type="password" name="pass" id="pass" size="20"></td>
   </tr>
   
   <tr>
    <td colspan="2" align="center">
     <input type="submit" value="글수정">
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" onClick="location='${ctxpath}/faq/list.do?pageNum=${pageNum}'">
    </td>
   </tr>
   
  </table> 
 </form>
</body>
</html>