package popUp;

import java.sql.*;
// calendar(�̸� ��������) ���̺� SQL�� �����ϴ� DB�۾����� ����ϴ� Ŭ����
public class EnterCommentDAO {
	private static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://127.0.0.1:3306/project";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";
	
	public EnterCommentDAO(){
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����/jar���� �Ǵ� ��Ʈ�� Ȯ�� ���");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(String com,int com_num,String id,String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
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



