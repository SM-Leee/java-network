package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatClientThread extends Thread{
	private BufferedReader bufferedReader;
	Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			while(true) {
				String data = bufferedReader.readLine();
				System.out.println(data);
				
			}
			
			
		} catch (UnsupportedEncodingException e) {
			ChatClient.log("Error : "+e);
		} catch (IOException e) {
			ChatClient.log("Error : "+e);
		}
		
		
		
	}

}
