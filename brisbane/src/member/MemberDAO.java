package member;
//DAO
import java.sql.*;
import java.util.*;
import javax.sql.*;//datasource
import javax.naming.*;//lookup
public class MemberDAO {
	//�̱��� ��ü ���,��ü�� �ϳ��� ����ϰڴ�.(�޸� ����)
	private static MemberDAO instance=new MemberDAO();
	
	//�޼���,JSP���� ����� �޼���
	public static MemberDAO getInstance(){
		return instance;
	}//getInstance
	
	//Ŀ�ؼ� Ǯ
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()
	
	//���̵� �ߺ�üũ
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
			System.out.println("confirmId() ����:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}//confirmId
	
	//-------------ȸ������-------------------
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
			System.out.println("insertMember() ����:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}//insertMember
	
	//=============�α���(����)
	
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
					x=1;//�α��� ����
				}else{
					x=0;//��ȣ Ʋ��
				}//else
			}else{
				x=-1;//���� ���̵�
			}//else
			
			
		}catch(Exception ex1){
			System.out.println("userCheck ����:"+ex1);
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
	//ȸ���������� - ȭ�鿡 ȸ������ ����
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
			System.out.println("getMember ����:"+ex1);
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
	// DB ȸ���������� 
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
			System.out.println("updateMember ����:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	}//updateMember
	//--------------------
	//ȸ��Ż��
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
			System.out.println("deleteMember() ����:"+ex1);
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


















