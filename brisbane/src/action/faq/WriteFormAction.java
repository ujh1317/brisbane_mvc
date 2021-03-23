package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
//인터페이스를 상속 받아 구현한다
//원글,답글
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//원글,답글
		int num=0, ref=1, re_step=0, re_level=0;//변수선언

		if(request.getParameter("num") != null){//답글이면
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		}//if end

		//해당뷰(jsp)에서 사용항 속성 설정
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("re_step", new Integer(re_step));
		request.setAttribute("re_level", new Integer(re_level));
		request.getAttribute("name");
		return "/faq/writeForm.jsp";//뷰 리턴
	}//requestPro() end

}//class end
