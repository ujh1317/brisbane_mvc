package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

//�������̽� ��� �޾� �����ϴ� Ŭ����
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		//�ش��(jsp)����� �Ӽ���
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
	
		return "/faq/deleteForm.jsp";//�丮��
	}//requestPro() end

}//class end
