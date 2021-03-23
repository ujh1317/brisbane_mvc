package controller;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;//properties 파일 읽어 오려고
import java.util.*;//Map, HashMap
import command.CommandAction;//인터페이스 
//import action.board.*;

//클라이언트를 받는 곳이다 
//컨트롤러는 서블릿으로 만든다 
//컨트롤러
public class ControllerDispatcher extends HttpServlet{
private Map<String,Object> map=new HashMap<String,Object>();//변수,객체생성
   
   //init() 초기화 작업
   public void init(ServletConfig config) throws ServletException{
      String path=config.getServletContext().getRealPath("/");
      
      String pros=path+config.getInitParameter("proFile");//WEB-INF/Command.properties
      Properties pp=new Properties();//객체생성
      
      FileInputStream fn=null;
      try{
         fn=new FileInputStream(pros);//파일 읽어와서
         pp.load(fn);//properties파일에 load 작업
         
      }catch(Exception ex1){
         System.out.println("파일 읽기 예외 :"+ex1);
      }finally{
         try{
            fn.close();
         }catch(Exception ex2){}
      }//finally end
      
      Iterator keys=pp.keySet().iterator();
      
      while(keys.hasNext()){//자료가 있는 동안 반복 처리 
         /*
              /ch19/list.do=ch19.action.ListAction
         */
         
         String kkey=(String)keys.next();//   /board/list.do
         String className=pp.getProperty(kkey);//action.board.ListAction
         
         try{
            Class commandClass=Class.forName(className);//클래스를 만들고 
            Object commandObject=commandClass.newInstance();//객체를 만든다 
            
            map.put(kkey, commandObject);//map.put(key,value)
                  
         }catch(Exception ex3){
            System.out.println("property 파일 내용을 클래스 객체를 만드는 동안 예외 발생 :"+ex3);
         }
      }//while end--
   }//init() end--------------------
   
   public void doGet(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException
   {
      reqPro(request,response);//메서드 호출 
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException
   {
      reqPro(request,response);//메서드 호출 
   }
   
   //사용자 정의 메서드 
   private void reqPro(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException{
      String view="";//jsp널읗 변수 
      
      CommandAction commandAction=null;//Action 들을 넣을 변수 
      
      try{
         
         String uri=request.getRequestURI();//   /프로젝트이름/ch19/list.do
         
         if(uri.indexOf(request.getContextPath())==0){
            uri=uri.substring(request.getContextPath().length());
            //   /ch19/list.do
         }//if
         
         
         
         
         
         commandAction=(CommandAction)map.get(uri);
         
         view=commandAction.requestPro(request, response);//메서드를 호출 하여 view 얻는다 
         //list.jsp
         
      }catch(Throwable ex7){
         throw new ServletException(ex7);//예외 생성
      }//catch end
      
      //view 로 포워딩 작업
      request.setAttribute("CONTENT", view); //ch19/list.jsp
      //request.setAttribute("homepage", "/ch19/list.jsp"); //ch19/list.jsp
      
      RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
      rd.forward(request, response);//view(list.jsp)로 포워딩한다 
   }//reqPro() end
}//class end