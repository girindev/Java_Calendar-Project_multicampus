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
 * @author 박성훈
 * 캘린더 서버로 다른 사용자에게 공유
 */
public class CalenderMultiShareServer {
	private ServerSocket serverSocket;
	private List<CalendarThread> threadList;

	public CalenderMultiShareServer() {
		threadList = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(5555);
			while (true) {
				System.out.println("클라이언트 기다리는중");
				Socket socket = serverSocket.accept();
				System.out.println("접속함 : " + socket.getInetAddress());

				// 새로운 클라이언트 접속하면 새로운
				// 쓰레드 객체를 생성해서 리스트에 추가함.
				CalendarThread t = new CalendarThread(socket);
				threadList.add(t);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버 리스트에 있는 모든 쓰레드에게 메세지 발송 명령해서
	// 모든 클라이언트에게 메세지 발송하기 메소드
	public void broadCast(String msg) {
		for (CalendarThread t : threadList) {
			t.speak(msg);
		}
	}

	// 쓰레드 목록에서 특정 쓰레드 삭제하기
	public void removeThread(CalendarThread t) {
		threadList.remove(t);
	}

	// 하나의 클라이언트가 접속했을 때 담당 쓰레드 클래스
	class CalendarThread extends Thread {
		private BufferedReader br;
		private BufferedWriter bw;

		public CalendarThread(Socket socket) {
			// 서버로부터 해당 클라이언트 소켓 전달받아서
			// 초기화 작업 수행
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
					// 클라이언트 퇴장
					 removeThread(this);
				}
				
			}
			
		}

		// 현재 쓰레드가 담당하는 클라이언트에게 메세지 보내기
		public void speak(String msg) {
			try {
				bw.write(msg);
				bw.flush();
			} catch (IOException e) {
				// 클라이언트 퇴장
				 removeThread(this);
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		new CalenderMultiShareServer();
	}
}
