package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBconnectionString;

public class ReplyDeleteDao {
	public ReplyDeleteDao(){
		try {
			Class.forName(DBconnectionString.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류/jar파일 또는 스트링 확인 요망");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int delete(int com_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
			String sql = "delete from comment where com_num=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pstmt!=null)
				try {pstmt.close();} catch (SQLException e) {}
			if(con!=null)
				try {con.close();} catch (SQLException e) {}
		}
		return result;
	}
}





