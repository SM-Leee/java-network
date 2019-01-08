package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
//과제
public class NSLookup {

	public static void main(String[] args) {
		String line = "www.naver.com";
		
		try {
			InetAddress[] inetAddress = InetAddress.getAllByName(line);
			for(InetAddress inetAddres : inetAddress) {
				System.out.println(inetAddres);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		

	}

}
