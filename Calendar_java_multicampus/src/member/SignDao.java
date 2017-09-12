package member;

import java.sql.*;

public class SignDao {
	private static final String DB_DRIVER=
			"com.mysql.jdbc.Driver";
	private static final String DB_URL=
			"jdbc:mysql://127.0.0.1:3306/project";
	private static final String DB_ID="root";
	private static final String DB_PW="sds1501";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public SignDao() {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}
		
		
	}
	
	public SignVo selectLogin(String id, String pw) {
		SignVo result = null;

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT id, pw, name FROM member where id=? and pw=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new SignVo();
				
				
				result.setId(rs.getString(1));
				result.setPw(rs.getString(2));
				result.setName(rs.getString(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}

		// 입력받은 아이디가 존재하는 경우에는 객체가 리턴되고
		// 존재하지 않는 경우에는 null값이 리턴됨(아이디 사용가능)
		return result;
	}
	
	public SignVo selectById(String id) {
		SignVo result = null;

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT id, pw, name, birth,phone FROM member where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new SignVo();
				
				result.setId(rs.getString(1));
				result.setPw(rs.getString(2));
				result.setName(rs.getString(3));
				result.setBirth(rs.getString(4));
				result.setPhone(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}

		// 입력받은 아이디가 존재하는 경우에는 객체가 리턴되고
		// 존재하지 않는 경우에는 null값이 리턴됨(아이디 사용가능)
		return result;
	}
	
	public SignVo selectFindId(String name, String birth, String phone) {
		SignVo result = null;

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT id,name,birth,phone FROM member where  name=? and birth=? and phone=?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3,phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new SignVo();
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setBirth(rs.getString(3));
				result.setPhone(rs.getString(4));
				
				System.out.println("select birth:"+result.getBirth());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}

		// 입력받은 아이디가 존재하는 경우에는 객체가 리턴되고
		// 존재하지 않는 경우에는 null값이 리턴됨(아이디 사용가능)
		return result;
	}
	
	public SignVo selectFindPw(SignVo inputVo) {
		SignVo result = null;

		try {
			System.out.println("dao vo:"+inputVo);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT pw,id,name,birth,phone FROM member where  "
					+ "id=? and name=? and birth=? and phone=?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, inputVo.getId());
			pstmt.setString(2, inputVo.getName());
			pstmt.setString(3, inputVo.getBirth());
			pstmt.setString(4, inputVo.getPhone());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new SignVo();
				result.setPw(rs.getString(1));
				result.setId(rs.getString(2));
				result.setName(rs.getString(3));
				result.setBirth(rs.getString(4));
				result.setPhone(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}

		// 입력받은 아이디가 존재하는 경우에는 객체가 리턴되고
		// 존재하지 않는 경우에는 null값이 리턴됨(아이디 사용가능)
		return result;
	}
	
	public int insertSignDao(SignVo sign)	{
		int result=0;
		
		try {
			con=DriverManager.getConnection(DB_URL,DB_ID,DB_PW);
			String sql= "INSERT INTO member(ID, PW, NAME, birth, PHONE)"+
					"VALUES(?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sign.getId());
			pstmt.setString(2, sign.getPw());
			pstmt.setString(3, sign.getName());
			pstmt.setString(4, sign.getBirth());
			pstmt.setString(5, sign.getPhone());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
			closePstmt();
			closeRs();
		}
		
		return result;
	}
	
	public void closeConnection() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closePstmt() {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeRs() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
