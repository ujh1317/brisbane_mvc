
function check(){
	
	if(document.writeForm.subject.value==""){		
		alert("제목을 입력하세요.");
		document.writeForm.subject.focus();
		return false;
	}
	if(document.writeForm.category.value==""){		
		alert("카테고리를 선택하세요.");
		document.writeForm.category.focus();
		return false;
	}
	if(document.writeForm.content.value==""){		
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
	if(document.writeForm.bounds.value==""){		
		alert("공개 범위를 선택하세요.");
		document.writeForm.bounds.focus();
		return false;
	}
		
		
	document.writeForm.submit();	
		
}//check