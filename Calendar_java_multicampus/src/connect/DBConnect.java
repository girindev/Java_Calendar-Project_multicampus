package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import chattingServer.ChatVO;
import user.UserVO;
import util.DBconnectionString;

public class DBConnect {

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
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
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
	
	public ArrayList<ChatVO> getChatList(){
		ArrayList<ChatVO> chatVO = new ArrayList<>();
		ChatVO chat;
		try {
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
			String sql = "select msg_num, msg, write_time from chat order by msg_num desc limit 30";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat = new ChatVO();
				chat.setMsg(rs.getString(2));
				chat.setTime(rs.getString(3));
				chatVO.add(chat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeRs();
			closePstmt();
			closeConnection();
		}
		return chatVO;
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
