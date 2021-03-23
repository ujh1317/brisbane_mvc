package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brisbane.*;
import command.*;
import boardmysql.*;//DAO,DTO(VO)
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		BrisbaneDTO dto=new BrisbaneDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� dto�� setter �۾� 
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
	
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao��ü ���
		int check=dao.updateArticle(dto);//dao�޼��� ȣ�� ����� �޴´�
		
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
	
		return "/faq/updatePro.jsp";
		
	}//requestPro() end

}//class end
