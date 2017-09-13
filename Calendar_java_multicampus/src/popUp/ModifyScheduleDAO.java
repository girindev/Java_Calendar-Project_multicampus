package popUp;

import java.sql.*;
// calendar(�̸� ��������) ���̺� SQL�� �����ϴ� DB�۾����� ����ϴ� Ŭ����

import util.DBconnectionString;

public class ModifyScheduleDAO {
	
	public ModifyScheduleDAO(){
		try {
			Class.forName(DBconnectionString.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����/jar���� �Ǵ� ��Ʈ�� Ȯ�� ���");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int update(String sch, String date, int sch_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DBconnectionString.DB_URL, DBconnectionString.DB_ID, DBconnectionString.DB_PW);
			String sql = "UPDATE SCHEDULE SET SCH_DATE=?, SCH_TITLE=? WHERE SCH_NUM=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, sch);
			pstmt.setInt(3, sch_num);
			
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



