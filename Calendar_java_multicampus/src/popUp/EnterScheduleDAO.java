package popUp;

import java.sql.*;
// calendar(�̸� ��������) ���̺� SQL�� �����ϴ� DB�۾����� ����ϴ� Ŭ����
public class EnterScheduleDAO {
	private static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://127.0.0.1:3306/calendar";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";
	
	public EnterScheduleDAO(){
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����/jar���� �Ǵ� ��Ʈ�� Ȯ�� ���");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(String sch) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "INSERT INTO SCHEDULE(SCH_TITLE)"
					+ " VALUES(?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sch);
			
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



