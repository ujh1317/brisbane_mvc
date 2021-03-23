package boardmysql;
import java.sql.*;
import java.util.*;//List,ArrayList
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
public class BoardDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	//�̱��� ��ü����.�޸� ����ȿ��
	private static BoardDAO dao=new BoardDAO();
	
	//jsp���� dao��ü ���
	public static BoardDAO getDao(){
		return dao;
	}
	
	//�ܺο��� ��ü���� ����.
	private BoardDAO(){}
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon
	
	public void insertArticle(BoardDTO dto) throws Exception{
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		int number=0;
		
		try{
			con=getCon();
			
			pstmt=con.prepareStatement("select max(num) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				number=rs.getInt(1)+1;
				
			}else{//ó�� ���϶�
				number=1;	
			}//else
			
			if(num!=0){
				//��۳��� �ֱ� ��ġ Ȯ��
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step=re_step+1;
				re_level=re_level+1;//�۱���
			}else{
				ref=number;
				re_step=0;
				re_level=0;
			}//else
			
			sql="insert into board(writer,subject,pw,regdate,ref,re_step,re_level,content,ip) values(?,?,?,NOW(),?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPw());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception ex1){
			System.out.println("insertArticle() ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}//insertArticle
	
	//=========================
	//�۰��� 
	//=========================
	
	public int getArticleCount() throws Exception{
		int x=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from board");
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getArticleCount() ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	
		return x;
		
	}//getArticleCount()
	
	//=========================
	//list
	//=========================
	
	public List getList(int start,int cnt) throws Exception{
		List<BoardDTO> list=null;
		try{
			con=getCon();
			sql="select * from board order by ref asc,re_step asc limit ?,?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList<BoardDTO>();//��ü����
				do{
					BoardDTO dto=new BoardDTO();//��ü����
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setPw(rs.getString("pw"));
					dto.setRegdate(rs.getTimestamp("regdate"));
					//System.out.println("��¥:"+rs.getDate("regdate"));
					//System.out.println("��¥:"+rs.getString("regdate"));
					//System.out.println("��¥:"+rs.getTimestamp("regdate"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setContent(rs.getString("content"));
					dto.setIp(rs.getString("ip"));
					
					list.add(dto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getList ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		
		return list;
	}//getList
	//===========================
	//�۳��� ����
	//===========================
	public BoardDTO getArticle(int num) throws Exception{
		BoardDTO dto=null;
		
		try{
			con=getCon();
			sql="update board set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				
			}//if
			
		}catch(Exception ex1){
			System.out.println("getArticle ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}
		return dto;
	}//getArticle
	
	//======================
	//�ۼ���
	//======================
	
	public BoardDTO updateGetArticle(int num) throws Exception{//������ �־ ����ó�� �ؾ���
		BoardDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				dto=new BoardDTO();

				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				
			}//while
		}catch(Exception ex1){
			System.out.println("updateGetArticle ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return dto;
	}//updateGetArticle
	
	//=============================
	//������ ����
	//=============================
	public int updateArticle(BoardDTO dto) throws Exception{
		String dbpw="";
		int x=-10;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			if(rs.next()){
				dbpw=rs.getString("pw");
				if(dbpw.equals(dto.getPw())){
					sql="update board set writer=?, subject=?, content=? where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setInt(4, dto.getNum());
					
					pstmt.executeUpdate();
					
					x=1;
					
				}else{
					x=0;
				}//else
			}//if
		}catch(Exception ex1){
			System.out.println("updateArticle ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}				
			}catch(Exception ex2){}
		}//finally
		return x;
	}//updateArticle
	
	//=============================
	//�ۻ���
	//=============================
	
	public int deleteArticle(int num,String pw) throws Exception{
		String dbpw="";
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dbpw=rs.getString("pw");
				if(pw.equals(dbpw)){
					pstmt=con.prepareStatement("delete from board where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x=1;
				}else{
					x=0;
				}
			}//if
			
		}catch(Exception ex1){
			System.out.println("deleteArticle() ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}//deleteArticle
	
	
}//class






























