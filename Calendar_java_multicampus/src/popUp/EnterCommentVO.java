package popUp;

import java.awt.print.Book;

public class EnterCommentVO {
	private String Com_Date;
	private String Com_Text;
	private String Com_Write_Id;
	private int COM_SCH_NUM;
	
/////////////////////////////////////////////
	public EnterCommentVO() {
	}
	
	
	public EnterCommentVO(String Com_Date,String Com_Text,int COM_SCH_NUM,String Com_Write_Id) {
		this.Com_Date = Com_Date;
		this.Com_Text = Com_Text;
		this.COM_SCH_NUM = COM_SCH_NUM;
		this.Com_Write_Id = Com_Write_Id;
	}
/////////////////////////////////////////////	


	public String getCom_Date() {
		return Com_Date;
	}


	public void setCom_Date(String com_Date) {
		Com_Date = com_Date;
	}


	public String getCom_Text() {
		return Com_Text;
	}


	public void setCom_Text(String com_Text) {
		Com_Text = com_Text;
	}


	public String getCom_Write_Id() {
		return Com_Write_Id;
	}


	public void setCom_Write_Id(String com_Write_Id) {
		Com_Write_Id = com_Write_Id;
	}


	public int getCOM_SCH_NUM() {
		return COM_SCH_NUM;
	}


	public void setCOM_SCH_NUM(int cOM_SCH_NUM) {
		COM_SCH_NUM = cOM_SCH_NUM;
	}
	

	
}
