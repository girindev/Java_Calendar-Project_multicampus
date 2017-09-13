package popUp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import calendarServer.CalenderClient;


public class AddSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JButton addScheduleButton;
	private JTextField textField_2;
	private String date;
	private CalenderClient client;
	private IRefreshListener iRefreshListener;
	public void setRefreshListener(IRefreshListener iRefreshListener) {
		this.iRefreshListener = iRefreshListener;
	}
	private String id;	
	
	public AddSchedule(int year, int month, int day, int hour, int minute, String id, CalenderClient client) {
		this.id = id;		
		setBounds(100, 100, 450, 300);
		setTitle("���� ���� �߰��ϱ�");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		
		JLabel lblNewLabel = new JLabel("���õ� ��¥");
		lblNewLabel.setFont(new Font("����ü", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 10));
		
		JLabel lblNewLabel_2 = new JLabel(year+"�� "+month+"�� "+day+"�� ");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 13));
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("���� / ���");
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 14));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setColumns(30);
		textField_1.setText("���� �� ��Ҹ� �Է��ϼ���");
		
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setHgap(15);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_4);
		
		JLabel label = new JLabel("�ð�����");
		label.setFont(new Font("����ü", Font.BOLD, 14));
		panel_4.add(label);
		
		textField_2 = new JTextField(hour + ":" + minute);
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setColumns(4);
		panel_4.add(textField_2);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(15);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_6);

		addScheduleButton = new JButton("���� �����");
		addScheduleButton.addActionListener(new addScheduleListener());
		panel_6.add(addScheduleButton);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		date = year + "-" + month + "-" + day + " " ;//+ textField_2.getText() + ":" + "00";
		
		
		//Ķ���� Ŭ���̾�Ʈ
		this.client = client;
	}
	class addScheduleListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (addScheduleButton == e.getSource()) {
				/*
				 * ��������� DB ó�� 
				 * 
				 * */
				
				EnterScheduleDAO firstadded = new EnterScheduleDAO();
				System.out.println(textField_2.getText());
				int result = firstadded.insert(textField_1.getText(),id, date+textField_2.getText()+ ":" + "00");
				
				///////�μ�Ʈ ���ڸ� /////////
				dispose();
				
				/*
				 * memocalendar�� addschedule ���� �ƴٰ� �˷��ֱ�
				 * 
				 * */
				if (result == 1 && iRefreshListener != null) {
					boolean flag = true;
					iRefreshListener.refresh(flag);
					client.sendRefreshSignal("1");
				}
			}
		}
	}
}

