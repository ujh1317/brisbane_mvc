package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

//인터페이스 상속 받아 구현하는 클래스
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		//해당뷰(jsp)사용할 속성들
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
	
		return "/faq/deleteForm.jsp";//뷰리턴
	}//requestPro() end

}//class end
