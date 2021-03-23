package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brisbane.*;
import command.*;
import boardmysql.*;//DAO,DTO(VO)

//인터페이스를 상속 받아서 구현 클래스 작성
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");

		//웹 창에서 보내준 내용 받고
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String pw=request.getParameter("pass");
		
		//dao 메서드 호출하여 결과값 받고
		BrisbaneDAO dao=BrisbaneDAO.getDao();//dao객체 얻기 
		int check=dao.deleteArticle(num, pw);//dao메서드 호출하여 결과값 받는다
		
		//해당뷰(jsp)에서 사용할 속성들 지정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
	
		return "/faq/deletePro.jsp";//뷰리턴
	}//requestPro() end

}//class end
