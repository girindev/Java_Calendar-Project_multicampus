package member;

import java.sql.*;

public class SignDao {
	private static final String DB_DRIVER=
			"com.mysql.jdbc.Driver";
	private static final String DB_URL=
			"jdbc:mysql://127.0.0.1:3306/java";
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
	
	public SignVo selectById(String id) {
		SignVo result = null;

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT numbering, id, pw, name, year,month,day,phone FROM SIGN where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new SignVo();
				
				result.setNumbering(rs.getString(1));
				result.setId(rs.getString(2));
				result.setPw(rs.getString(3));
				result.setName(rs.getString(4));
				result.setYear(rs.getString(5));
				result.setMonth(rs.getString(6));
				result.setDay(rs.getString(7));
				result.setPhon(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 입력받은 아이디가 존재하는 경우에는 객체가 리턴되고
		// 존재하지 않는 경우에는 null값이 리턴됨(아이디 사용가능)
		return result;
	}
	
	public int insertSignDao(SignVo sign)	{
		int result=0;
		
		try {
			con=DriverManager.getConnection(DB_URL,DB_ID,DB_PW);
			String sql= "INSERT INTO SIGN(ID, PW, NAME, YEAR, MONTH, DAY, PHONE)"+
					"VALUES(?,?,?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sign.getId());
			pstmt.setString(2, sign.getPw());
			pstmt.setString(3, sign.getName());
			pstmt.setString(4, sign.getYear());
			pstmt.setString(5, sign.getMonth());
			pstmt.setString(6, sign.getDay());
			pstmt.setString(7, sign.getPhon());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
