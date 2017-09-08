package user;

public class UserVO {
	private String UserID;//아이디
	private String name;//이름
	private String str;//
	private boolean isSelected;//체크여부
	private boolean isConnect;//접속여부
	
	public UserVO() {
		
	}
	
	public UserVO(String str) {
		this.str = str;
		isSelected = false;
		isConnect = false;
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

	@Override
	public String toString() {
		return "UserVO [UserID=" + UserID + ", name=" + name + ", str=" + str + ", isSelected=" + isSelected
				+ ", isConnect=" + isConnect + "]";
	}
	
//	public String toString() {
//		return str;
//	}
	
	
	
}
