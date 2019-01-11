package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class UDPTimeClient {

	public static void main(String[] args) {
		
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket();
			
			DatagramPacket sendPacket = new DatagramPacket("".getBytes(), 0, new InetSocketAddress(UDPTimeServer.SERVER_IP, UDPTimeServer.PORT));
			socket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[UDPTimeServer.BUFFER_SIZE], UDPTimeServer.BUFFER_SIZE);
			socket.receive(receivePacket);
			
			String data = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
