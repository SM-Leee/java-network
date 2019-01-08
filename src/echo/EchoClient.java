package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP ="218.39.221.67";
	private static final int PORT =5000;
	public static void main(String[] args) {

		Socket socket = null;

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,PORT));
			System.out.println("[client] 연결이 되었습니다.");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			Scanner sc = new Scanner(System.in); 
			while(true) {
				System.out.print(">>");
				String data = sc.nextLine();
				
				if("exit".equals(data)) {
					System.out.println("[client] 서버로부터 접속이 끊어졌습니다.");
					break;
				}
				pw.println(data);
				
				data = br.readLine();
				System.out.print("<<");
				System.out.println(data);
				if(data == null) {
					System.out.println("[client] 서버로부터 접속이 끊어졌습니다.");
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && socket.isClosed() ==false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
