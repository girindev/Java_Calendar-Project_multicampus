package user;

public class UserVO {
	private String UserID;//���̵�
	private String name;//�̸�
	private boolean isSelected;//üũ����
	private boolean isConnect;//���ӿ���
	
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
