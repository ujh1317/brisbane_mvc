 package action.faq;
import javax.servlet.http.*;


import command.CommandAction;

import java.util.*;//List,ArrayList

import brisbane.*;//DAO,DTO

//인터페이스를 상속 받아서 , 구현 클래스 작업 하는 곳
//1.액션은 jsp처리 로직을 액션에다 한다 
//2.DAO메서드 호출하여 결과값 받는다
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//처리
				String pageNum=request.getParameter("pageNum");
				if(pageNum==null){
					pageNum="1";
				}//if end
				
				int pageSize=10;//한 페이지 당 글 갯수 
				int currentPage=Integer.parseInt(pageNum);
				
				int startRow=(currentPage-1)*pageSize+1;//한 페이지의 시작 글번호
				int endRow=currentPage*pageSize;//한페이지의 마지막 글번호
				
				
				int count=0;// 총 글갯수 넣을 변수 
				int number=0;//글번호 처리 하기 위한 변수 
				int pageBlock=10;//블럭당 페이지 수
				
				List faqList=null;
				BrisbaneDAO dao=BrisbaneDAO.getDao();//dao 객체 얻기 
				count=dao.getArticleCount();//전체 글갯수 얻기 
				
				if(count>0){//글이 존재 하면
					faqList=dao.getList(startRow, pageSize);//dao메서드 호출하고 결과 받는다
				}else{//글이 없으면
					faqList=Collections.EMPTY_LIST;//비어있다는 뜻
				}//else end
				
				number=count-(currentPage-1)*pageSize;//출력할 글번호
				int pageCount=count/pageSize+(count%pageSize==0?0:1);
				               //  몫                      꽁다리 레코드 수 ( 31개 글 , 페이지는 4개 페이지)
				
				int startPage=(int)(currentPage/pageBlock)*10+1;// 시작 페이지 구하기 
				int endPage=startPage+pageBlock-1;//끝 페이지

				//JSP에서 사용 하도록 request.setAttribute("key",value) 작업을 한다 
				
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
				
				request.setAttribute("faqList", faqList);
				
				return "/faq/list.jsp";//뷰 리턴==> 컨트롤러로 가서 ==> 뷰로 넘어간다  
	}//requestPro()

}//class end
