package member;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthScrollBarUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class IdFind extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JTextField phonTextField;
	private StringBuilder bulider = new StringBuilder();
	private SignDao dao = new SignDao();
	private String q="-";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdFind frame = new IdFind();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	SignVo sVo=new SignVo();
	public IdFind() {
		setTitle("아이디 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		// 1. 이름입력
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);
		
		JLabel nameLabel = new JLabel("이름:           ");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		
		// 2.생년월일 입력
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);
		
		JLabel dayOfBirthLabel = new JLabel("생년월일:  ");
		panel_1.add(dayOfBirthLabel);
		
		yearTextField = new JTextField();
		yearTextField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(yearTextField);
		yearTextField.setText("");
		yearTextField.setColumns(4);
		
		JLabel yearLabel_1 = new JLabel("년");
		panel_1.add(yearLabel_1);
		
		monthTextField = new JTextField();
		panel_1.add(monthTextField);
		monthTextField.setColumns(4);
		
		JLabel monthLabel_2 = new JLabel("월");
		panel_1.add(monthLabel_2);
		
		dayTextField = new JTextField();
		panel_1.add(dayTextField);
		dayTextField.setColumns(4);
		
		JLabel dayLabel_3 = new JLabel("일");
		panel_1.add(dayLabel_3);
		
		
		// 3. 핸드폰번호 입력
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(15);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);
		
		JLabel phonLabel_4 = new JLabel("핸드폰:       ");
		panel_2.add(phonLabel_4);
		
		phonTextField = new JTextField();
		phonTextField.setText("");
		panel_2.add(phonTextField);
		phonTextField.setColumns(15);
		
		
		// 4. 아이디출력부분
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		
		
		contentPane.add(panel_3);
		
		JLabel idFindLabel = new JLabel();
		panel_3.add(idFindLabel);
		
		
		
		// 5. 찾기취소 버튼
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setVgap(15);
		contentPane.add(panel_4);
		
		JButton findButton = new JButton("찾기");
		panel_4.add(findButton);
		findButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					String name = nameTextField.getText();
					String year = yearTextField.getText();
					String month = monthTextField.getText();
					String day = dayTextField.getText();
					String birth= year+q+month+q+day+" "+"00:00:00";
					String phone = phonTextField.getText();
					
					sVo=dao.selectFindId(name, birth, phone);
					if(sVo == null) {
						idFindLabel.setText("ID 없음");
					}else {
						idFindLabel.setText(sVo.getId());
					}
				}
			});
			
		
		
		
		JButton cansleButton = new JButton("취소");
		panel_4.add(cansleButton);
		cansleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IdFind.this.setVisible(false);
				new Login();
			}
		});
		
		
		setVisible(true);
	}

}
