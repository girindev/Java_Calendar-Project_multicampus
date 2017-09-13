package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.ReplyVo;
	
public class ReplyDeleteDao {
	private ConnectionInit dao;
	private ConnectionInit pstmt;
	private ResultSet rs = null;
	
	public ReplyDeleteDao() {
		dao = new ConnectionInit();
	}
	
	public ArrayList<ReplyVo> selectCommentList(int com_num) {
		new ArrayList<>();
		ArrayList<ReplyVo> replyList = new ArrayList<>();
		String sql = "delete from comment where com_num="+com_num;
		rs = dao.selectQuery(sql);
	
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, book.getTitle());
//			// 5. insert, update, delete : executeUpdate메소드 (리턴int)
//			result = pstmt.executeUpdate();
//			
//			while (rs.next()) {
//				String com_comment = rs.getString("com_text");
//				Date com_date = rs.getTimestamp("com_date");
//				String com_write_id = rs.getString("com_write_id");
//				int com_num = rs.getInt("com_sch_num");
//				replyList.add(new ReplyVo(com_comment, com_date, com_write_id,com_num));
//				replyList.remove(index);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		
		return replyList;
	}
}
