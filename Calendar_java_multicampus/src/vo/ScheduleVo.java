package vo;

import java.util.Date;

public class ScheduleVo {
	private String content;//sch_title 내용
	private Date date; //sch_date 날짜
	private String name; //sch_writer_id 작성자
	
	//기본생성자
	public ScheduleVo() {
		// TODO Auto-generated constructor stub
	}
	//초기화 해주는 생성자
	public ScheduleVo(String content, Date date, String name) {
		this.content = content;
		this.date = date;
		this.name =name;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
