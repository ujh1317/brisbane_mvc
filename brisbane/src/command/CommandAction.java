package command;
import javax.servlet.http.*;
//인터페이스
public interface CommandAction {
	//요청처리:requestPro() 
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable;
	
	
}//interface
