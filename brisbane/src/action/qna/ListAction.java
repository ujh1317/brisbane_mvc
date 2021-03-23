package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import qna.*;
import command.*;
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		String list_category=request.getParameter("list_category");
		if(list_category==null){
			list_category="";
		}

		int pageSize=10;
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		int count=0;
		int number=0;
		int pageBlock=10;
		List qnaList=null;
		QnaDAO dao=QnaDAO.getDao();
		count=dao.getQnaCount();
		if(count>0){
			qnaList=dao.getQnaList(startRow, pageSize, list_category);
		}else{
			qnaList=Collections.EMPTY_LIST;//비어있다는 뜻
		}
		
		number=(currentPage-1)*pageSize+1;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage=(int)(currentPage/pageBlock)*10+1;
		int endPage=startPage+pageBlock-1;
		//jsp에서 사용하도록 request.setAttribute 작업!
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list_category", list_category);
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
		
		request.setAttribute("qnaList", qnaList);
		
		
		return "/qna/list.jsp";
	}

}
