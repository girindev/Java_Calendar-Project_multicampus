package user;

public class UserVO {
	private String UserID;//아이디
	private String name;//이름
	private boolean isSelected;//체크여부
	private boolean isConnect;//접속여부
	
	public UserVO() {
		
	}
	
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserID() {
		return UserID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setConnect(boolean b) {
		isConnect = b;
	}
	
	public boolean isConnect() {
		return isConnect;
	}
	
	public String toString() {
		return getName();
	}
}
