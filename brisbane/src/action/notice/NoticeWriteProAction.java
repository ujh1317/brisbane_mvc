package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.*;
import notice.*;

public class NoticeWriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("memId");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(writer);
		dto.setSubject(request.getParameter("subject"));
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		
		NoticeDAO dao = NoticeDAO.getDao();
		dao.noticeWrite(dto);
		
		return "/notice/noticeWritePro.jsp";
	}//requestPro
}//class
