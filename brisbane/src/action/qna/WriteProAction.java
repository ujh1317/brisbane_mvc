package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.*;
import command.*;
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String writer=(String)session.getAttribute("memId");
		
		QnaDTO dto=new QnaDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(writer);
		dto.setCategory(request.getParameter("category"));
		dto.setSubject(request.getParameter("subject"));
		
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		dto.setBounds(Integer.parseInt(request.getParameter("bounds")));
		QnaDAO dao=QnaDAO.getDao();
		dao.insertQna(dto);
		
		return "/qna/writePro.jsp";
	}

}
