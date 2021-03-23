package notice;
import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

public class NoticeDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//싱글톤 객체생성
	private static NoticeDAO dao = new NoticeDAO();
	
	//jsp에서 dao객체얻기
	public static NoticeDAO getDao(){
		return dao;
	}//getDao()
	
	//디폴트 생성자
	public NoticeDAO(){}
	
	//커넥션
	private Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//글쓰기
	public void noticeWrite(NoticeDTO dto) throws Exception{
		int num = dto.getNum();
		int ref = dto.getRef();
		int re_step = dto.getRe_step();
		int re_level = dto.getRe_level();
		int number = 0; //글그룹 처리
		
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select max(num) from notice"); //최대 글번호 얻기
			rs = pstmt.executeQuery();
			if(rs.next()){ //글이 있을때
				number = rs.getInt(1)+1;
			}else{ //글이 없을때
				number = 1;
			}//else
			
			if(num!=0){ //답글
				sql = "update notice set re_step=re_step+1 where ref=? and re_step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step = re_step+1;
				re_level = re_level+1;
			}else{ //원글
				ref = number;
				re_step = 0;
				re_level = 0;
			}//else
			
			sql = "insert into notice(writer,subject,regdate,ref,re_step,re_level,content,ip)"+
					" values(?,?,now(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setInt(3, ref);
			pstmt.setInt(4, re_step);
			pstmt.setInt(5, re_level);
			pstmt.setString(6, dto.getContent());
			pstmt.setString(7, dto.getIp());
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("noticeWrite()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//noticeWrite()
	
	//글갯수
	public int getCount() throws Exception{
		int x = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x = rs.getInt(1); //총글갯수
			}//if
		}catch(Exception e){
			System.out.println("getCount()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//getCount()
	
	//리스트
	public List<NoticeDTO> getList(int start, int cnt) throws Exception{
		List<NoticeDTO> list = null;
		try{
			conn = getConn();
			sql = "select * from notice order by ref desc, re_step asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1); //시작위치
			pstmt.setInt(2, cnt); //갯수
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list = new ArrayList<NoticeDTO>();
				do{
					NoticeDTO dto = new NoticeDTO();
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setRegdate(rs.getDate("regdate"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setContent(rs.getString("content"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setIp(rs.getString("ip"));
					list.add(dto);
				}while(rs.next());
			}//if
		}catch(Exception e){
			System.out.println("getList()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return list;
	}//getList
	
	//글내용보기
	public NoticeDTO getContent(int num) throws Exception{
		NoticeDTO dto = null;
		try{
			conn = getConn();
			
			//조회수 증가
			sql = "update notice set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//글번호가 일치하는 글의 내용보기
			pstmt = conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto = new NoticeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
			}//if
		}catch(Exception e){
			System.out.println("getContent()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return dto;
	}//getContent()
	
	//글수정(jsp)
	public NoticeDTO updateView(int num) throws Exception{
		NoticeDTO dto = null;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				dto = new NoticeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
			}//while
		}catch(Exception e){
			System.out.println("updateView()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return dto;
	}//updateView()
	
	//DB수정
	public int updateDb(NoticeDTO dto) throws Exception{
		String dbName = "";
		int x = -10;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select writer from notice where num=?");
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //내용이 존재할때
				dbName = rs.getString("writer");
				if(dbName.equals(dto.getWriter())){ //작성자가 DB와 일치하면
					sql = "update notice set subject=?,content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getSubject());
					pstmt.setString(2, dto.getContent());
					pstmt.setInt(3, dto.getNum());
					pstmt.executeUpdate();
					x = 1;
				}else{ //일치하지 않으면
					x = 0;
				}//else
			}//if
		}catch(Exception e){
			System.out.println("updateDb()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//updateDb()
	
	public int delete(int num) throws Exception{
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select writer from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pstmt = conn.prepareStatement("delete from notice where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}//if
		}catch(Exception e){
			System.out.println("delete()"+e);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return 1;
	}//delete
	
}//class
