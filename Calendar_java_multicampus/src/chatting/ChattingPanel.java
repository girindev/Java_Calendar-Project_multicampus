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
import java.util.ArrayList;
import java.util.stream.Collector.Characteristics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chattingServer.ChatVO;
import connect.DBConnect;
import member.Info;
import user.UserListPanel;

public class ChattingPanel extends JPanel implements ActionListener {
	private JPanel panelArea;
	private JPanel panelInput;
	private JTextArea chatArea;
	private JTextField chatField;
	private JButton btnSend;

	private BufferedReader br;
	private BufferedWriter bw;

	private UserListPanel userListPanel;

	public ChattingPanel(UserListPanel userListPanel) {
		this.userListPanel = userListPanel;
		setBackground(Color.PINK);

		panelArea = new JPanel();
		panelInput = new JPanel();
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatField = new JTextField();
		btnSend = new JButton("보내기");

		// 이벤트 처리기(서버에게 메세지 보내는 작업) 등록
		chatField.addActionListener(this);
		btnSend.addActionListener(this);

		panelArea.setLayout(new BorderLayout());
		panelArea.add(new JScrollPane(chatArea));

		panelInput.setLayout(new BorderLayout());
		panelInput.add(chatField);
		panelInput.add(btnSend, BorderLayout.EAST);

		setLayout(new BorderLayout());
		add(new JLabel("그룹 채팅방"), BorderLayout.NORTH);
		add(panelArea, BorderLayout.CENTER);
		add(panelInput, BorderLayout.SOUTH);

		//
		DBConnect dbCon = new DBConnect();
		ArrayList<ChatVO> chat = dbCon.getChatList();
		for (int i = chat.size() - 1; i >= 0; i--)
			chatArea.append(chat.get(i).getMsg() + "\n");
		chatArea.setCaretPosition(chatArea.getText().length());

		settingNetwork();
	}

	private void settingNetwork() {

		try {
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5555);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			if (bw != null) {
				bw.write(Info.name + "/" + Info.id + "\n");
				bw.flush();

				// 닉네임 전송 후에는 서버가 보내는 메세지 받는 쓰레드
				new ListenThread().start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionevent) {
		String msg = chatField.getText();
		if (msg.trim().equals("")) {
			return;
		}
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
					
					if (receiveMsg.endsWith("]님이 입장하셨습니다.") || receiveMsg.endsWith("]님이 퇴장하였습니다.")) {
						userListPanel.listRefresh();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}