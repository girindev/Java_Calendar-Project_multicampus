package vo;

import java.util.Date;

public class ReplyVo {
	private String comment;//com_text ����
	private Date date; //com_date ��¥
	private String name; //com_writer_id �ۼ���
	private int com_num; //com_sch_num ��ȣ
	
	//�⺻������
	public ReplyVo() {
		// TODO Auto-generated constructor stub
	}
	//�ʱ�ȭ ���ִ� ������
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
	
	
