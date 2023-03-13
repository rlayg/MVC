package och12;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//singleton + DBCP
public class MemberDao {
	private static MemberDao instance;
	
	private MemberDao() {
	}
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		} 
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int check(String id, String passwd) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select passwd from member2 where id =?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			// id OK
			if(rs.next()) {
				String dbPasswd = rs.getString(1);
				System.out.println("dbPasswd => " + dbPasswd);
				if (dbPasswd.equals(passwd)) {
					result = 1;
				}else {
					result = 0;
				}
			} else {
				result = -1;
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return result;
	}
	
	public int insert(MemberDto memberDto) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
//		String sql = "insert into member2 values (?,?,?,?,?,?)";
		
		String sql = "INSERT INTO member2 (id, passwd, name, address, tel, reg_date)"
                + " VALUES (?, ?, ?, ?, ?, SYSDATE)";
		
//		ResultSet rs = null;
		
		try {
			// 1. DBCP 이용 /ch10 11 참고
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());		//DB서버에 저장된 값 가져옴 DTO 통해서
			pstmt.setString(2, memberDto.getPasswd());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getAddress());
			pstmt.setString(5, memberDto.getTel());
			
			//  Member2 진짜 Insert
			result = pstmt.executeUpdate();
			
			 if(result > 0){ System.out.println("입력 성공"); 
			 } else {
			 System.out.println("입력 실패"); 
			 }
			
			
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
//			if(rs != null) {
//				rs.close();
//			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return result;
	}
	
	public List<MemberDto> list() throws SQLException {
		List<MemberDto> memberDto = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// Create statement and execute SELECT query
		String sql = "SELECT * FROM member2";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// Iterate through result set and create Member objects for each row
			while(rs.next()) {
				MemberDto md = new MemberDto();
				System.out.println("md => " + md);
				md.setId(rs.getString("id"));
//	            md.setPasswd(rs.getString("passwd"));
	            md.setName(rs.getString("name"));
	            md.setAddress(rs.getString("address"));
	            md.setTel(rs.getString("tel"));
	            md.setReg_date(rs.getDate("reg_date"));
	            memberDto.add(md);			
	            
			}
			System.out.println("rs => " +rs);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
		
		return memberDto;
	}
	
	public int delete(String id, String passwd) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM member2 WHERE id = ? ";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
//			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPasswd = rs.getString(1);
				System.out.println("dbPasswd => " + dbPasswd);
				if (dbPasswd.equals(passwd)) {
					result = 1;
				}else {
					result = 0;
				}
			} else {
				result = -1;
			}
			
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return result;
	}
	
	
	
}
