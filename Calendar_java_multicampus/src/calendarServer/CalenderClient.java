package calendarServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import popUp.IRefreshListener;
/**
 * @author 박성훈
 * 다른 사용자와 캘린더 공유(통신) 클라이언트단
 * */
public class CalenderClient {
	// TCP 통신 멤버
	private BufferedReader br;
	private BufferedWriter bw;
	private String IPAddress = "127.0.0.1";//70.12.115.74
	private IRefreshListener iRefreshListener;
	
	
	public CalenderClient(IRefreshListener iRefreshListener) {
		this.iRefreshListener = iRefreshListener;
		try {
			Socket socket =
					new Socket(InetAddress.getByName(IPAddress),5555);
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			new ListenerThread().start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendRefreshSignal() {
		try {
			bw.write("1");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ListenerThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String receiveMsg = br.readLine();
					if (receiveMsg == "-1")
						System.out.println("퇴장");
					else {
						System.out.println("메세지 : "+receiveMsg);
						if(iRefreshListener != null) {
							iRefreshListener.refresh(true);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
