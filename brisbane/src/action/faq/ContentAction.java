package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;//DAO,DTO
import brisbane.*;

public class ContentAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable {
      
      int num=Integer.parseInt(request.getParameter("num"));
      String pageNum=request.getParameter("pageNum");
      
      BrisbaneDAO dao=BrisbaneDAO.getDao();//dao객체 얻는다 
      BrisbaneDTO dto=dao.getArticle(num);//num에 해당하는 글을 얻어온다
      
      String content=dto.getContent();
      content=content.replace("\n", "<br>");
      
      request.setAttribute("content", content);
      request.setAttribute("dto", dto);
      
      request.setAttribute("ref",new Integer(dto.getRef()));
      request.setAttribute("re_step",new Integer(dto.getRe_step()));
      request.setAttribute("re_level",new Integer(dto.getRe_level()));

      
      request.setAttribute("num", new Integer(num));
      request.setAttribute("pageNum", pageNum);
      return "/faq/content.jsp";//뷰리턴
   }//requestPro() end

}//class end