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
      
      BrisbaneDAO dao=BrisbaneDAO.getDao();//dao��ü ��´� 
      BrisbaneDTO dto=dao.getArticle(num);//num�� �ش��ϴ� ���� ���´�
      
      String content=dto.getContent();
      content=content.replace("\n", "<br>");
      
      request.setAttribute("content", content);
      request.setAttribute("dto", dto);
      
      request.setAttribute("ref",new Integer(dto.getRef()));
      request.setAttribute("re_step",new Integer(dto.getRe_step()));
      request.setAttribute("re_level",new Integer(dto.getRe_level()));

      
      request.setAttribute("num", new Integer(num));
      request.setAttribute("pageNum", pageNum);
      return "/faq/content.jsp";//�丮��
   }//requestPro() end

}//class end