package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBconnectionString;

public class ConnectionInit {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public ConnectionInit() {
		try {
			Class.forName(DBconnectionString.DB_DRIVER);
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
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
