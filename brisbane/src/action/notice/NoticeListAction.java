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
		
		int pageSize = 10; //�� �������� �۰���
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1; //���������� ���� �۹�ȣ
		int endRow = currentPage*pageSize; //���������� ������ �۹�ȣ
		int count = 0; //�� �۰��� ���� ����
		int number = 0; //�� ��ȣ ó���ϱ� ���� ����
		int pageBlock = 10; //���� ��������
		
		List noticeList = null;
		NoticeDAO dao = NoticeDAO.getDao();
		count = dao.getCount(); //��ü �۰���
		
		if(count>0){ //���� ������
			noticeList = dao.getList(startRow, pageSize);
		}else{ //���� ������
			noticeList = Collections.EMPTY_LIST;
		}//else
		
		number = count-(currentPage-1)*pageSize; //����� �۹�ȣ
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		int startPage = (int)(currentPage/pageBlock)*10+1; //����������
		int endPage = startPage+pageBlock-1; //������������
		
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
