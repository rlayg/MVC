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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	
	public int confirm(String id) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from member2 where id = ?";
		System.out.println("sql => " + sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
	
			} else {
				result = 0;
			}
			System.out.println("rs -> " + rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		//oracle의 sysdate가 들어가, pc시간이 아니라
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
			
			 if(result > 0){ 
				 System.out.println("입력 성공"); 
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
	
	public int insert3(MemberDto memberDto) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String serverSaveFilename = "";
		String upLoadFilename = "";
//		request.setCharacterEncoding("utf-8");
		int maxSize = 5 * 1024 * 1024; //5메가
		String fileSave = "/fileSave";
		String realPath = getServletContext().getRealPath(fileSave);
		System.out.println("realPath -> " + realPath);
	
		
		String sql = "INSERT INTO member2 (id, passwd, name, address, tel, reg_date, img_path)"
                + " VALUES (?, ?, ?, ?, ?, SYSDATE)";
		//oracle의 sysdate가 들어가, pc시간이 아니라
//		ResultSet rs = null;
		
		//UpLoad
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		try {
			// 1. DBCP 이용 /ch10 11 참고
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());		//DB서버에 저장된 값 가져옴 DTO 통해서
			pstmt.setString(2, memberDto.getPasswd());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getAddress());
			pstmt.setString(5, memberDto.getTel());
//			pstmt.setSti(, memberDto.getTel());
			
			//  Member2 진짜 Insert
			result = pstmt.executeUpdate();
			
			 if(result > 0){ 
				 System.out.println("입력 성공"); 
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
	
	
	
	private Object getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 내가 한 답, 이것도 맞아 나는 while, 밑에는 do-while */
	/*
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
				MemberDto md = new MemberDto(); //이거를 바깥에 선언하면 똑같은 값이 나오게 돼 주소값 참조해서 다 똑같은게 들어간대
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
	*/
	/* 선생님 답 */
	public List<MemberDto> list() throws SQLException {
		List<MemberDto> list = null;
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
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					MemberDto md = new MemberDto(); //이거를 바깥에 선언하면 똑같은 값이 나오게 돼
					System.out.println("md => " + md);
					md.setId(rs.getString("id"));
	//	            md.setPasswd(rs.getString("passwd"));
		            md.setName(rs.getString("name"));
		            md.setAddress(rs.getString("address"));
		            md.setTel(rs.getString("tel"));
		            md.setReg_date(rs.getDate("reg_date"));
		            list.add(md);			
				
			} while(rs.next());
			System.out.println("rs => " +rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		
		return list;
	}
	
	/*	태산씨 답 */
	/*
	public int select(String id) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from member2 where id =?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// id OK
			if (rs.next()) {
				return result = 1;
			} else {
				return result = 0;
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
	*/
	
	
	/* 선생님 답 select - update */
	
	public MemberDto select(String id) throws SQLException {
		MemberDto memberDto = new MemberDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from member2 where id =?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberDto.setId(rs.getString("id")); //(rs.getString(1)) 이런식도 가능
				memberDto.setPasswd(rs.getString("passwd"));
				memberDto.setName(rs.getString("name"));
				memberDto.setAddress(rs.getString("address"));
				memberDto.setTel(rs.getString("tel"));
				memberDto.setReg_date(rs.getDate("reg_date"));
				memberDto.setImg_path(rs.getString("img_path"));
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		
		return memberDto;
	}
	
	public int update(MemberDto memberDto) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member2 set passwd = ?, name = ?, address = ?, tel = ? where id = ?";
		System.out.println("sql => " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getPasswd());
			pstmt.setString(2, memberDto.getName());
			pstmt.setString(3, memberDto.getAddress());
			pstmt.setString(4, memberDto.getTel());
			pstmt.setString(5, memberDto.getId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return result;
	}

	
	/* 태산씨 답 이 코드가 돌아가려면 위 태산씨의 select가 있어야함 */
	/*
	public int delete(String id, String passwd) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM member2 WHERE id = ? AND passwd = ? ";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			result = pstmt.executeUpdate(); //int result는 수정된 개수
			
			if(result == 1) {
				return result;
			} else if (result == 0) {
				if (select(id) == 1) {
					result = 0;
					return result;
				} else if (select(id) == 0) {
					result = -1;
					return result;
				}
			}
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
		}
		
		return result;
	}
	*/
	
	/* 선생님 답 */
	
	public int delete(String id, String passwd) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		result = check(id, passwd);
		if(result != 1) {
			return result;
		}
		
		String sql = "DELETE FROM member2 WHERE id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate(); //int result는 수정된 개수
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
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
