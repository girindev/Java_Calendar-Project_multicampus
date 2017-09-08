package chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChattingPanel extends JPanel implements ActionListener {
	private JPanel panelArea;
	private JPanel panelInput;
	private JTextArea chatArea;
	private JTextField chatField;
	private JButton btnSend;

	private BufferedReader br;
	private BufferedWriter bw;

	public ChattingPanel() {
		setBackground(Color.PINK);

		panelArea = new JPanel();
		panelInput = new JPanel();
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatField = new JTextField();
		btnSend = new JButton("������");

		// �̺�Ʈ ó����(�������� �޼��� ������ �۾�) ���
		chatField.addActionListener(this);
		btnSend.addActionListener(this);

		panelArea.setLayout(new BorderLayout());
		panelArea.add(new JScrollPane(chatArea));

		panelInput.setLayout(new BorderLayout());
		panelInput.add(chatField);
		panelInput.add(btnSend, BorderLayout.EAST);

		setLayout(new BorderLayout());
		add(new JLabel("�׷� ä�ù�"), BorderLayout.NORTH);
		add(panelArea, BorderLayout.CENTER);
		add(panelInput, BorderLayout.SOUTH);

//		settingNetwork();
	}

	private void settingNetwork() {
		try {
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5555);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ������ ������ �Ŀ� �г��� �Է��ؼ� �����ϱ�
			String nickname = JOptionPane.showInputDialog(this, "��ȭ�� �Է��ϼ���.", JOptionPane.INFORMATION_MESSAGE);

			bw.write(nickname + "\n");
			bw.flush();

			// �г��� ���� �Ŀ��� ������ ������ �޼��� �޴� ������
			new ListenThread().start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent actionevent) {
		String msg = chatField.getText();
		chatField.setText("");

		try {
			bw.write(msg + "\n");
			bw.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	class ListenThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String receiveMsg = br.readLine();
					chatArea.append(receiveMsg + "\n");
					chatArea.setCaretPosition(chatArea.getText().length());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
