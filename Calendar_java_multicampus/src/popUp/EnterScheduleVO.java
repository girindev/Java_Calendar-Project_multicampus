package popUp;

import java.awt.print.Book;

public class EnterScheduleVO {
	private String Sch_Date;
	private String Sch_Title;
	private String Sch_Write_Id;
/////////////////////////////////////////////
	public EnterScheduleVO() {
	}
	
	
	public EnterScheduleVO(String Sch_Date,String Sch_Title,String Sch_Write_Id) {
		this.Sch_Date = Sch_Date;
		this.Sch_Title = Sch_Title;
		this.Sch_Write_Id = Sch_Write_Id;
	}
/////////////////////////////////////////////	
	public String getSch_Date() {
		return Sch_Date;
	}


	public void setSch_Date(String sch_Date) {
		Sch_Date = sch_Date;
	}


	public String getSch_Title() {
		return Sch_Title;
	}


	public void setSch_Title(String sch_Title) {
		Sch_Title = sch_Title;
	}


	public String getSch_Write_Id() {
		return Sch_Write_Id;
	}


	public void setSch_Write_Id(String sch_Write_Id) {
		Sch_Write_Id = sch_Write_Id;
	}

	
}
