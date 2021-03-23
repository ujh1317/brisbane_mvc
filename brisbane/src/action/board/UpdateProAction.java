package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		BoardDTO dto=new BoardDTO();
		//클라이언트가 보내준 데이터 받아서 dto에 setter작업
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		BoardDAO dao=BoardDAO.getDao();
		int check=dao.updateArticle(dto);
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
		
		return "/board/updatePro.jsp";
	}//requestPro

}//UpdateProAction
