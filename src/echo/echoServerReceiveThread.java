package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class echoServerReceiveThread extends Thread {

	private Socket socket;
	public echoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {


		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteIpAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remotePort = inetRemoteSocketAddress.getPort();
		EchoServer.log(remoteIpAddress+":"+remotePort+"님이 접속하셨습니다.");

		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
			PrintWriter pw = new PrintWriter(osw,true);

			while(true) {
				String data = br.readLine();
				if(data == null) {
					EchoServer.log(remoteIpAddress+":"+remotePort+"님이 접속을 종료하였습니다.");
					break;
				}
				EchoServer.log("데이터 수신 : "+data);
				pw.println(data);
			}

		} catch(SocketException e) {
			EchoServer.log("abnormal closed by Client");
		} catch (IOException e) {
			EchoServer.log("error : "+e);
		} finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				EchoServer.log("error : "+e);
			}
		}

	}

}
