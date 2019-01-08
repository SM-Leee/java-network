package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
//과제
public class NSLookup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = null;
		try {
			while(true) {
				System.out.print("> ");
				line = sc.nextLine();
				if("exit".equals(line)) {
					break;
				}
				InetAddress[] inetAddress = InetAddress.getAllByName(line);
				for(InetAddress inetAddres : inetAddress) {
					System.out.println(inetAddres);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}



	}

}
