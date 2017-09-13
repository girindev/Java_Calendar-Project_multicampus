package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionInit {
	private static final String DB_DRIVER =
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://70.12.115.65:3306/project";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public ConnectionInit() {
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet selectQuery(String query) {
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
	
	public void closeConnection() {
		try{if (con != null) con.close(); } catch(SQLException e) {e.printStackTrace();}
		try{if (pstmt != null) pstmt.close(); } catch(SQLException e) {e.printStackTrace();}
	}
}
