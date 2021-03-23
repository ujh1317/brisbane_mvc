package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

import java.util.*;

import notice.*;

public class NoticeListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}//if
		
		int pageSize = 10; //한 페이지당 글갯수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1; //한페이지의 시작 글번호
		int endRow = currentPage*pageSize; //한페이지의 마지막 글번호
		int count = 0; //총 글갯수 넣을 변수
		int number = 0; //글 번호 처리하기 위한 변수
		int pageBlock = 10; //블럭당 페이지수
		
		List noticeList = null;
		NoticeDAO dao = NoticeDAO.getDao();
		count = dao.getCount(); //전체 글갯수
		
		if(count>0){ //글이 있을때
			noticeList = dao.getList(startRow, pageSize);
		}else{ //글이 없을때
			noticeList = Collections.EMPTY_LIST;
		}//else
		
		number = count-(currentPage-1)*pageSize; //출력할 글번호
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		int startPage = (int)(currentPage/pageBlock)*10+1; //시작페이지
		int endPage = startPage+pageBlock-1; //마지막페이지
		
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
		request.setAttribute("noticeList", noticeList);
		
		return "/notice/noticeList.jsp";
	}//requestPro()
}//class
