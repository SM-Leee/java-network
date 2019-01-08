package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {
	
	public static void main(String[] args) {
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName();
			System.out.println(hostName);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
