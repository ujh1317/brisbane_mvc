<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js" type="text/javascript"></script>
</head>
<body>
	<c:if test="${empty sessionScope.memId}">
	<!-- 로그인 안된 상태 -->
		<form name="loginForm" method="post" action="${ctxpath}/member/loginPro.do" onSubmit="return loginCheck()">
			<table width="700" align="center">
			
			<tr>
				<td rowspan="3" width="500" align="center" bgcolor="#ecf7fd"><font size="5"><b>로그인</b></font><br></td>
				<td width="500" align="right" bgcolor="#f9fafb">아이디&nbsp;</td>
				<td width="100"><input type="text" name="id" size="15"></td>
			</tr>
			<tr>
				
				<td width="100" align="right" bgcolor="#f9fafb">패스워드&nbsp;</td>
				<td><input type="password" name="pw" size="15"></td>
			</tr>
			<tr>
				
				<td colspan="2" align="center" bgcolor="#f9fafb">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입" onClick="location.href='${ctxpath}/member/inputForm.do'">
				</td>
			</tr>
			
			</table>	
		</form>
	</c:if>

	<!-- 로그인 상태 -->
	<c:if test="${!empty sessionScope.memId}">
		<table width="700" align="center">
			<tr>
			<td align="center">
				<a href="${ctxpath}/member/main.do?menu=<%=1%>">
					<img src="${ctxpath}/imgs/menu1.jpg" width="40"/><br>
					워킹홀리데이란?
				</a>
				
			</td>
			<td align="center">
				<a href="${ctxpath}/member/main.do?menu=<%=2%>">
					<img src="${ctxpath}/imgs/menu2.jpg" width="40"/><br>
					&nbsp;&nbsp;&nbsp;안전 정보&nbsp;&nbsp;&nbsp;
				</a>
			</td>
			<td align="center">
				<a href="${ctxpath}/member/main.do?menu=<%=3%>">
					<img src="${ctxpath}/imgs/menu3.jpg" width="40"/><br>
					&nbsp;&nbsp;&nbsp;지역 정보&nbsp;&nbsp;&nbsp;
				</a>
			</td>
			<td align="center">
				<a href="${ctxpath}/member/main.do?menu=<%=4%>">
					<img src="${ctxpath}/imgs/menu4.jpg" width="40"/><br>
					&nbsp;&nbsp;&nbsp;취업정보&nbsp;&nbsp;&nbsp;
				</a>
			</td>
			</tr>
			<tr>
			<td colspan="4" bgcolor="#f9fafb">
			<pre class="main">
				${data}
			</pre>
			</td>
			</tr>
			
			
		</table>
	</c:if>
	
</body>
</html>















