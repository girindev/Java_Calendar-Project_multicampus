package member;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthScrollBarUI;

public class Sign extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField nameTextField;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JTextField phonTextField;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Login frame = new Login();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Sign() {
		SignVo sVo = new SignVo();
		SignDao sDao = new SignDao();

		setTitle("\uD68C\uC6D0\uAC00\uC785");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel falseLabel2 = new JLabel("");
		// 1. ���̵� ���� �� �ߺ�Ȯ��
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel idLabel = new JLabel("ID:               ");
		idLabel.setHorizontalAlignment(SwingConstants.LEFT);
		idLabel.setFont(new Font("����", Font.PLAIN, 16));
		panel.add(idLabel);

		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);

		idTextField = new JTextField();
		idTextField.setFont(new Font("����", Font.PLAIN, 12));
		idTextField.setText("");
		panel.add(idTextField);
		idTextField.setColumns(10);

		JButton overlapButton = new JButton("\uC911\uBCF5\uD655\uC778");
		overlapButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(overlapButton);
		overlapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SignVo sVo = sDao.selectById(idTextField.getText());
				if ((idTextField.getText().toString()).equals(sVo.getId())) {
				falseLabel2.setText("�ߺ��� ���̵��Դϴ�");
				
				}

			}
		});

		// 2. ��й�ȣ�Է�
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);

		JLabel PwLabel = new JLabel("PW:                        ");
		PwLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(PwLabel);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(passwordField);
		// 3. ��й�ȣ ���Է�
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);

		JLabel rewriteLabel = new JLabel("�ٽ��Է��Ͻÿ�:");
		rewriteLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(rewriteLabel);

		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		passwordField_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(passwordField_1);
		JLabel falseLabel = new JLabel();
		panel_2.add(falseLabel);

		// �ƹ��͵� �ƴ� �������� â��
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		contentPane.add(panel_3);

		JLabel lblNewLabel_1 = new JLabel("��������");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1);

		// 4. �̸��Է� �κ�
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_4);

		JLabel nameLabel = new JLabel("�̸�:                 ");
		panel_4.add(nameLabel);

		nameTextField = new JTextField();
		panel_4.add(nameTextField);
		nameTextField.setColumns(10);

		// 5. ������� �Է�
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_5);

		JLabel lblNewLabel_3 = new JLabel("�������:         ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblNewLabel_3);

		yearTextField = new JTextField();
		panel_5.add(yearTextField);
		yearTextField.setColumns(4);

		JLabel lblNewLabel_4 = new JLabel("��");
		panel_5.add(lblNewLabel_4);

		monthTextField = new JTextField();
		panel_5.add(monthTextField);
		monthTextField.setColumns(4);

		JLabel lblNewLabel_5 = new JLabel("��");
		panel_5.add(lblNewLabel_5);

		dayTextField = new JTextField();
		panel_5.add(dayTextField);
		dayTextField.setColumns(4);

		JLabel lblNewLabel_6 = new JLabel("��");
		panel_5.add(lblNewLabel_6);

		// 6.�ڵ��� ��ȣ �Է�
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_6);

		JLabel label_3 = new JLabel("�ڵ���:            ");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(label_3);

		phonTextField = new JTextField();
		panel_6.add(phonTextField);
		phonTextField.setColumns(10);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		contentPane.add(panel_8);

		
		falseLabel2.setForeground(Color.RED);
		panel_8.add(falseLabel2);

		// ����,��� �κ�
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		contentPane.add(panel_7);

		JButton signButton = new JButton("����");
		panel_7.add(signButton);

		signButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
					if (idTextField.getText().length() == 0) {
						falseLabel2.setText("���̵� �Է��� �ȵǾ����ϴ�.");
					} else if (nameTextField.getText().length() == 0) {
						falseLabel2.setText("�̸���  �Է��� �ȵǾ����ϴ�.");
					} else if (yearTextField.getText().length() == 0) {
						falseLabel2.setText("�⵵��  �Է��� �ȵǾ����ϴ�.");
					} else if (monthTextField.getText().length() == 0) {
						falseLabel2.setText("����  �Է��� �ȵǾ����ϴ�.");
					} else if (dayTextField.getText().length() == 0) {
						falseLabel2.setText("����  �Է��� �ȵǾ����ϴ�.");
					} else if (phonTextField.getText().length() == 0) {
						falseLabel2.setText("�ڵ�����  �Է��� �ȵǾ����ϴ�.");
					} else {
						sVo.setId(idTextField.getText().toString());
						sVo.setPw(new String(passwordField.getPassword()));
						sVo.setName(nameTextField.getText().toString());
						sVo.setYear(yearTextField.getText().toString());
						sVo.setMonth(monthTextField.getText().toString());
						sVo.setDay(dayTextField.getText().toString());
						sVo.setPhon(phonTextField.getText().toString());

						System.out.println("ȸ������" + sDao.insertSignDao(sVo));
						Sign.this.setVisible(false);

					}
				} else {
					falseLabel.setText("Ʋ�Ƚ��ϴ� �ٽ� �Է��ϼ���");
					falseLabel.setForeground(Color.RED);

				}

			}
		});

		JButton cansleButton = new JButton("���");
		panel_7.add(cansleButton);
		cansleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sign.this.setVisible(false);
				new Login();
			}
		});
		setVisible(true);
	}

}
