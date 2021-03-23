package qna;

import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

public class QnaDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	private static QnaDAO dao=new QnaDAO();
	
	private QnaDAO(){}
	
	public static QnaDAO getDao(){
		return dao;
	}//getDAO()
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()
	
	public void insertQna(QnaDTO dto) throws Exception{
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		int number=0;
		
		try{
			con=getCon();
			
			pstmt=con.prepareStatement("select max(num) from qna");
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				number=rs.getInt(1)+1;
				
			}else{//처음 글일때
				number=1;	
			}//else
			
			if(num!=0){
				
				//답글끼워 넣기 위치 확보
				sql="update qna set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step=re_step+1;
				re_level=re_level+1;//글깊이
			}else{
				ref=number;
				re_step=0;
				re_level=0;
			}//else
			
			sql="insert into qna(writer,category,subject,regdate,modifydate,ref,re_step,re_level,content,ip,bounds) values(?,?,?,NOW(),NOW(),?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getSubject());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			pstmt.setInt(9, dto.getBounds());
			
			pstmt.executeUpdate();
				
		}catch(Exception ex1){
			System.out.println("insertQna() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}//insertQna()
	
	public int getQnaCount() throws Exception{
		
		int x=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from qna");
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getQnaCount() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	
		return x;
		
	}//getQnaCount()
	
	public List getQnaList(int start, int cnt, String list_category) throws Exception{
		List<QnaDTO> list=null;
		try{
			con=getCon();
			
			
			if(list_category==null||list_category.equals("")||list_category.equals("전체 글")){
				sql="select * from qna order by ref desc,re_step asc limit ?,?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, cnt);
				rs=pstmt.executeQuery();
			}else{
				sql="select * from qna where category=? order by ref asc,re_step asc limit ?,?";//des로바꿔보기
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, list_category);
				pstmt.setInt(2, start-1);
				pstmt.setInt(3, cnt);
				rs=pstmt.executeQuery();
			}
			

			if(rs.next()){
				list=new ArrayList<QnaDTO>();//객체생성
				do{
					QnaDTO dto=new QnaDTO();//객체생성
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setCategory(rs.getString("category"));
					dto.setSubject(rs.getString("subject"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setModifydate(rs.getString("modifydate"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));			
					dto.setContent(rs.getString("content"));
					dto.setIp(rs.getString("ip"));
					dto.setBounds(rs.getInt("bounds"));

					list.add(dto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getQnaList 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		
		return list;	
		
	}//getQnaList()
	

	public QnaDTO getQna(int num) throws Exception {
		QnaDTO dto=null;
	
		try{
			con=getCon();
			sql="update qna set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new QnaDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setModifydate(rs.getString("modifydate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));			
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				dto.setBounds(rs.getInt("bounds"));
				
			}//if
			
		}catch(Exception ex1){
			System.out.println("getQna() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}
		return dto;
	}//getQna()
	
	public QnaDTO getUpdateQna(int num) throws Exception{
		
		QnaDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from Qna where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				dto=new QnaDTO();

				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setModifydate(rs.getString("modifydate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));			
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				dto.setBounds(rs.getInt("bounds"));
				
			}//while
		}catch(Exception ex1){
			System.out.println("getUpdateQna() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return dto;
		
	}//getUpdateQna()
	
	public int updateQna(QnaDTO dto) throws Exception{
		int x=-10;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			if(rs.next()){


				sql="update qna set subject=?, category=?, content=?, bounds=?, modifydate=NOW() where num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getSubject());
				pstmt.setString(2, dto.getCategory());
				pstmt.setString(3, dto.getContent());
				pstmt.setInt(4, dto.getBounds());
				pstmt.setInt(5, dto.getNum());
				pstmt.executeUpdate();
					
				x=1;
					
			}else{
				x=0;
			}
		
		}catch(Exception ex1){
			System.out.println("updateQna() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}				
			}catch(Exception ex2){}
		}//finally
		return x;
		
	}//updateQna()
	
	public int deleteQna(int num) throws Exception{
		
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement("delete from qna where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				x=1;
			}else{
				x=0;
			}
	
		}catch(Exception ex1){
			System.out.println("deleteQna() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
		
	}//deleteQna()
	

}//class end






