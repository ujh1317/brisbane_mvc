package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;//DAO,DTO(VO)
import brisbane.*;
public class UpdateFormAction implements CommandAction {

	@Override //��� ���� �޼��� ���� ������ 
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao��ü ��� 
		BrisbaneDTO dto=dao.updateGetArticle(num);//num�� �ش��ϴ� ������ ���� ��´� 
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		
		
		return "/faq/updateForm.jsp";//�丮��
	}

}//class end
