package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.ScheduleVo;

public class ScheduleAllSelectDao {
	private ConnectionInit dao;
	private ResultSet rs = null;
	
	public ScheduleAllSelectDao() {
		dao = new ConnectionInit();
	}
	
	public ArrayList<ScheduleVo> selectScheduleAllList(String date) {
		new ArrayList<>();
		ArrayList<ScheduleVo> scheduleList = new ArrayList<>();
		String sql = "Select sch_title,sch_write_id, sch_date, color "
				+ "from schedule, member "
				+ "where (sch_date like '"+date+"%') "
						+ "AND (sch_write_id = name)";
		rs = dao.selectQuery(sql);
		try {
			while (rs.next()) {
				
				String sch_content = rs.getString("sch_title");
				Date sch_date = rs.getTimestamp("sch_date");
				String sch_write_id = rs.getString("sch_write_id");
				String color = rs.getString("color");
				int schPK = rs.getInt("sch_num");
				scheduleList.add(new ScheduleVo(sch_content, sch_date, sch_write_id,color, schPK));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return scheduleList;
	}
}
