package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.*;
import command.*;
public class DeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getDao();
		int check=dao.deleteQna(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/qna/delete.jsp";
	}

}//class end
