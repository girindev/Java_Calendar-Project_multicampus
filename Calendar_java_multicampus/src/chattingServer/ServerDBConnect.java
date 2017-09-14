package chattingServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import user.UserVO;
import util.DBconnectionString;

public class ServerDBConnect {
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
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int updateConnect(int online, String id) {
		int result = 0;
		try {
			String connectSql = "update member set connect= ? where id = ?";
			pstmt = con.prepareStatement(connectSql);
			pstmt.setInt(1, online);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	
	
	public UserVO getConnect() {
		UserVO result = null;

		try {			
			String sql = "SELECT id, name, connect FROM member";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new UserVO();
				
				result.setUserID(rs.getString(1));
				result.setName(rs.getString(2));
				result.setConnect(rs.getBoolean(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*chat 메세지 내용 저장*/
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
