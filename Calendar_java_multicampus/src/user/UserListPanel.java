package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import connect.DBConnect;


public class UserListPanel extends JPanel{
	private UserVO[] userArr;
	private ArrayList<UserVO> userListArr;
	
	public UserListPanel() {
		DBConnect dbCon = new DBConnect();
		userListArr = dbCon.getUserList();
		userArr = userListArr.toArray(new UserVO[userListArr.size()]);
		
		setUserListPanel(userArr);
	}
	
	public UserListPanel(UserVO[] users) {
		setUserListPanel(users);
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
			}
		});
		JScrollPane sp = new JScrollPane(list);
		setLayout(new BorderLayout());
		
		JButton okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			private ArrayList checkUserNo;

			@Override
			public void actionPerformed(ActionEvent e) {
				checkUserNo = new ArrayList<>();
				
				ListModel model = list.getModel();
				for (int i = 0; i < model.getSize(); i++) {
					UserVO item = (UserVO)model.getElementAt(i);
					if(item.isSelected()) {
						//체크된 아이디 가져오기
//						checkUserNo.add(item.getUserNo());
					}
				}
			}
		});
		JButton clearBtn = new JButton("초기화");
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//체크 해제
				ListModel model = list.getModel();
				for (int i = 0; i < model.getSize(); i++) {
					UserVO item = (UserVO)model.getElementAt(i);
						item.setSelected(false);						
				}
				repaint();
			}
		});
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(okBtn);
		btnPanel.add(clearBtn);
		
		add(btnPanel, BorderLayout.SOUTH);
		add(sp, BorderLayout.CENTER);
	} 
	
	
	class CheckListRenderer extends JCheckBox implements ListCellRenderer{

		public CheckListRenderer() {
			setBackground(UIManager.getColor("List.textBackground"));
			setForeground(UIManager.getColor("List.textForeground"));
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			setEnabled(list.isEnabled());
			setSelected(((UserVO)value).isSelected());
			
			setFont(list.getFont());
			String conStr="오프라인";
			if(((UserVO)value).isConnect()) conStr = "온라인";
			setText(value.toString()+"  "+conStr);
			return this;
		}
	}
}
