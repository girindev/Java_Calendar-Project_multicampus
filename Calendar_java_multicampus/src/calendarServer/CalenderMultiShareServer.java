package calendarServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author �ڼ���
 * Ķ���� ������ �ٸ� ����ڿ��� ����
 */
public class CalenderMultiShareServer {
	private ServerSocket serverSocket;
	private List<CalendarThread> threadList;

	public CalenderMultiShareServer() {
		threadList = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(5555);
			while (true) {
				System.out.println("Ŭ���̾�Ʈ ��ٸ�����");
				Socket socket = serverSocket.accept();
				System.out.println("������ : " + socket.getInetAddress());

				// ���ο� Ŭ���̾�Ʈ �����ϸ� ���ο�
				// ������ ��ü�� �����ؼ� ����Ʈ�� �߰���.
				CalendarThread t = new CalendarThread(socket);
				threadList.add(t);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���� ����Ʈ�� �ִ� ��� �����忡�� �޼��� �߼� ����ؼ�
	// ��� Ŭ���̾�Ʈ���� �޼��� �߼��ϱ� �޼ҵ�
	public void broadCast(String msg) {
		for (CalendarThread t : threadList) {
			t.speak(msg);
		}
	}

	// ������ ��Ͽ��� Ư�� ������ �����ϱ�
	public void removeThread(CalendarThread t) {
		threadList.remove(t);
	}

	// �ϳ��� Ŭ���̾�Ʈ�� �������� �� ��� ������ Ŭ����
	class CalendarThread extends Thread {
		private BufferedReader br;
		private BufferedWriter bw;

		public CalendarThread(Socket socket) {
			// �����κ��� �ش� Ŭ���̾�Ʈ ���� ���޹޾Ƽ�
			// �ʱ�ȭ �۾� ����
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					String msg = br.readLine();
					broadCast(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// Ŭ���̾�Ʈ ����
					 removeThread(this);
				}
				
			}
			
		}

		// ���� �����尡 ����ϴ� Ŭ���̾�Ʈ���� �޼��� ������
		public void speak(String msg) {
			try {
				bw.write(msg);
				bw.flush();
			} catch (IOException e) {
				// Ŭ���̾�Ʈ ����
				 removeThread(this);
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		new CalenderMultiShareServer();
	}
}
