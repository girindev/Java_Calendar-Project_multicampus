package member;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Pwfind extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField phonTextField;
	private JTextField dayTextField;
	private SignDao dao=new SignDao();
	private SignVo sVo=new SignVo();
	private String q="-";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pwfind frame= new Pwfind();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pwfind() {
		setTitle("비밀번호찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		// 1. ID입력
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(13);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);
		
		JLabel idLabel = new JLabel("ID:                  ");
		panel.add(idLabel);
		
		idTextField = new JTextField();
		panel.add(idTextField);
		idTextField.setColumns(10);
		
		
		// 2. 이름
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(13);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);
		
		JLabel nameLabel = new JLabel("이름:             ");
		panel_1.add(nameLabel);
		
		nameTextField = new JTextField();
		panel_1.add(nameTextField);
		nameTextField.setColumns(10);
		
		
		// 3. 생년월일
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(13);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("생년월일:     ");
		panel_2.add(lblNewLabel_1);
		
		yearTextField = new JTextField();
		panel_2.add(yearTextField);
		yearTextField.setColumns(4);
		
		JLabel yearLabel = new JLabel("년");
		panel_2.add(yearLabel);
		
		monthTextField = new JTextField();
		panel_2.add(monthTextField);
		monthTextField.setColumns(4);
		
		JLabel monethLabel = new JLabel("월");
		panel_2.add(monethLabel);
		
		dayTextField = new JTextField();
		panel_2.add(dayTextField);
		dayTextField.setColumns(4);
		
		JLabel dayLabel = new JLabel("일");
		panel_2.add(dayLabel);
		
		
		// 4. 핸드폰 입력
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(13);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_3);
		
		JLabel phonLabel = new JLabel("핸드폰:         ");
		panel_3.add(phonLabel);
		
		phonTextField = new JTextField();
		panel_3.add(phonTextField);
		phonTextField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		JLabel pwFindLabel=new JLabel();
		panel_4.add(pwFindLabel);
		
		
		// 5. 비밀번호 출력부분
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setVgap(15);
		contentPane.add(panel_5);
		
		
		// 6. 찾기&취소 버튼
		
		JButton findButton = new JButton("찾기");
		panel_5.add(findButton);
		
			findButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String id= idTextField.getText();
					String name = nameTextField.getText();
					String year = yearTextField.getText();
					String month = monthTextField.getText();
					String day = dayTextField.getText();
					String birth= year+q+month+q+day+" "+"00:00:00";
					String phone = phonTextField.getText();
					
					SignVo inputVo = new SignVo(id, name, birth, phone);
					
					
					sVo=dao.selectFindPw(inputVo);
					if(sVo == null) {
						pwFindLabel.setText("pw 없음");
					}else {
						pwFindLabel.setText(sVo.getPw());
					}
				}
					
				
			});
			
		
		JButton cansleButton = new JButton("취소");
		panel_5.add(cansleButton);
		cansleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pwfind.this.setVisible(false);
				new Login();
			}
		});
				
		
		
		setVisible(true);
	}

}
