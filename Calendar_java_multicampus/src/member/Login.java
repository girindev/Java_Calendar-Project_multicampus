package member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import calendar.MemoCalendar;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private SignDao dao = new SignDao();
	private SignVo sVo = new SignVo();
	private JLabel label;
	private Info info=new Info(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 5, 0));

		// 아이디 입력
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 4));

		JLabel lbLabel = new JLabel("ID:   ");
		lbLabel.setFont(new Font("굴림", Font.PLAIN, 45));
		lbLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lbLabel);

		idTextField = new JTextField();// 아이디입력 텍스트필드
		idTextField.setFont(new Font("굴림", Font.PLAIN, 23));
		idTextField.setForeground(Color.BLUE);
		idTextField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(idTextField);
		idTextField.setColumns(10);

		// 비밀번호 입력
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(4);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);

		JLabel lblPw = new JLabel("PW: ");
		lblPw.setFont(new Font("굴림", Font.PLAIN, 45));
		panel_1.add(lblPw);

		passwordField = new JPasswordField();// 처음 비밀번호입력
		passwordField.setFont(new Font("굴림", Font.PLAIN, 23));
		passwordField.setColumns(10);
		passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(passwordField);

		// 아이디/비밀번호 찾기
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 16));

		JButton idFindButton = new JButton("아이디 찾기");
		idFindButton.setFont(new Font("굴림", Font.PLAIN, 10));
		panel_2.add(idFindButton);
		idFindButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.setVisible(false);
				new IdFind();

			}
		});

		JButton pwFindButton = new JButton("비밀번호 찾기");
		pwFindButton.setFont(new Font("굴림", Font.PLAIN, 10));
		panel_2.add(pwFindButton);
		pwFindButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.setVisible(false);
				new Pwfind();
			}
		});

		// 출력부분
		JPanel panel4 = new JPanel();
		panel4.setBackground(Color.WHITE);
		label = new JLabel();
		panel4.add(label);
		getContentPane().add(panel4);

		// 로그인버튼 및 회원가입버튼
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(8);
		panel_3.setBackground(Color.WHITE);
		contentPane.add(panel_3);

		JButton logButton = new JButton("로그인");// 로그인
		logButton.setFont(new Font("굴림", Font.PLAIN, 23));

		passwordField.addActionListener(this);
		logButton.addActionListener(this);

		logButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = idTextField.getText();
				String pw = new String(passwordField.getPassword());

				sVo = dao.selectLogin(id, pw);
				if (sVo == null) {
					label.setText("잘못 입력하였습니다.");
				} else {
					Login.this.setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							new MemoCalendar(idTextField.getText());
						}
					});
				}
			}
		});

		panel_3.add(logButton);// 회원가입
		JButton signButton = new JButton("회원가입");
		signButton.setFont(new Font("굴림", Font.PLAIN, 23));
		panel_3.add(signButton);
		signButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.setVisible(false);
				new Sign();
			}
		});
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idTextField.getText();
		String pw = new String(passwordField.getPassword());

		sVo = dao.selectLogin(id, pw);

		if (sVo == null) {
			label.setText("잘못 입력하였습니다.");
		} else {
			System.out.println(sVo.getId()+"///"+sVo.getName());
			info.id=sVo.getId();
			info.name=sVo.getName();
			
			Login.this.setVisible(false);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new MemoCalendar(idTextField.getText());
				}
			});
		}
	}
}