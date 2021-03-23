 package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;//DAO,DTO
import brisbane.*;

//인터페이스 상속 받아 구현한 클래스
public class WriteProAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable {
       
      request.setCharacterEncoding("UTF-8");
      
      BrisbaneDTO dto=new BrisbaneDTO();//객체생성
      //클라이언트가 보내준 데이터 받아서 dto에 저장
      dto.setNum(Integer.parseInt(request.getParameter("num")));
      dto.setName(request.getParameter("name"));
      dto.setSubject(request.getParameter("subject"));
      
      dto.setRef(Integer.parseInt(request.getParameter("ref")));
      dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
      dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
      dto.setQuestion("1");
      dto.setAnswer("1");
      dto.setContent(request.getParameter("content"));
      dto.setIp(request.getRemoteAddr());
      dto.setPass(request.getParameter("pass"));
      
      BrisbaneDAO dao=BrisbaneDAO.getDao();//dao객체얻기
      dao.insertArticle(dto);//dao메서드 호출
      
      return "/faq/writePro.jsp";//뷰 리턴
   }

}//class end