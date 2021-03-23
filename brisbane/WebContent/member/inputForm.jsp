<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

	function openDaumPostcode(){
	   new daum.Postcode({
	      oncomplete:function(data){
	         document.getElementById('zipcode').value=data.zonecode;
	         document.getElementById('addr').value=data.address;
	       }
	   }).open();
	}//openDaumPostcode()---

</script>	
<script type="text/javascript">	
	function check(){
		if($('#id').val()==""){
			alert("id를 입력하세요.");
			$('#id').focus();
			return false;
		}
		if($('#pw').val()==""){
			alert("암호를 입력하세요.");
			$('#pw').focus();
			return false;
		}
		if($('#pw2').val()==""){
			alert("암호 확인을 입력하세요.");
			$('#pw2').focus();
			return false;
		}
		if($('#pw').val()!=$('#pw2').val()){
			alert("암호와 암호 확인이 다릅니다.");
			$('#pw').val('');
			$('#pw2').val('');
			$('#pw').focus();
			return false;
		}

		if($('#name').val()==""){
			alert("이름을 입력하세요.");
			$('#name').focus();
			return false;
		}
		
		return true;
		
	}//check()
	//id 중복체크 혹시 하지 않았으면 반드시 하게 하려고
	function onfocus_id(){
		if($('#idcheck').val()=='-1'){
			alert('id중복체크를 하세요.');
			$('#id').focus();
			return false;
		}
		return true;
	}//onfocus_id
	
	function confirmIdCheck(){

			if($('#id').val()==''){
				alert("id를 입력하세요.");
			}else{
				$.ajax({
					type:'POST',
					url:'confirmId.jsp',
					data:"id="+$('#id').val(),
					dataType:'JSON',
					success:function(data){
						if(data.check==1){
							alert("사용중인 id");
							$('#id').val('').focus();
						}else{
							alert("사용가능한 id");
							$('#idcheck').val('1'); 
							$('#pw').focus();
						}//else
						
					}//function
				});//ajax
			}//else

	}//confirmIdCheck()

	function AjaxTest(){
		$.ajax({
			type:'POST',
			url:'confirmId.jsp',//서버주소
			data:"id="+$('#id').val(),//서버로 넘겨줄 파라미터값
			dataType:'JSON',//서버가 보내준 자료타입
			success:function(data){
				if(data.check==1){
					//사용중인 id
				}else if(data.check==-1){
					//사용가능한 id
				}
			}//success
		});
		
	}
	
</script>

<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form name="inputForm" method="post" action="${ctxpath}/member/inputPro.do" onSubmit="return check()">
		<table width="650" cellpadding="3">
			<tr>
			<td colspan="2" height="30" align="center" bgcolor="#ecf7fd">
				<font size="5"><b>회원가입</b></font>
			</td>
			</tr>
			
			<tr>
				<td bgcolor="#ecf7fd" align="right">사용자 ID</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="id" id="id" size="15" onKeyUp="document.inputForm.idcheck.value='-1';">
					<input type="hidden" name="idcheck" id="idcheck" value="-1">
					
					<input type="button" value="ID 중복체크" onClick="confirmIdCheck()">
				</td>				
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">비밀번호</td>
				<td bgcolor="#f9fafb">
				<input type="password" name="pw" id="pw" size="15" onFocus="return onfocus_id()">
				</td>
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">비밀번호 확인</td>
				<td bgcolor="#f9fafb">
				<input type="password" name="pw2" id="pw2" size="15">
				</td>
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">이름</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="name" id="name" size="30">
				</td>				
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">주민번호</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="jumin1" id="jumin1" size="6" onKeyUp="if(this.value.length==6) inputForm.jumin2.focus();">
					- <input type="password" name="jumin2" id="jumin2" size="7" onKeyUp="if(this.value.length==7) inputForm.email.focus();">
				</td>
								
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">이메일</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="email" id="email" size="30">
				</td>				
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">우편번호</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="zipcode" id="zipcode" size="7" readonly>
					<input type="button" value="주소검색" onClick="openDaumPostcode()">
				</td>				
			</tr>
			<tr>
				<td bgcolor="#ecf7fd" align="right">주소</td>
				<td bgcolor="#f9fafb">
					<input type="text" name="addr" id="addr" size="50" readonly>
					<br>
					상세 주소:<input type="text" name="addr2" id="addr2" size="30">
				</td>				
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원가입" onFocus="return onfocus_id()">
					<input type="reset" value="다시입력">	
					<input type="button" value="가입안함" onClick="location='${ctxpath}/member/main.do'">	
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>












