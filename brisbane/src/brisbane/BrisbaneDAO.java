package brisbane;
import java.sql.*;//Connection,Statement,PreparedStatement,ResultSet
import java.util.*;//List,ArrayList

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

//DAO
public class BrisbaneDAO {
   //변수
   Connection con=null;
   Statement stmt=null;
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   String sql="";
   
   //싱글톤 객체생성,메모리 절약 효과가 있다 
   private static BrisbaneDAO dao=new BrisbaneDAO();//객체 생성
   
   //jsp에서 dao 객체얻기
   public static BrisbaneDAO getDao(){
      return dao;
   }
   
   //디폴트 생성자:private으로하면 외부에서 객체생성 못함
   private BrisbaneDAO(){}
   
   //커넥션 얻기 
   private Connection getCon() throws Exception{
      Context ct=new InitialContext();
      DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
      return ds.getConnection();
   }//getCon() end----------------------
   
   //-------------------
   // 원글,답글 쓰기
   //-------------------
   public void insertArticle(BrisbaneDTO dto) throws Exception{
      int num=dto.getNum();
      int ref=dto.getRef();
      int re_step=dto.getRe_step();
      int re_level=dto.getRe_level();
      
      int number=0;//글 그룹 처리하기위한 변수
      try{
         con=getCon();//커넥션 얻기
         
         //최대 글번호 얻기 
         pstmt=con.prepareStatement("select max(num) from faq");
         rs=pstmt.executeQuery();//쿼리 수행
         
         if(rs.next()){//글이 있을때
            number=rs.getInt(1)+1;// 최대글번호 + 1
         }else{//처음 글일때,글이 없을때
            number=1;// ref=number
         }//else end
         
         if(num != 0){//답글 일때
            //답글이면 답글 끼워넣기의  위치 확보
            sql="update faq set re_step=re_step+1 where ref=? and re_step>?";
            pstmt=con.prepareStatement(sql);
            //?값 채우기
            pstmt.setInt(1, ref);
            pstmt.setInt(2, re_step);
            pstmt.executeUpdate();//쿼리 수행 
            
            re_step=re_step+1;//글 순서
            re_level=re_level+1;//글 깊이
            
         }else{//원글
            ref=num;//글번호가 글 그룹번호가 된다 
            re_step=0;
            re_level=0;
         }//else end
         
         sql="insert into faq(name,subject,pass,regdate,ref,re_step,re_level,answer,question,ip,content) values(?,?,?,NOW(),?,?,?,?,?,?,?)";
         //NOW() : 년월일 시분초 mysql
         //curdate() : 년월일  mysql
         //sysdate : 오라클 
         
         pstmt=con.prepareStatement(sql);
         //?값 채우기 
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getSubject());
         pstmt.setString(3, dto.getPass());
         //날짜
         pstmt.setInt(4, ref);
         pstmt.setInt(5, re_step);
         pstmt.setInt(6, re_level);
         pstmt.setString(7,dto.getAnswer());
         pstmt.setString(8,dto.getQuestion());
         
         pstmt.setString (9, dto.getIp());
         pstmt.setString(10, dto.getContent());
     
         
         pstmt.executeUpdate();//insert,update,delete 쿼리 수행
      }catch(Exception ex1){
         System.out.println("insertArticle() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
   }//insertArticle() end
   //------------------------
   // 글 갯수
   //------------------------
   public int getArticleCount() throws Exception{
      int x=0;
      try{
         con=getCon();//커넥션 얻기 
         pstmt=con.prepareStatement("select count(*) from faq");
         rs=pstmt.executeQuery();//쿼리 수행 
         if(rs.next()){
            x=rs.getInt(1);//총 글 갯수 
         }//if end
         
      }catch(Exception ex1){
         System.out.println("getArticleCount() 예외 :"+ex1);
      }finally{
         try{
          if(rs!=null){rs.close();}
          if(pstmt!=null){pstmt.close();}
          if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
   
      return x;
   }//getArticleCount() end
    
   //------------------
   //리스트
   //------------------
   public List getList(int start,int cnt) throws Exception{
      List<BrisbaneDTO> list=null;
      try{
         con=getCon();//커넥션 얻기 
         sql="select * from faq order by ref asc,re_step asc limit ?,?";
         //limit 시작위치,갯수
         
         pstmt=con.prepareStatement(sql);
         //?값 채우기
         pstmt.setInt(1, start-1);//시작위치
         pstmt.setInt(2, cnt);
         rs=pstmt.executeQuery();//쿼리 수행
         
         if(rs.next()){
            list=new ArrayList<BrisbaneDTO>();//객체 생성
            do{
               BrisbaneDTO dto=new BrisbaneDTO();//객체생성
               
               dto.setNum(rs.getInt("num"));
               dto.setName(rs.getString("name"));
               dto.setSubject(rs.getString("subject"));
               dto.setEmail(rs.getString("Email"));
               dto.setPass(rs.getString("pass"));
               
               dto.setRegdate(rs.getTimestamp("regdate"));
               
               System.out.println("날짜:"+rs.getDate("regdate"));
               System.out.println("날짜:"+rs.getString("regdate"));
               System.out.println("날짜:"+rs.getTimestamp("regdate"));
               
               dto.setCount(rs.getInt("count"));//조회수
               dto.setRef(rs.getInt("ref"));//글 그룹
               dto.setRe_step(rs.getInt("re_step"));//글 순서
               dto.setRe_level(rs.getInt("re_level"));//글 깊이 
               
               dto.setQuestion(rs.getString("question"));//글내용
               dto.setAnswer(rs.getString("answer"));//글 답면
               dto.setIp(rs.getString("ip"));//ip
               
               list.add(dto);//list에 넣는다 *********
               
            }while(rs.next());
         }//if end
      }catch(Exception ex1){
         System.out.println("getList() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
      
      return list;
   }//getList() end

   //-------------------
   // 글 내용기
   //-------------------
   public BrisbaneDTO getArticle(int num) throws Exception{
      BrisbaneDTO dto=null;
      
      try{
         con=getCon();//커넥션 얻기 
         
         //조횟수 증가----
         sql="update faq set count=count+1 where num=?";
         pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
         pstmt.setInt(1, num);
         pstmt.executeUpdate();//쿼리 수행
         
         //글 내용 보기 위한 쿼리 작업 -------
         pstmt=con.prepareStatement("select * from faq where num=?");
         pstmt.setInt(1,num);// ? 값 채우기 
         rs=pstmt.executeQuery();//쿼리 수행 
         
         if(rs.next()){
            dto=new BrisbaneDTO();//객체생성
            
            dto.setNum(rs.getInt("num"));
            dto.setName(rs.getString("name"));
            dto.setSubject(rs.getString("subject"));
            dto.setPass(rs.getString("pass"));
            dto.setContent(rs.getString("content"));
            dto.setRegdate(rs.getTimestamp("regdate"));
            dto.setCount(rs.getInt("count"));
            
          
            
            dto.setRef(rs.getInt("ref"));
            dto.setRe_step(rs.getInt("re_step"));
            dto.setRe_level(rs.getInt("re_level"));
            


            dto.setQuestion(rs.getString("question"));//글내용
            dto.setAnswer(rs.getString("answer"));//답글
            dto.setIp(rs.getString("ip"));
            
         }//if end
         
      }catch(Exception ex1){
         System.out.println("getArticle() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
      
      return dto;
   }//getArticle() end
   //-----------------
   // 글수정,jsp보낼것
   //-----------------
   public BrisbaneDTO updateGetArticle(int num) throws Exception{
      BrisbaneDTO dto=null;
      try{
         con=getCon();//커넥션 얻기 
         pstmt=con.prepareStatement("select * from faq where num=?");
         pstmt.setInt(1, num);
         
         rs=pstmt.executeQuery();//쿼리 수행 
         while(rs.next()){
            dto=new BrisbaneDTO();//객체생성
            
            dto.setNum(rs.getInt("num"));
            dto.setName(rs.getString("name"));
            dto.setSubject(rs.getString("subject"));
            dto.setPass(rs.getString("pass"));
            
            dto.setRegdate(rs.getTimestamp("regdate"));
            dto.setCount(rs.getInt("count"));
            
            dto.setRef(rs.getInt("ref"));
            dto.setRe_step(rs.getInt("re_step"));
            dto.setRe_level(rs.getInt("re_level"));
            
            dto.setContent(rs.getString("content"));
            dto.setQuestion(rs.getString("question"));//글내용
            dto.setAnswer(rs.getString("answer"));
            dto.setIp(rs.getString("ip"));
         }//while end
         
      }catch(Exception ex1){
         System.out.println("updateGetArticle() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){rs.close();}
            if(con!=null){rs.close();}
         }catch(Exception ex2){}
      }//finally end
      
      return dto;
   }//updateGetArticle() end
   
   //---------------------
   // DB의 데이터 수정
   //---------------------
   public int updateArticle(BrisbaneDTO dto) throws Exception{
      String dbpw="";
      int x=-10;
      try{
         con=getCon();
         pstmt=con.prepareStatement("select pass from faq where num=?");
         pstmt.setInt(1, dto.getNum());//?값 채우기 
         rs=pstmt.executeQuery();//쿼리 수행 
         
         if(rs.next()){
            dbpw=rs.getString("pass");
            if(dbpw.equals(dto.getPass())){//입력한 암호와 DB암호가 일치하면
               //글 수정
               sql="update faq set name=?,subject=?,content=? where num=?";
               pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
               
               pstmt.setString(1, dto.getName());//?값 채우기
               pstmt.setString(2, dto.getSubject());
               pstmt.setString(3, dto.getContent());
               pstmt.setInt(4, dto.getNum());
              
               pstmt.executeUpdate();//쿼리 수행 
               
               x=1;// 수정 정상
            }else{//암호가 틀릴때
               x=0;
            }//else end
         }//if end
         
      }catch(Exception ex1){
         System.out.println("updateArticle() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
      
      return x;
   }//updateArticle() end
   
   //----------------------
   // 글삭제
   //----------------------
   public int deleteArticle(int num,String pass) throws Exception{
      String dbpw="";
      int x=-100;
      try{
         con=getCon();//커넥션 얻기
         pstmt=con.prepareStatement("select pass from faq where num=?");
         pstmt.setInt(1,num);
         rs=pstmt.executeQuery();
         
         if(rs.next()){
            dbpw=rs.getString("pass");
            if(pass.equals(dbpw)){//입력한 암호와 db의 암호가 같으면 삭제
               pstmt=con.prepareStatement("delete from faq where num=?");
               pstmt.setInt(1,num);
               pstmt.executeUpdate();//쿼리 수행
               x=1;//삭제성공
            }else{
               x=0;//삭제실패
            }
         }//if end
         
      }catch(Exception ex1){
         System.out.println("deleteArticle() 예외 :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
      return x;
   }//deleteArticle() end
   
}//class end