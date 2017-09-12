package member;

public class SignVo {
	private String numbering;
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String phone;
	
	public SignVo() {
		
	}
	public SignVo(String id, String pw, String name, 
			String birth, String phone) {
		this.birth=birth;
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.pw=pw;
		
	}
	
	public SignVo(String id, String name, 
			String birth, String phone) {
		this.birth=birth;
		this.id=id;
		this.name=name;
		this.phone=phone;
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


	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "SignVo [id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", phone=" + phone + "]";
	}
	
	
	
	
}
