package popUp;

import java.sql.*;
// calendar(이름 임의지정) 테이블에 SQL을 전달하는 DB작업들을 담당하는 클래스

import util.DBconnectionString;
public class EnterScheduleDAO {

	public EnterScheduleDAO(){
		try {
			Class.forName(DBconnectionString.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류/jar파일 또는 스트링 확인 요망");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(String sch, String id, String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
			String sql = "INSERT INTO SCHEDULE(SCH_TITLE, SCH_WRITE_ID, SCH_DATE)"
					+ " VALUES(?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sch);
			pstmt.setString(2, id);
			pstmt.setString(3, date);
			
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



