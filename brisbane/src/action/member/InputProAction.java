package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import member.*;
import java.sql.Timestamp;
public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		MemberDTO dto=new MemberDTO();
		String id=request.getParameter("id");
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setJumin1(request.getParameter("jumin1"));
		dto.setJumin2(request.getParameter("jumin2"));
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setZipcode(request.getParameter("zipcode"));
		
		String addr=request.getParameter("addr");
		String addr2=request.getParameter("addr2");
		addr=addr+","+addr2;
		dto.setAddr(addr);
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		MemberDAO dao=MemberDAO.getInstance();
		dao.insertMember(dto);
		request.setAttribute("id", id);
		

		return "/member/inputPro.jsp";
	}

}
