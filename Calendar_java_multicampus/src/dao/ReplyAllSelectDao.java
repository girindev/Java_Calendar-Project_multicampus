package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.ReplyVo;

public class ReplyAllSelectDao {
	private ConnectionInit dao;
	private ResultSet rs = null;
	
	public ReplyAllSelectDao() {
		dao = new ConnectionInit();
	}
	
	
	public ArrayList<ReplyVo> selectCommentList(int scheduleNum) {
		new ArrayList<>();
		ArrayList<ReplyVo> replyList = new ArrayList<>();
		String sql = "Select * from comment where com_sch_num="+scheduleNum;
		rs = dao.selectQuery(sql);
		try {
			while (rs.next()) {
				String com_comment = rs.getString("com_text");
				Date com_date = rs.getTimestamp("com_date");
				String com_write_id = rs.getString("com_write_id");
				int com_num = rs.getInt("com_sch_num");
				replyList.add(new ReplyVo(com_comment, com_date, com_write_id,com_num));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return replyList;
	}

}
