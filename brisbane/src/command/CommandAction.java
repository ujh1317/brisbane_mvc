package command;
import javax.servlet.http.*;
//�������̽�
public interface CommandAction {
	//��ûó��:requestPro() 
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable;
	
	
}//interface
