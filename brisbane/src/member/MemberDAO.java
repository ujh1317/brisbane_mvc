package member;
//DAO
import java.sql.*;
import java.util.*;
import javax.sql.*;//datasource
import javax.naming.*;//lookup
public class MemberDAO {
	//싱글톤 객체 사용,객체를 하나만 사용하겠다.(메모리 절약)
	private static MemberDAO instance=new MemberDAO();
	
	//메서드,JSP에서 사용할 메서드
	public static MemberDAO getInstance(){
		return instance;
	}//getInstance
	
	//커넥션 풀
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()
	
	//아이디 중복체크
	public int confirmId(String id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int x=-1;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select id from member where id=?");
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=1;
			}else{
				x=-1;
			}//if
			
			
		}catch(Exception ex1){
			System.out.println("confirmId() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}//confirmId
	
	//-------------회원가입-------------------
	public void insertMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getJumin1());
			pstmt.setString(5, dto.getJumin2());

			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getZipcode());
			pstmt.setString(8, dto.getAddr());
			pstmt.setTimestamp(9, dto.getRegdate());
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertMember() 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}//insertMember
	
	//=============로그인(인증)
	
	public int userCheck(String id,String pw) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String dbPw="";
		int x=-1;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dbPw=rs.getString("pw");
				if(pw.equals(dbPw)){
					x=1;//로그인 성공
				}else{
					x=0;//암호 틀림
				}//else
			}else{
				x=-1;//없는 아이디
			}//else
			
			
		}catch(Exception ex1){
			System.out.println("userCheck 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}//userCheck
	
	//-------------------------
	//회원정보수정 - 화면에 회원정보 전달
	//-------------------------
	
	public MemberDTO getMember(String id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getTimestamp("regdate"));//***
		
			}//if
		}catch(Exception ex1){
			System.out.println("getMember 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		return dto;
	}//getMember
	
	//-------------------------
	// DB 회원정보수정 
	//-------------------------
	public void updateMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getCon();
			String sql="update member set pw=?, name=?, email=?, zipcode=?, addr=? where id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getId());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("updateMember 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	}//updateMember
	//--------------------
	//회원탈퇴
	//--------------------
	public int deleteMember(String id, String pw) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-1;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dbPw=rs.getString("pw");
				if(pw.equals(dbPw)){
					pstmt2=con.prepareStatement("delete from member where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
					x=1;
				}else{
					x=-1;
				}//else
			}//if
			
		}catch(Exception ex1){
			System.out.println("deleteMember() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;//******
		
	}//deleteMember
	
	
	
}//class


















