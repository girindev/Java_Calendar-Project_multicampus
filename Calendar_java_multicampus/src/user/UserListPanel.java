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


public class UserListPanel extends JPanel{
	private UserVO[] userArr;
	public UserListPanel() {}
	public void setUserListPanel(UserVO[] users) {
		this.userArr = users;
		setBackground(Color.WHITE);
		JList list = new JList();
		
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
		
		final JTextArea textArea = new JTextArea(3, 10);
		JScrollPane textPanel = new JScrollPane(textArea);
		JButton printButton = new JButton("print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListModel model = list.getModel();
				int n = model.getSize();
				for (int i = 0; i < n; i++) {
					UserVO item = (UserVO) model.getElementAt(i);
					if (item.isSelected()) {
						textArea.append(item.toString());
						textArea.append(System.getProperty("line.separator"));
					}
				}
			}
		});
		JButton clearButton = new JButton("clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(printButton);
		panel.add(clearButton);
		
		setLayout(new BorderLayout());
		add(sp, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		add(textPanel, BorderLayout.SOUTH);
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
