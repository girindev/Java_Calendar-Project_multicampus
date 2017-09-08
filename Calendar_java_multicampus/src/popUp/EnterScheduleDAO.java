package popUp;

import java.sql.*;
// calendar(이름 임의지정) 테이블에 SQL을 전달하는 DB작업들을 담당하는 클래스
public class EnterScheduleDAO {
	private static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://127.0.0.1:3306/java";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";
	
	public EnterScheduleDAO(){
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류/jar파일 또는 스트링 확인 요망");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(EnterScheduleVO sch) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}



