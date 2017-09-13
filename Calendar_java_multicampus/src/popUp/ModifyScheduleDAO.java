package popUp;

import java.sql.*;
// calendar(�̸� ��������) ���̺� SQL�� �����ϴ� DB�۾����� ����ϴ� Ŭ����

public class ModifyScheduleDAO {
	private static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://127.0.0.1:3306/project";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";
	
	public ModifyScheduleDAO(){
		try {
			Class.forName(DRIVER_NAME);
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
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
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



