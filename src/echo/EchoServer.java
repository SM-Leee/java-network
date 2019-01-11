package echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
	
	private static final int PORT = 5000;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();

			InetAddress inetAddress = InetAddress.getLocalHost();
			String ipAddress = inetAddress.getHostAddress();
			
			serverSocket.bind(new InetSocketAddress(ipAddress, PORT));
			log("binding"+ipAddress+":"+PORT);
			
			
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new echoServerReceiveThread(socket);
				thread.start();
			}

		} catch (IOException e) {
			log("error : "+e);
			
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error : "+e);
			}
		}

	}

	public static void log(String log) {
		System.out.println("[server#"+Thread.currentThread().getId()+"]"+log);
	}
}
