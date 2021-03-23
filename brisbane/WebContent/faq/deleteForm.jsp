<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
 <link href="style.css" rel="stylesheet" type="text/css">
 
 <style type="text/css">
  h2{text-align:center;}
 </style>
 
 <script type="text/javascript">
  function check(){
	  if(document.delForm.pass.value==''){
		  alert("암호를 입력 하세요 ");
		  document.delForm.pass.focus();
		  return false;
	  }
	  return true;
  }//check() end
 
 </script>
</head>

<body>
 <h2>글삭제 폼</h2>
 <form name="delForm" method="post" action="${ctxpath}/faq/deletePro.do?pageNum=${pageNum}" onSubmit="return check()">
 
  <table border="1" align="center" width="300">
  
    <tr height="30">
      <td align="center" bgcolor="#b0e0e6">
              암호를 입력 하세요
      </td>
    </tr>
    
    <tr height="30">
      <td align="center" bgcolor="#b0e0e6">
            암호 입력:<input type="password" name="pass" size="10">
            <input type="hidden" name="num" value="${num}">
      </td>
    </tr>
    
    <tr height="30">
      <td align="center" bgcolor="#b0e0e6">
        <input type="submit" value="글삭제">
        <input type="button" value="글목록" onClick="location.href='${ctxpath}/faq/list.do?pageNum=${pageNum}'">
      </td>
    </tr>
  </table>
 </form>
</body>
</html>