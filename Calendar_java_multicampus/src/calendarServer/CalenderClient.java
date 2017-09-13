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
 * @author �ڼ���
 * �ٸ� ����ڿ� Ķ���� ����(���) Ŭ���̾�Ʈ��
 * */
public class CalenderClient {
	// TCP ��� ���
	private BufferedReader br;
	private BufferedWriter bw;
	private String IPAddress = "70.12.115.74";
	private IRefreshListener iRefreshListener;
	
	public void setIRefreshListener(IRefreshListener iRefreshListener) {
		this.iRefreshListener = iRefreshListener;
	}
	
	public CalenderClient() {
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
	
	class ListenerThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String receiveMsg = br.readLine();
					if (receiveMsg == "-1")
						System.out.println("����");
					else {
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
