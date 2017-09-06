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
