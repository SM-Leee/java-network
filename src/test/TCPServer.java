package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
