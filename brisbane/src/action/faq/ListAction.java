 package action.faq;
import javax.servlet.http.*;


import command.CommandAction;

import java.util.*;//List,ArrayList

import brisbane.*;//DAO,DTO

//�������̽��� ��� �޾Ƽ� , ���� Ŭ���� �۾� �ϴ� ��
//1.�׼��� jspó�� ������ �׼ǿ��� �Ѵ� 
//2.DAO�޼��� ȣ���Ͽ� ����� �޴´�
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//ó��
				String pageNum=request.getParameter("pageNum");
				if(pageNum==null){
					pageNum="1";
				}//if end
				
				int pageSize=10;//�� ������ �� �� ���� 
				int currentPage=Integer.parseInt(pageNum);
				
				int startRow=(currentPage-1)*pageSize+1;//�� �������� ���� �۹�ȣ
				int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
				
				
				int count=0;// �� �۰��� ���� ���� 
				int number=0;//�۹�ȣ ó�� �ϱ� ���� ���� 
				int pageBlock=10;//���� ������ ��
				
				List faqList=null;
				BrisbaneDAO dao=BrisbaneDAO.getDao();//dao ��ü ��� 
				count=dao.getArticleCount();//��ü �۰��� ��� 
				
				if(count>0){//���� ���� �ϸ�
					faqList=dao.getList(startRow, pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
				}else{//���� ������
					faqList=Collections.EMPTY_LIST;//����ִٴ� ��
				}//else end
				
				number=count-(currentPage-1)*pageSize;//����� �۹�ȣ
				int pageCount=count/pageSize+(count%pageSize==0?0:1);
				               //  ��                      �Ǵٸ� ���ڵ� �� ( 31�� �� , �������� 4�� ������)
				
				int startPage=(int)(currentPage/pageBlock)*10+1;// ���� ������ ���ϱ� 
				int endPage=startPage+pageBlock-1;//�� ������

				//JSP���� ��� �ϵ��� request.setAttribute("key",value) �۾��� �Ѵ� 
				
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
				
				return "/faq/list.jsp";//�� ����==> ��Ʈ�ѷ��� ���� ==> ��� �Ѿ��  
	}//requestPro()

}//class end
