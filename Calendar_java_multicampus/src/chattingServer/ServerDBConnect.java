package chattingServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.UserVO;

public class ServerDBConnect {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://70.12.115.65:3306/project";
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

	public void connection() {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDBMsg(String msg) {
		try {
			String sql = "INSERT INTO CHAT(MSG,WRITE_TIME)\r\n" + 
					"  VALUES('"+msg+"',now());";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
