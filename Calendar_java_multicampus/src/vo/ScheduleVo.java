package vo;

import java.util.Date;

public class ScheduleVo {
	private String content;//sch_title ����
	private Date date; //sch_date ��¥
	private String name; //sch_writer_id �ۼ���
	private String color;
	private int schPK;
	
	//�⺻������
	public ScheduleVo() {
		// TODO Auto-generated constructor stub
	}
	//�ʱ�ȭ ���ִ� ������
	public ScheduleVo(String content, Date date, String name,String color, int schPK) {
		this.content = content;
		this.date = date;
		this.name =name;
		this.color = color;
		this.schPK = schPK;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSchPK() {
		return schPK;
	}
	public void setSchPK(int schPK) {
		this.schPK = schPK;
	}
	
}
