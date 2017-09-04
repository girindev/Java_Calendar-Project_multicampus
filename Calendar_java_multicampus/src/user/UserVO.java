package user;

public class UserVO {
	private int UserNo;
	private String str;
	private boolean isSelected;
	private boolean isConnect;
	
	public UserVO(String str) {
		this.str = str;
		isSelected = false;
		isConnect = false;
		
	}
	
	public void setUserNo(int num) {
		this.UserNo = num;
	}
	public int getUserNo() {
		return UserNo;
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
		return str;
	}

}
