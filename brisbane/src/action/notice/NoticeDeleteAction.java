package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;
import command.*;

public class NoticeDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = NoticeDAO.getDao();
		int check = dao.delete(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/notice/noticeDelete.jsp";
	}//requestPro()
}//class
