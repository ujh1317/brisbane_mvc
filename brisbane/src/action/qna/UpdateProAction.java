package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.*;
import command.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");

		
		QnaDTO dto=new QnaDTO();
		//클라이언트가 보내준 데이터 받아서 dto에 setter작업
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setSubject(request.getParameter("subject"));
		dto.setCategory(request.getParameter("category"));
		dto.setContent(request.getParameter("content"));
		dto.setBounds(Integer.parseInt(request.getParameter("bounds")));
		
		QnaDAO dao=QnaDAO.getDao();
		int check=dao.updateQna(dto);
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
		
		return "/qna/updatePro.jsp";
	}

}//class end
