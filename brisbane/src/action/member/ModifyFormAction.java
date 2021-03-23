package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import member.*;

public class ModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id=request.getParameter("id");
		MemberDAO dao=MemberDAO.getInstance();		
		MemberDTO dto=dao.getMember(id);
		String addr=dto.getAddr();
		String ad[]=addr.split(",");
		dto.setAddr(ad[0]);
		
		//�ش�信�� ����� �Ӽ�
		request.setAttribute("dto", dto);
		request.setAttribute("addr2", ad[1]);
		
		return "/member/modifyForm.jsp";
	}//requestPro
	
}//class end
