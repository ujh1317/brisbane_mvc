package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;//DAO,DTO(VO)
import brisbane.*;
public class UpdateFormAction implements CommandAction {

	@Override //상속 받은 메서드 내용 재정의 
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao객체 얻기 
		BrisbaneDTO dto=dao.updateGetArticle(num);//num에 해당하는 수정할 내용 얻는다 
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		
		
		return "/faq/updateForm.jsp";//뷰리턴
	}

}//class end
