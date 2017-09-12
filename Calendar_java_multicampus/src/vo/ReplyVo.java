package vo;

import java.util.Date;

public class ReplyVo {
	private String comment;//com_text 내용
	private Date date; //com_date 날짜
	private String name; //com_writer_id 작성자
	private int com_num; //com_sch_num 번호
	
	//기본생성자
	public ReplyVo() {
		// TODO Auto-generated constructor stub
	}
	//초기화 해주는 생성자
	public ReplyVo(String comment, Date date, String name, int com_num) {
		this.comment = comment;
		this.date = date;
		this.name =name;
		this.com_num = com_num;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
}
	
	
