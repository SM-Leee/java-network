package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	private static final String SERVER_IP ="218.39.221.67";
	private static final int PORT =5000;
	public static void main(String[] args) {
		Socket socket = null;
		try {
			// 1. 소켓 생성
			socket = new Socket();

			//1.1 socket buffer size확인 
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferedSize = socket.getSendBufferSize();

			System.out.println(receiveBufferSize+ ":"+sendBufferedSize);

			//1-2. socket buffer size 변경
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);

			//1-3. SO_NODELAY(Nagle Algorithm off)
			socket.setTcpNoDelay(true);
			//socket.setTcpNoDelay(false);

			//1-4. SO_TIMEOUT
			socket.setSoTimeout(1);

			receiveBufferSize = socket.getReceiveBufferSize();
			sendBufferedSize = socket.getSendBufferSize();

			System.out.println(receiveBufferSize+ ":"+sendBufferedSize);

			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));
			System.out.println("[client] connected");

			// 3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. 쓰기
			String data = "Hello World\n";
			os.write(data.getBytes("UTF-8"));

			// 5. 읽기
			byte[] buffer = new byte[255];
			int readByteCount = is.read(buffer);	//Blocking
			if(readByteCount == -1) {
				System.out.println("[client] closed by server");
				return;
			}

			data = new String(buffer, 0, readByteCount, "UTF-8");
			System.out.println("[client] received : "+data);

		} catch (SocketTimeoutException e) {
			System.out.println("[client] time out");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
