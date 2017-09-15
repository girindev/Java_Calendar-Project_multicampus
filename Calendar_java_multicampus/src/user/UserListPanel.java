package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import connect.DBConnect;
import member.Info;
import popUp.IRefreshListener;


class UserCheckList{
	
	
}
public class UserListPanel extends JPanel {
	private UserVO[] userArr;
	private Set<UserVO> nowUserCheckList = new HashSet<>();
	private IRefreshListener iRefreshListener;
	
	public UserListPanel(IRefreshListener iRefreshListener) {
		DBConnect dbCon = new DBConnect();
		ArrayList<UserVO> userListArr = dbCon.getUserList();
		userArr = new UserVO[userListArr.size()];
		this.iRefreshListener = iRefreshListener;
		
		UserVO me = new UserVO();
		me.setUserID(Info.id);
		me.setName(Info.name);
		me.setSelected(Info.selected);
		me.setConnect(Info.connect);
		userArr[0] = me;

		nowUserCheckList.add(me);
		int j = 1;
		for (int i = 0; i < userListArr.size(); i++) {
			System.out.println(userListArr.size());
			if (!userListArr.get(i).getUserID().equals(Info.id)) {
				userArr[j++] = userListArr.get(i);
			}
		}

		// userArr = userListArr.toArray(new UserVO[userListArr.size()]);
		setUserListPanel(userArr);
	}
	
	public void listRefresh() {
		DBConnect dbCon = new DBConnect();
		ArrayList<UserVO> getListArr = dbCon.getUserList();
		
		for (int i = 0; i < userArr.length; i++) {
			for (int j = 0; j < getListArr.size(); j++) {
				if(userArr[i].getUserID().equals(getListArr.get(j).getUserID())) {
					userArr[i].setConnect(getListArr.get(j).isConnect());
				}
			}
		}
		repaint();
	}
	
	public void updateConnectList(String id, boolean con) {
		for (int i = 0; i < userArr.length; i++) {
			if(userArr[i].getUserID().equals(id)) {
				userArr[i].setConnect(con);
				return;
			}
		}
		repaint();
	}

	public void setUserListPanel(UserVO[] users) {
		this.userArr = users;
		setBackground(Color.WHITE);
		JList list = new JList(userArr);

		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				UserVO item = (UserVO) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected());
				Rectangle rect = list.getCellBounds(index, index);
				list.repaint(rect);
				
				if (nowUserCheckList.add(item) == false) {
					nowUserCheckList.remove(item);
				}
				
				iRefreshListener.refresh(true);
			}
		});
		JScrollPane sp = new JScrollPane(list);
		setLayout(new BorderLayout());

		/*JButton okBtn = new JButton("Ȯ��");
		okBtn.addActionListener(new ActionListener() {
			private ArrayList checkUserNo;

			@Override
			public void actionPerformed(ActionEvent e) {
				checkUserNo = new ArrayList<>();

				ListModel model = list.getModel();
				for (int i = 0; i < model.getSize(); i++) {
					UserVO item = (UserVO) model.getElementAt(i);
					if (item.isSelected()) {
						// üũ�� ���̵� ��������
						 checkUserNo.add(item.getUserNo());
					}
				}
			}
		});*/
		
		JButton clearBtn = new JButton("�ʱ�ȭ");
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// üũ ����
				ListModel model = list.getModel();
				((UserVO) model.getElementAt(0)).setSelected(true);
				
				for (int i = 1; i < model.getSize(); i++) {
					UserVO item = (UserVO) model.getElementAt(i);
					item.setSelected(false);
					nowUserCheckList.remove(item);
				}
				
				iRefreshListener.refresh(true);
				repaint();
			}
		});

		JPanel btnPanel = new JPanel();
//		btnPanel.add(okBtn);
		btnPanel.add(clearBtn);

		add(btnPanel, BorderLayout.SOUTH);
		add(sp, BorderLayout.CENTER);
	}

	class CheckListRenderer extends JCheckBox implements ListCellRenderer {

		public CheckListRenderer() {
			setBackground(UIManager.getColor("List.textBackground"));
			setForeground(UIManager.getColor("List.textForeground"));
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			setEnabled(list.isEnabled());
			setSelected(((UserVO) value).isSelected());

			setFont(list.getFont());
			String conStr = "��������";
			if (((UserVO) value).isConnect())
				conStr = "�¶���";
			setText(value.toString() + "  " + conStr);
			return this;
		}
	}
	public String getUserList() {
		StringBuffer userId = new StringBuffer(" AND (sch_write_id = ");
		if (nowUserCheckList.size() == 0) {
			userId.append("null");
		} else {
			int i=1;
			for (UserVO v : nowUserCheckList) {
				userId.append("'"+ v.getName()+"'");
				if (i < nowUserCheckList.size()) {
					userId.append(" OR sch_write_id =");
					i++;
				}
			}
		}
		userId.append(")");
		return userId.toString();
	}
}
