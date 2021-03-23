package brisbane;
import java.sql.*;//Connection,Statement,PreparedStatement,ResultSet
import java.util.*;//List,ArrayList

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

//DAO
public class BrisbaneDAO {
   //����
   Connection con=null;
   Statement stmt=null;
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   String sql="";
   
   //�̱��� ��ü����,�޸� ���� ȿ���� �ִ� 
   private static BrisbaneDAO dao=new BrisbaneDAO();//��ü ����
   
   //jsp���� dao ��ü���
   public static BrisbaneDAO getDao(){
      return dao;
   }
   
   //����Ʈ ������:private�����ϸ� �ܺο��� ��ü���� ����
   private BrisbaneDAO(){}
   
   //Ŀ�ؼ� ��� 
   private Connection getCon() throws Exception{
      Context ct=new InitialContext();
      DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
      return ds.getConnection();
   }//getCon() end----------------------
   
   //-------------------
   // ����,��� ����
   //-------------------
   public void insertArticle(BrisbaneDTO dto) throws Exception{
      int num=dto.getNum();
      int ref=dto.getRef();
      int re_step=dto.getRe_step();
      int re_level=dto.getRe_level();
      
      int number=0;//�� �׷� ó���ϱ����� ����
      try{
         con=getCon();//Ŀ�ؼ� ���
         
         //�ִ� �۹�ȣ ��� 
         pstmt=con.prepareStatement("select max(num) from faq");
         rs=pstmt.executeQuery();//���� ����
         
         if(rs.next()){//���� ������
            number=rs.getInt(1)+1;// �ִ�۹�ȣ + 1
         }else{//ó�� ���϶�,���� ������
            number=1;// ref=number
         }//else end
         
         if(num != 0){//��� �϶�
            //����̸� ��� �����ֱ���  ��ġ Ȯ��
            sql="update faq set re_step=re_step+1 where ref=? and re_step>?";
            pstmt=con.prepareStatement(sql);
            //?�� ä���
            pstmt.setInt(1, ref);
            pstmt.setInt(2, re_step);
            pstmt.executeUpdate();//���� ���� 
            
            re_step=re_step+1;//�� ����
            re_level=re_level+1;//�� ����
            
         }else{//����
            ref=num;//�۹�ȣ�� �� �׷��ȣ�� �ȴ� 
            re_step=0;
            re_level=0;
         }//else end
         
         sql="insert into faq(name,subject,pass,regdate,ref,re_step,re_level,answer,question,ip,content) values(?,?,?,NOW(),?,?,?,?,?,?,?)";
         //NOW() : ����� �ú��� mysql
         //curdate() : �����  mysql
         //sysdate : ����Ŭ 
         
         pstmt=con.prepareStatement(sql);
         //?�� ä��� 
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getSubject());
         pstmt.setString(3, dto.getPass());
         //��¥
         pstmt.setInt(4, ref);
         pstmt.setInt(5, re_step);
         pstmt.setInt(6, re_level);
         pstmt.setString(7,dto.getAnswer());
         pstmt.setString(8,dto.getQuestion());
         
         pstmt.setString (9, dto.getIp());
         pstmt.setString(10, dto.getContent());
     
         
         pstmt.executeUpdate();//insert,update,delete ���� ����
      }catch(Exception ex1){
         System.out.println("insertArticle() ���� :"+ex1);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally end
   }//insertArticle() end
   //------------------------
   // �� ����
   //------------------------
   public int getArticleCount() throws Exception{
      int x=0;
      try{
         con=getCon();//Ŀ�ؼ� ��� 
         pstmt=con.prepareStatement("select count(*) from faq");
         rs=pstmt.executeQuery();//���� ���� 
         if(rs.next()){
            x=rs.getInt(1);//�� �� ���� 
         }//if end
         
      }catch(Exception ex1){
         System.out.println("getArticleCount() ���� :"+ex1);
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
   //����Ʈ
   //------------------
   public List getList(int start,int cnt) throws Exception{
      List<BrisbaneDTO> list=null;
      try{
         con=getCon();//Ŀ�ؼ� ��� 
         sql="select * from faq order by ref asc,re_step asc limit ?,?";
         //limit ������ġ,����
         
         pstmt=con.prepareStatement(sql);
         //?�� ä���
         pstmt.setInt(1, start-1);//������ġ
         pstmt.setInt(2, cnt);
         rs=pstmt.executeQuery();//���� ����
         
         if(rs.next()){
            list=new ArrayList<BrisbaneDTO>();//��ü ����
            do{
               BrisbaneDTO dto=new BrisbaneDTO();//��ü����
               
               dto.setNum(rs.getInt("num"));
               dto.setName(rs.getString("name"));
               dto.setSubject(rs.getString("subject"));
               dto.setEmail(rs.getString("Email"));
               dto.setPass(rs.getString("pass"));
               
               dto.setRegdate(rs.getTimestamp("regdate"));
               
               System.out.println("��¥:"+rs.getDate("regdate"));
               System.out.println("��¥:"+rs.getString("regdate"));
               System.out.println("��¥:"+rs.getTimestamp("regdate"));
               
               dto.setCount(rs.getInt("count"));//��ȸ��
               dto.setRef(rs.getInt("ref"));//�� �׷�
               dto.setRe_step(rs.getInt("re_step"));//�� ����
               dto.setRe_level(rs.getInt("re_level"));//�� ���� 
               
               dto.setQuestion(rs.getString("question"));//�۳���
               dto.setAnswer(rs.getString("answer"));//�� ���
               dto.setIp(rs.getString("ip"));//ip
               
               list.add(dto);//list�� �ִ´� *********
               
            }while(rs.next());
         }//if end
      }catch(Exception ex1){
         System.out.println("getList() ���� :"+ex1);
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
   // �� �����
   //-------------------
   public BrisbaneDTO getArticle(int num) throws Exception{
      BrisbaneDTO dto=null;
      
      try{
         con=getCon();//Ŀ�ؼ� ��� 
         
         //��Ƚ�� ����----
         sql="update faq set count=count+1 where num=?";
         pstmt=con.prepareStatement(sql);//������ ���� ���� 
         pstmt.setInt(1, num);
         pstmt.executeUpdate();//���� ����
         
         //�� ���� ���� ���� ���� �۾� -------
         pstmt=con.prepareStatement("select * from faq where num=?");
         pstmt.setInt(1,num);// ? �� ä��� 
         rs=pstmt.executeQuery();//���� ���� 
         
         if(rs.next()){
            dto=new BrisbaneDTO();//��ü����
            
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
            


            dto.setQuestion(rs.getString("question"));//�۳���
            dto.setAnswer(rs.getString("answer"));//���
            dto.setIp(rs.getString("ip"));
            
         }//if end
         
      }catch(Exception ex1){
         System.out.println("getArticle() ���� :"+ex1);
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
   // �ۼ���,jsp������
   //-----------------
   public BrisbaneDTO updateGetArticle(int num) throws Exception{
      BrisbaneDTO dto=null;
      try{
         con=getCon();//Ŀ�ؼ� ��� 
         pstmt=con.prepareStatement("select * from faq where num=?");
         pstmt.setInt(1, num);
         
         rs=pstmt.executeQuery();//���� ���� 
         while(rs.next()){
            dto=new BrisbaneDTO();//��ü����
            
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
            dto.setQuestion(rs.getString("question"));//�۳���
            dto.setAnswer(rs.getString("answer"));
            dto.setIp(rs.getString("ip"));
         }//while end
         
      }catch(Exception ex1){
         System.out.println("updateGetArticle() ���� :"+ex1);
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
   // DB�� ������ ����
   //---------------------
   public int updateArticle(BrisbaneDTO dto) throws Exception{
      String dbpw="";
      int x=-10;
      try{
         con=getCon();
         pstmt=con.prepareStatement("select pass from faq where num=?");
         pstmt.setInt(1, dto.getNum());//?�� ä��� 
         rs=pstmt.executeQuery();//���� ���� 
         
         if(rs.next()){
            dbpw=rs.getString("pass");
            if(dbpw.equals(dto.getPass())){//�Է��� ��ȣ�� DB��ȣ�� ��ġ�ϸ�
               //�� ����
               sql="update faq set name=?,subject=?,content=? where num=?";
               pstmt=con.prepareStatement(sql);//������ ���� ���� 
               
               pstmt.setString(1, dto.getName());//?�� ä���
               pstmt.setString(2, dto.getSubject());
               pstmt.setString(3, dto.getContent());
               pstmt.setInt(4, dto.getNum());
              
               pstmt.executeUpdate();//���� ���� 
               
               x=1;// ���� ����
            }else{//��ȣ�� Ʋ����
               x=0;
            }//else end
         }//if end
         
      }catch(Exception ex1){
         System.out.println("updateArticle() ���� :"+ex1);
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
   // �ۻ���
   //----------------------
   public int deleteArticle(int num,String pass) throws Exception{
      String dbpw="";
      int x=-100;
      try{
         con=getCon();//Ŀ�ؼ� ���
         pstmt=con.prepareStatement("select pass from faq where num=?");
         pstmt.setInt(1,num);
         rs=pstmt.executeQuery();
         
         if(rs.next()){
            dbpw=rs.getString("pass");
            if(pass.equals(dbpw)){//�Է��� ��ȣ�� db�� ��ȣ�� ������ ����
               pstmt=con.prepareStatement("delete from faq where num=?");
               pstmt.setInt(1,num);
               pstmt.executeUpdate();//���� ����
               x=1;//��������
            }else{
               x=0;//��������
            }
         }//if end
         
      }catch(Exception ex1){
         System.out.println("deleteArticle() ���� :"+ex1);
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