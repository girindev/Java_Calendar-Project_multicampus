package calendarServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
/**
 * @author �ڼ���
 * �ٸ� ����ڿ� Ķ���� ����(���) Ŭ���̾�Ʈ��
 * */
public class CalenderClient {
	// TCP ��� ���
	private BufferedReader br;
	private BufferedWriter bw;
	private String IPAddress = "70.12.115.74";
	public CalenderClient() {
		try {
			Socket socket =
					new Socket(InetAddress.getByName(IPAddress),5555);
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ListenThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String receiveMsg = br.readLine();
//					if (receiveMsg == "-1")
						//���� ó��
//					else 
//						refresh();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
