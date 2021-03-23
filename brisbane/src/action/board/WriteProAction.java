package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.*;
import boardmysql.*;
// �������̽� ��ӹ޾� ������ Ŭ����

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("memId");
		
		BoardDTO dto=new BoardDTO();
		//Ŭ���̾�Ʈ�� ������ ������ �޾Ƽ� dto�� ����
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setPw(request.getParameter("pw"));
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		
		BoardDAO dao=BoardDAO.getDao();
		dao.insertArticle(dto);
		
		
		return "/board/writePro.jsp";
	}

}


















