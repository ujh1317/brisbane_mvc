package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brisbane.*;
import command.*;
import boardmysql.*;//DAO,DTO(VO)

//�������̽��� ��� �޾Ƽ� ���� Ŭ���� �ۼ�
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");

		//�� â���� ������ ���� �ް�
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String pw=request.getParameter("pass");
		
		//dao �޼��� ȣ���Ͽ� ����� �ް�
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao��ü ��� 
		int check=dao.deleteArticle(num, pw);//dao�޼��� ȣ���Ͽ� ����� �޴´�
		
		//�ش��(jsp)���� ����� �Ӽ��� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
	
		return "/faq/deletePro.jsp";//�丮��
	}//requestPro() end

}//class end
