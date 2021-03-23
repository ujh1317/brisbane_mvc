package action.board;
import javax.servlet.http.*;

import java.util.*;
import boardmysql.*;
import command.CommandAction;

//�������̽� ��ӹ޾Ƽ� �����۾��ϴ� ��
//1.�׼��� jspó�������� �׼ǿ����Ѵ�.
//2.DAO�޼��忡�� ȣ���Ͽ� ������޴´�.
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		
		int pageSize=10;
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		int count=0;
		int number=0;
		int pageBlock=10;
		List boardList=null;
		BoardDAO dao=BoardDAO.getDao();
		count=dao.getArticleCount();
		if(count>0){
			boardList=dao.getList(startRow, pageSize);
		}else{
			boardList=Collections.EMPTY_LIST;//����ִٴ� ��
		}
		
		number=count-(currentPage-1)*pageSize;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage=(int)(currentPage/pageBlock)*10+1;
		int endPage=startPage+pageBlock-1;
		//jsp���� ����ϵ��� request.setAttribute �۾�!
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageBlock", new Integer(pageBlock));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("boardList", boardList);
		
		return "/board/list.jsp";
	}//requestPro

}//class








