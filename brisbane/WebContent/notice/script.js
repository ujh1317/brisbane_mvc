function writeSave(){
	//alert("aaa")
	if(document.noticeform.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.noticeform.writer.focus();
	  return false;
	}
	if(document.noticeform.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.noticeform.subject.focus();
	  return false;
	}
	
	if(document.noticeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.noticeform.content.focus();
	  return false;
	}
   
	//document.writeform.submit();
	return true;
 }    

