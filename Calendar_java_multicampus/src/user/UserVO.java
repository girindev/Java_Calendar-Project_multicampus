package user;

public class UserVO {
	private String UserID;//���̵�
	private String name;//�̸�
	private String str;//
	private boolean isSelected;//üũ����
	private boolean isConnect;//���ӿ���
	
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
