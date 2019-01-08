package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	private static final int PORT = 5000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. Binding (Socket에 SocketAddress(ID Address+Port)를 binding한다.)
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localHostAddress = inetAddress.getHostAddress();

			serverSocket.bind(new InetSocketAddress(localHostAddress, PORT));
			System.out.println("[server] binding "+localHostAddress+":"+PORT);

			// 3. Accept : 클라이언트로부터 소켓 연결이 올 때까지 대기한다.
			Socket socket = serverSocket.accept();	//Blocking
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();

			System.out.println("[server] connected by Client["+remoteHostAddress+":"+remotePort+"]");

			try {

				// 4. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while(true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);	// Blocking
					if(readByteCount == -1) {
						// 정상종료 : Remot socket이 close()를 메소드를 통해서 정상적으로 소켓을 닫은 경운
						System.out.println("[server] closed by Client");
						break;
					}
					//받은 byte를 String으로 변경 후 출력
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] received : "+data);

					// 6. 데이터 쓰기
					os.write(data.getBytes("UTF-8"));
				}
			} catch(SocketException e) {
				System.out.println("[server] abnormal closed by Client");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					// 7. 자원정리
					if(socket != null && socket.isClosed() == false) {
						socket.close();					
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {//Stream이 닫힌 상태에서 close해주면 문제가 생기므로 이렇게 넣어줘야된다.
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
