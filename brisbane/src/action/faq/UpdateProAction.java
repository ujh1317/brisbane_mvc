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
		
		BrisbaneDTO dto=new BrisbaneDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 받아서 dto에 setter 작업 
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
	
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao객체 얻기
		int check=dao.updateArticle(dto);//dao메서드 호출 결과값 받는다
		
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
	
		return "/faq/updatePro.jsp";
		
	}//requestPro() end

}//class end
