package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	private static final int PORT = 5000;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<Writer> listWriters = new ArrayList<Writer>();
		try {
			serverSocket = new ServerSocket();
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localHostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(localHostAddress, PORT));
			log("binding "+localHostAddress+":"+PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();	
			}
			
		} catch (IOException e) {
			log("error : "+e);
		} finally{
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
		System.out.println("[server"+Thread.currentThread().getId()+"]"+log);
	}
}
