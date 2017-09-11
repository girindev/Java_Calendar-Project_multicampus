package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.UserVO;

public class DBConnect {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/project";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";

//	private static Connection con = null;
	public Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

//	public static Connection getSharedInstance() {
//		try {
//			if (con == null) {
//				con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return con;
//	}

	// 회원 리스트 가져오기
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserVO> userArr = new ArrayList<>();
		UserVO user;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "select * from MEMBER order by connect desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new UserVO();
				user.setUserID(rs.getString(1));
				user.setName(rs.getString(3));
				user.setConnect(rs.getBoolean(6));
				userArr.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeRs();
			closePstmt();
			closeConnection();
		}
		return userArr;
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
