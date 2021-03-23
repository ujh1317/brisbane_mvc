function writeSave(){
	//alert("aaa")
	if(document.writeForm.name.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeForm.name.focus();
	  return false;
	}
	if(document.writeForm.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeForm.subject.focus();
	  return false;
	}
	
	if(document.writeForm.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeForm.content.focus();
	  return false;
	}
        
	if(document.writeForm.pass.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeForm.pass.focus();
	  return false;
	}
	
	return true;
	//document.writeform.submit();
 }    

