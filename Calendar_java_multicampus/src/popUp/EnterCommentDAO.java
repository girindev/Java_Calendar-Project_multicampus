package popUp;

import java.sql.*;

import util.DBconnectionString;
// calendar(이름 임의지정) 테이블에 SQL을 전달하는 DB작업들을 담당하는 클래스
public class EnterCommentDAO {
	public EnterCommentDAO(){
		try {
			Class.forName(DBconnectionString.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류/jar파일 또는 스트링 확인 요망");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(String com,int com_num,String id,String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
			String sql = "INSERT INTO COMMENT(Com_Text,COM_SCH_NUM,Com_Write_Id,Com_Date)"
					+ " VALUES(?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, com);
			pstmt.setInt(2, com_num);
			pstmt.setString(3, id);
			pstmt.setString(4, date);
			
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



