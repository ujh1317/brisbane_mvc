package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		//해당뷰에서 사용할 속성들
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
		
		
		return "/board/deleteForm.jsp";
	}//requestPro

}//class
