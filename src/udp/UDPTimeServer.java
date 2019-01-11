package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UDPTimeServer {
	
	public static final int PORT = 5000;
	public static final int BUFFER_SIZE = 1024;
	public static final String SERVER_IP = "218.39.221.67";
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket(PORT);
			
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ");
				String data = format.format(new Date());
				byte[] sendData = data.getBytes();

				//3. 데이터 전송
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null && socket.isClosed()==false) {
				socket.close();
			}
		}

	}

}
