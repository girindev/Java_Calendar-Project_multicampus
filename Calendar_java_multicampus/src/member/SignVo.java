package member;

public class SignVo {
	private String numbering;
	private String id;
	private String pw;
	private String name;
	private String year;
	private String month;
	private String day;
	private String phon;
	
	public SignVo() {
		
	}
	public SignVo(String numbering, String id, String pw, String name, 
			String year, String month, String day, String phon) {
		this.day=day;
		this.id=id;
		this.month=month;
		this.name=name;
		this.numbering=numbering;
		this.phon=phon;
		this.pw=pw;
		this.year=year;
	}
	
	public SignVo(String id, String name, 
			String year, String month, String day, String phon) {
		this.day=day;
		this.id=id;
		this.month=month;
		this.name=name;
		this.phon=phon;
		this.year=year;
	}
	
	public String getNumbering() {
		return numbering;
	}
	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getPhon() {
		return phon;
	}
	public void setPhon(String phon) {
		this.phon = phon;
	}
	@Override
	public String toString() {
		return "SignVo [numbering=" + numbering + ", id=" + id + ", pw=" + pw + ", name=" + name + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", phon=" + phon + "]";
	}
	
	
	
}
