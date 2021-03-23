package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.*;
import command.*;
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=0, ref=1, re_step=0, re_level=0;
		if(request.getParameter("num")!=null){//답글이면
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		}//if
		
		QnaDAO dao=QnaDAO.getDao();
		QnaDTO dto=dao.getQna(num);
		
		//jsp 에서 사용할 속성 설정
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("re_level", new Integer(re_level));		
		request.setAttribute("re_step", new Integer(re_step));
		request.setAttribute("dto", dto);
		
		return "/qna/writeForm.jsp";
	}

}
