package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;
import command.*;

public class NoticeContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = NoticeDAO.getDao();
		NoticeDTO dto = dao.getContent(num);
		
		String content = dto.getContent();
		content = content.replace("\n", "<br>");
		request.setAttribute("content", content);
		request.setAttribute("dto", dto);
		request.setAttribute("ref", new Integer(dto.getRef()));
		request.setAttribute("re_step", new Integer(dto.getRe_step()));
		request.setAttribute("re_level", new Integer(dto.getRe_level()));
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
		
		return "/notice/noticeContent.jsp";
	}//requestPro
}//class
